package cgi.cc.financials.api.rest

uses cgi.cc.financials.check.Cgi_CheckCreator
uses gw.api.json.JsonObject

/**
 * Date: 27/12/2021
 * Author: ankit.nandanwar@cgi.com
 * <p>
 * REST API Handler for the claim financials web service.
 */
class Cgi_ClaimFinancialsRestAPIHandler {

  /**
   * @param body
   * @return
   */
  public function createCheckSet(body : JsonObject) : String {
    var checkCreator = new Cgi_CheckCreator()
    return checkCreator.createCheckSet(body)
  }
}