package cgi.cc.financials.check

uses cgi.cc.user.util.Cgi_UserUtil
uses cgi.cc.unattacheddocuments.util.Cgi_UnattachedDocumentUtil
uses cgi.cc.unattacheddocuments.enumeration.Cgi_UnattachmentIssueEnum
uses gw.api.database.Query
uses gw.api.database.Restriction
uses gw.api.database.Table
uses gw.api.financials.CheckPayeeInfo
uses gw.api.financials.CurrencyAmount
uses gw.api.json.JsonObject
uses gw.api.util.DisplayableException
uses gw.api.util.TypecodeMapperUtil
uses gw.pl.persistence.core.Bundle
uses jsonschema.cgi.cc.financials.check.v1_0.Check
uses jsonschema.cgi.cc.pulse.pulseoutput.v1_0.pulseoutput

uses java.math.BigDecimal
uses java.text.SimpleDateFormat

/**
 * Date: 28/12/2021
 * Author: ankit.nandanwar@cgi.com
 * <p>
 * Class that facilitates creation of a check.
 */
class Cgi_CheckCreator {

  private var _body : JsonObject
  private var _unattachedDocument : Cgi_UnattachedDocument
  private var _checkDTO : Check
  private var _payee : Contact
  private var _claim : Claim
  private var _incident : Incident
  private var _exposure : Exposure

  public function createCheckSet(body : JsonObject, unattachedDocument : Cgi_UnattachedDocument = null) : String {
    var response : String
    _body = body
    _unattachedDocument = unattachedDocument
    var pulseOutputDto = pulseoutput.wrap(body)
    _checkDTO = pulseOutputDto.data.general

    findPayeeContact()
    findClaim()
    findExposure()

    gw.transaction.Transaction.runWithNewBundle(\bundle -> {
      var issue = findUnattachmentIssue()
      if (issue != null) {
        initiateUnattachedDocumentFlow(issue, bundle)
        response = constructUnattachedFlowInfoResponse(issue)
      } else {
        _exposure = bundle.add(_exposure)
        createNewCheck()
        response = constructSuccessResponse()
      }
    })
    return response
  }

  private function findPayeeContact() {
    if (_checkDTO.primaryPayee.PayeeType == null
        or _checkDTO.primaryPayee.PayeeType == ContactRole.TC_CLAIMANT
        or _checkDTO.primaryPayee.PayeeType == ContactRole.TC_INSURED) {
      findPersonContact()
    } else {
      findCompanyContact()
    }
  }

