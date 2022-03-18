package cgi.cc.unattacheddocuments.presentation

uses cgi.cc.financials.check.Cgi_CheckCreator
uses cgi.cc.unattacheddocuments.constant.Cgi_UnattachmentIssueConstants
uses gw.api.database.IQueryBeanResult
uses gw.api.database.Query
uses gw.api.filters.StandardQueryFilter
uses gw.api.filters.TypeKeyFilterSet
uses gw.api.json.JsonObject
uses jsonschema.cgi.cc.pulse.pulseoutput.v1_0.pulseoutput

/**
 * Date: 29/12/2021
 * Author: ankit.nandanwar@cgi.com
 * <p>
 * Helper class to support UI related operations for the page 'Cgi_UnattachedDocuments.pcf'.
 */
class Cgi_UnattachedDocumentsUIHelper {

  private var _documentTypeFilter : StandardQueryFilter[]
  private var _checkCreator : Cgi_CheckCreator

  construct() {
    _documentTypeFilter = new TypeKeyFilterSet(Cgi_UnattachedDocument#DocumentType.PropertyInfo).getFilterOptions()
    _checkCreator = new Cgi_CheckCreator()
  }

  public property get UnattachedDocumentList() : IQueryBeanResult<Cgi_UnattachedDocument> {
    return Query.make(Cgi_UnattachedDocument).select()
  }

  public function attach(unattachedDocument : Cgi_UnattachedDocument) {
    var body = JsonObject.parse(unattachedDocument.DocumentExtractJSON, pulseoutput.FullyQualifiedName)
    _checkCreator.createCheckSet(body, unattachedDocument)
    delete(unattachedDocument)
  }

  public property get DocumentTypeFilter() : StandardQueryFilter[] {
    return _documentTypeFilter
  }

  public function delete(unattachedDocument : Cgi_UnattachedDocument) {
    gw.transaction.Transaction.runWithNewBundle(\bundle -> {
      unattachedDocument = bundle.add(unattachedDocument)
      unattachedDocument.remove()
    })
  }

  public function getIssueBreakup(unattachedDocument : Cgi_UnattachedDocument) : String[] {
    return unattachedDocument.Issue.split(Cgi_UnattachmentIssueConstants.ELEMENT_DELIMITER)
  }

  public function decideActionForLinksInIssue(issueElement : String) {
    var claim : Claim
    var issueElementBreakup = issueElement.split(Cgi_UnattachmentIssueConstants.KEYVALUE_DELIMITER)
    var key = issueElementBreakup[0].trim()
    var value = issueElementBreakup[1].trim()

    if (key.contains(Cgi_UnattachmentIssueConstants.CLAIM_KEY)) {
      claim = Claim.finder.findClaimByClaimNumber(value)
      pcf.Claim.go(claim)
    } else if (key.contains(Cgi_UnattachmentIssueConstants.EXPOSURE_KEY)) {
      var issueValueBreakup = value.split(Cgi_UnattachmentIssueConstants.EXPOSURE_MARKER)
      var claimNumber = issueValueBreakup[0].trim()
      var exposureClaimOrder = Integer.parseInt(issueValueBreakup[1].trim())
      claim = Claim.finder.findClaimByClaimNumber(claimNumber)
      var exposure = claim.Exposures.firstWhere(\exp -> exp.ClaimOrder == exposureClaimOrder)
      pcf.ExposureDetail.go(exposure)
    }
  }

  public function isIssueLinkAvailable(issueElement : String) : boolean {
    return not issueElement.contains(
        "${Cgi_UnattachmentIssueConstants.DESCRIPTION_KEY} ${Cgi_UnattachmentIssueConstants.KEYVALUE_DELIMITER}")
  }
}