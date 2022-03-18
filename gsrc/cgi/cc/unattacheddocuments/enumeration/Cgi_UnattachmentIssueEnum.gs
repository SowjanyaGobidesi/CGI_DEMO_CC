package cgi.cc.unattacheddocuments.enumeration

/**
 * Date: 27/12/2021
 * Author: ankit.nandanwar@cgi.com
 * <p>
 * Enum for different types of unattachment issues.
 */
enum Cgi_UnattachmentIssueEnum {
  PAYEE_CONTACT_NOT_FOUND("Payee contact could not be found"),
  CLAIM_NOT_FOUND("Claim could not be found"),
  CLAIM_CLOSED("Claim is in closed state"),
  INCIDENT_NOT_FOUND("Incident could not be found"),
  EXPOSURE_NOT_FOUND("Exposure could not be found"),
  EXPOSURE_CLOSED("Exposure is in closed state"),
  EXPOSURE_OR_CLAIM_NOT_AT_ABILITY_TO_PAY("Exposure/Claim not at ability to pay"),
  FINANCIALS_HOLD_CIQ("Claim under financials hold due to coverage in question"),
  FINANCIALS_HOLD_INCIDENT_ONLY("Claim under financials hold due to being Incident Only"),
  FINANCIALS_HOLD_UNVERIFIED_POLICY("Claim under financials hold due to policy being unverified")

  private var _desc : String

  private construct(desc : String) {
    _desc = desc
  }

  property get Description() : String {
    return _desc
  }
}