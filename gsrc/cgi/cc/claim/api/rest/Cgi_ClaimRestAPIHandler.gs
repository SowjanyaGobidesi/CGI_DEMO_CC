package cgi.cc.claim.api.rest

uses gw.api.database.Query
uses gw.api.json.JsonObject
uses gw.document.DocumentsUtil
uses jsonschema.cgi.cc.document.document.v1_0.Document

uses java.io.ByteArrayInputStream

/**
 * Date: 06/01/2022
 * Author: ankit.nandanwar@cgi.com
 * <p>
 * REST API Handler for the claims web service.
 */
class Cgi_ClaimRestAPIHandler {

  public function addDocument(body : JsonObject, claimNumber : String) : String {
    var documentDTO = Document.wrap(body)
    var claim = Claim.finder.findClaimByClaimNumber(claimNumber)

    gw.transaction.Transaction.runWithNewBundle(\bundle -> {
      claim = bundle.add(claim)
      var documentCreationInfo = DocumentsUtil.createDocumentCreationInfo(claim)
      documentCreationInfo.Document.Author = documentDTO.DocumentSummary.Author
      documentCreationInfo.Document.DateModified = documentDTO.DocumentSummary.DateModified
      documentCreationInfo.Document.Description = documentDTO.DocumentSummary.Description
      documentCreationInfo.Document.Language = documentDTO.DocumentSummary.Language
      documentCreationInfo.Document.Type = documentDTO.DocumentSummary.DocumentType
      documentCreationInfo.setFile(documentDTO.DocumentSummary.Name, documentDTO.DocumentContent.MimeType,
          new ByteArrayInputStream(documentDTO.DocumentContent.Content))
    }, User.util.UnrestrictedUser)

    return "Document uploaded successfully"
  }
}