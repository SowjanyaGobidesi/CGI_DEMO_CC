package cgi.cc.user.util

/**
 * Date: 29/12/2021
 * Author: ankit.nandanwar@cgi.com
 * <p>
 * Utility class to perform actions related to user.
 */
class Cgi_UserUtil {

  private static final var AUTOMATIC_PAYMENT_USER_PUBLICID = "techuser:1"

  public static function isAutomaticPaymentUser(user : User) : boolean {
    return user.PublicID == AUTOMATIC_PAYMENT_USER_PUBLICID
  }
}