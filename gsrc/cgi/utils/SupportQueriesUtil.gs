package cgi.utils

uses com.guidewire.pl.system.dependency.PLDependencies

class SupportQueriesUtil {

  /**
   * Property enables Support Queries Screen
   */
  public static property get IsSupportQueriesScreenEnabled() : boolean {
    return perm.System.admindatachangeexec and !PLDependencies.getServerMode().isProduction()
  }

}