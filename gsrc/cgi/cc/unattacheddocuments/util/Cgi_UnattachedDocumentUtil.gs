package cgi.cc.unattacheddocuments.util

uses cgi.cc.unattacheddocuments.constant.Cgi_UnattachmentIssueConstants
uses cgi.cc.unattacheddocuments.enumeration.Cgi_UnattachmentIssueEnum
uses gw.api.json.JsonDeserializationOptions
uses gw.api.json.JsonObject
uses gw.api.json.JsonValidationIssue
uses gw.api.json.JsonValidationResult
uses gw.api.util.DisplayableException
uses jsonschema.cgi.cc.financials.check.v1_0.Check
uses jsonschema.cgi.cc.pulse.pulseoutput.v1_0.pulseoutput

/**
 * Date: 29/12/2021
 * Author: ankit.nandanwar@cgi.com
 * <p>
 * Utility class to perform actions related to the entity 'Cgi_UnattachedDocument'.
 */
class Cgi_UnattachedDocumentUtil {

  public static function createUnattachedDocument(type : Cgi_UnattachedDocType, body : JsonObject, issue : String) {
    new Cgi_UnattachedDocument() {
      :DocumentType = type,
      :DocumentExtractJSON = body.toPrettyJsonString(),
      :Issue = issue
    }
  }

  public static function constructIssueStringForUILinks(issue : Cgi_UnattachmentIssueEnum, claimNumber : String,
                                                        exposureClaimOrder : Integer) : String {
    var issueString = "${Cgi_UnattachmentIssueConstants.DESCRIPTION_KEY} ${Cgi_UnattachmentIssueConstants.KEYVALUE_DELIMITER} ${issue.Description}"
    if (claimNumber.HasContent) { // Claim info
      issueString += "${Cgi_UnattachmentIssueConstants.ELEMENT_DELIMITER}${Cgi_UnattachmentIssueConstants.CLAIM_KEY} ${Cgi_UnattachmentIssueConstants.KEYVALUE_DELIMITER} ${claimNumber}"
      if (exposureClaimOrder != null) { // Exposure info
        issueString += "${Cgi_UnattachmentIssueConstants.ELEMENT_DELIMITER}${Cgi_UnattachmentIssueConstants.EXPOSURE_KEY} ${Cgi_UnattachmentIssueConstants.KEYVALUE_DELIMITER} ${claimNumber}${Cgi_UnattachmentIssueConstants.EXPOSURE_MARKER}${exposureClaimOrder}"
      }
    }
    return issueString
  }

  public static function getSchemaNameBasedOnDocumenType(unattachedDocumentType : Cgi_UnattachedDocType) : String {
    var schemaFQN : String
    switch (unattachedDocumentType) {
      case Cgi_UnattachedDocType.TC_INVOICE:
        schemaFQN = Check.FullyQualifiedName
        break
      //TODO: Add new cases after new doc types are added in the typelist
    }
    return schemaFQN
  }

  public static function validateExtractSchema(unattachedDocument : Cgi_UnattachedDocument,
                                               throwDisplayableException : boolean = true) : List<JsonValidationIssue> {
    //var schemaFQN = getSchemaNameBasedOnDocumenType(unattachedDocument.DocumentType)
    var schemaFQN = pulseoutput.FullyQualifiedName
    var parseValidationResult = new JsonValidationResult()
    var deserlizationOptions = new JsonDeserializationOptions()
        .withUnknownPropertyHandlingOption(REJECT)

    JsonObject.parse(unattachedDocument.DocumentExtractJSON, schemaFQN, parseValidationResult, deserlizationOptions)

    if (throwDisplayableException and parseValidationResult.Issues.HasElements) {
      constructErrorAndThrowDisplayableException(parseValidationResult.Issues)
    }
    return parseValidationResult.Issues
  }

  private static function constructErrorAndThrowDisplayableException(validationIssues : List<JsonValidationIssue>) {
    var errorMessageStringBuffer = new StringBuffer(
        "The JSON Extract failed schema validation with the following error(s):")
    validationIssues.eachWithIndex(\issue, index ->
        errorMessageStringBuffer.append("\n${index + 1}. ${issue.localizeMessage()}"))
    throw new DisplayableException(errorMessageStringBuffer.toString())
  }
}