  private function findPersonContact() {
    //TODO: phone number logic
    var names = _checkDTO.primaryPayee.ownerName.split(" ")
    var firstName = names[0]
    var lastName = names[1]
    _payee = Query.make(Person)
        .compareIgnoreCase(Person#FirstName, Equals, firstName)
        .compareIgnoreCase(Person#LastName, Equals, lastName)
        .select()
        .FirstResult
  }

  private function findCompanyContact() {
    var companyName = _checkDTO.primaryPayee.ownerName
    _payee = Query.make(Company)
        .compareIgnoreCase(Company#Name, Equals, companyName)
        .select()
        .FirstResult
  }

  private function findClaim() {
    findIncident()
    if (_incident != null) {
      _claim = _incident.Claim
    } else {
      var policy = findPolicy()
      _claim = policy.Claim
    }
  }

  private function findIncident() {
    var incidentQueryObject = Query.make(VehicleIncident)

    applyVehicleQueryRestrictions<VehicleIncident>(incidentQueryObject.join(VehicleIncident#Vehicle))

    //Claim restriction added only for demo purposes
    incidentQueryObject.join(VehicleIncident#Claim)
        .compare(Claim#State, Equals, TC_OPEN)

    _incident = incidentQueryObject.select().FirstResult
  }

  private function findPolicy() : Policy {
    var policyQueryObject = Query.make(Policy)
        .compare(Policy#Status, Equals, PolicyStatus.TC_INFORCE)

    var vehicleQueryObject = policyQueryObject.join(RiskUnit#Policy)
        .cast(VehicleRU)
        .join(VehicleRU#Vehicle)
    applyVehicleQueryRestrictions<Policy>(vehicleQueryObject)

    //Claim restriction added only for demo purposes
    policyQueryObject.join(Policy#Claim)
        .compare(Claim#State, Equals, TC_OPEN)

    return policyQueryObject.select().FirstResult
  }

  private function applyVehicleQueryRestrictions<T extends KeyableBean>(query : Table<T>) : Restriction<T> {
    var firstVehicleDTO = _checkDTO.payments.first().vehicle
    return query.compareIgnoreCase(Vehicle#Vin, Equals, firstVehicleDTO.vin)
        //.compareIgnoreCase(Vehicle#Make, Equals, firstVehicleDTO.vehicleMake)
        //.compareIgnoreCase(Vehicle#Model, Equals, firstVehicleDTO.vehicleModel)
        //.compare(Vehicle#Year, Equals, firstVehicleDTO.year)
  }

  /**
   * Every Exposure is an unique combination of Incident, Coverage and Claimant.
   */
  private function findExposure() {
    if (_payee != null and _incident != null) {
      var exposureQueryObject = Query.make(Exposure)

      //Apply Incident Restricition
      exposureQueryObject.compare(Exposure#Incident, Equals, _incident)

      //TODO: Discuss and apply CoverageSubType Restriction
      //exposureQueryObject.compare(Exposure#CoverageSubType, Equals, )

      //Apply Claimant Restriction
      exposureQueryObject.join(ClaimContactRole#Exposure)
          .compare(ClaimContactRole#Role, Equals, ContactRole.TC_CLAIMANT)
          .join(ClaimContactRole#ClaimContact)
          .compare(ClaimContact#Contact, Equals, _payee)

      _exposure = exposureQueryObject.select().FirstResult
    }
  }

  private function findUnattachmentIssue() : Cgi_UnattachmentIssueEnum {
    var issue : Cgi_UnattachmentIssueEnum
    if (_payee == null) {
      issue = PAYEE_CONTACT_NOT_FOUND
    } else if (_claim == null) {
      issue = CLAIM_NOT_FOUND
    } else if (_claim.State == ClaimState.TC_CLOSED) {
      issue = CLAIM_CLOSED
    } else if (_incident == null) {
      issue = INCIDENT_NOT_FOUND
    } else if (_exposure == null) {
      issue = EXPOSURE_NOT_FOUND
    } else if (_exposure.State == ExposureState.TC_CLOSED) {
      issue = EXPOSURE_CLOSED
    } else if (not(_exposure.AtAbilityToPay and _claim.AtAbilityToPay)) {
      issue = EXPOSURE_OR_CLAIM_NOT_AT_ABILITY_TO_PAY
    } else if (_claim.applyFinancialHolds()) {
      if (_claim.CoverageInQuestion) {
        issue = FINANCIALS_HOLD_CIQ
      } else if (_claim.IncidentReport) {
        issue = FINANCIALS_HOLD_INCIDENT_ONLY
      } else if (not _claim.Policy.Verified) {
        issue = FINANCIALS_HOLD_UNVERIFIED_POLICY
      }
    }
    return issue
  }

  private function initiateUnattachedDocumentFlow(issue : Cgi_UnattachmentIssueEnum, bundle : Bundle) {
    var issueString : String
    if (Cgi_UserUtil.isAutomaticPaymentUser(User.util.CurrentUser)) {
      issueString = Cgi_UnattachedDocumentUtil.constructIssueStringForUILinks(issue, _claim.ClaimNumber,
          _exposure.ClaimOrder)
      Cgi_UnattachedDocumentUtil.createUnattachedDocument(Cgi_UnattachedDocType.TC_INVOICE, _body, issueString)
    } else {
      if (_unattachedDocument != null and not _unattachedDocument.Issue.contains(issue.Description)) {
        issueString = Cgi_UnattachedDocumentUtil.constructIssueStringForUILinks(issue, _claim.ClaimNumber,
            _exposure.ClaimOrder)
        _unattachedDocument = bundle.add(_unattachedDocument)
        _unattachedDocument.Issue = issueString
        bundle.commit()
      }
      throw new DisplayableException(issue.Description)
    }
  }

  private function createNewCheck() {
    var firstPaymentDTO = _checkDTO.payments.first()
    var firstPaymentLineItemDTO = firstPaymentDTO.lineItems.first()
    var lineCategory = TypecodeMapperUtil.getTypecodeMapper()
        .getInternalCodeByAlias(LineCategory.RelativeName, "pulse", firstPaymentLineItemDTO.lineCategory)

    var checkCreator = _exposure.newCheckCreator()
    var newCheck = checkCreator
        .withCheckAmount(firstPaymentLineItemDTO.lineItemAmount.AsBigDecimal)
        .withCheckCurrency(firstPaymentDTO.Currency ?: Currency.TC_USD)
        .withComments(_checkDTO.Comments)
        .withCostCategory(firstPaymentDTO.CostCategory ?: CostCategory.TC_BODY)
        .withCostType(firstPaymentDTO.CostType ?: CostType.TC_CLAIMCOST)
        .withErodesReserves(true)
        .withLineCategory(LineCategory.get(lineCategory))
        .withMemo(_checkDTO.Memo)
        .withPaymentMethod(_checkDTO.PaymentMethod ?: PaymentMethod.TC_CHECK)
        .withPaymentType(firstPaymentDTO.PaymentType ?: PaymentType.TC_PARTIAL)
        .withPayTo(_checkDTO.primaryPayee.ownerName)
        .withPrimaryPayee(createNewCheckPayeeInfo())
        .withReportabilityType(_checkDTO.Reportability ?: ReportabilityType.TC_REPORTABLE)
        .withRequestingUser(User.util.CurrentUser)
        .withReservingCurrency(firstPaymentDTO.ReservingCurrency ?: Currency.TC_USD)
        .withScheduledSendDate(_checkDTO.ScheduledSendDate ?: Date.Now)
        .createCheck()
    newCheck.InvoiceNumber = _checkDTO.invoiceNumber
    if (_checkDTO.towedDate != null) {
      newCheck.DateOfService = convertToDate(_checkDTO.towedDate)
    }
    //TODO: Write code to include multiple payments and multiple txn line items
    addTaxLineItem(firstPaymentDTO.taxAmount.AsBigDecimal, newCheck.Payments.first())
    checkCreator.prepareForCommit()
  }

  private function createNewCheckPayeeInfo() : CheckPayeeInfo {
    return new CheckPayeeInfo()
        .withPayee(_payee)
        .withPayeeRole(_checkDTO.primaryPayee.PayeeType ?: ContactRole.TC_CLAIMANT)
  }

  private function addTaxLineItem(taxAmount : BigDecimal, payment : Payment) {
    payment.addNewLineItem(new CurrencyAmount(taxAmount, payment.Currency), payment.Comments, LineCategory.TC_CGI_TAX)
  }

  private function constructUnattachedFlowInfoResponse(issue : Cgi_UnattachmentIssueEnum) : String {
    return "Check could not be created because of ${issue.toString()}. An unattached document is created for manual intervention"
  }

  private function constructSuccessResponse() : String {
    return "Check created successfully on Exposure ${_exposure.ClaimOrder}, Claim ${_claim.ClaimNumber}."
  }

  private function convertToDate(date : String) : Date {
    var sdf = new SimpleDateFormat("MM/dd/yyyy")
    return sdf.parse(date)
  }
}