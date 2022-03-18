package cgi.pageprocess
uses gw.lang.parser.GosuParserFactory
uses gw.lang.parser.IGosuParser
uses gw.lang.parser.ScriptabilityModifiers
uses gw.lang.reflect.TypeSystem

uses java.io.ByteArrayOutputStream
uses java.io.PrintStream


class SupportQueriesPageProcess {


  private var _var_input: String as Var_Input = ""
  private var _var_output: String as Var_Output = ""


  function executeScript() {
    var oldOut = System.out
    try {
      var rOut = new ByteArrayOutputStream()
      var rs = ""
      using (var pStream = new PrintStream(rOut)) {
        System.setOut(pStream)
        var inp = createGosuParserAndValidateInput()
        var xpr = inp.parseExpOrProgram(null)
        var rs1 = xpr.evaluate()
        rs = rs1 != null ? rs1 as String : rs
      }
      _var_output = rOut.toString() + "\n" + rs
      gw.api.util.LocationUtil.addRequestScopedInfoMessage("Script excuted successfully. Please check results in Result area.")
    } catch (e) {
      gw.api.util.LocationUtil.addRequestScopedErrorMessage("Error ${e}}")
    } finally {
      //reset to old output stream
      System.setOut(oldOut)
    }
  }

  function executeSQL(sqlVar :String) : Map <String, java.util.List<java.lang.String[]>>{

    var results : java.util.List<java.lang.String[]> = {}
    var finalHeader : java.lang.String[] = {}
    var tableHeader : java.util.List<java.lang.String[]> = {}
    var finalResults = new HashMap<String, java.util.List<java.lang.String[]>>()

    try{

      var regexPattern = "(\\s*) (//b) (ALTER|CREATE|DELETE|DROP|EXEC(UTE) {0,1} |INSERT( +INTO) {0,1} |MERGE|UPDATE) (\\b)(\\s*).*"
      var p = java.util.regex.Pattern.compile(regexPattern, java.util.regex.Pattern.CASE_INSENSITIVE | java.util.regex.Pattern.UNICODE_CASE)
      if(p.matcher(sqlVar).matches()){
        gw.api.util.LocationUtil.addRequestScopedErrorMessage("Only Select Queries can  be executed!")
        return null ;
      }else{
        using ( var conn = com.guidewire.pl.system.database.DatabaseDependencies.Database.ConnectionPool.Connection,
                var pstmt = conn.prepareStatement(sqlVar),var rs = pstmt.executeQuery()){
          results = {}
          tableHeader={}
          var columns = rs.MetaData.getColumnCount()
          if(columns>100){
            columns =100
          }
          finalHeader = new String[columns]
          for(i in 1..columns){
            finalHeader[i-1]=rs.MetaData.getColumnLabel(i)
          }
          var rows =0
          while(rs.next() && rows <500){
            var data = new String[columns]
            for(i in 1..columns){
              data[i-1]=rs.getString(i)
            }
            results.add(data)
            rows++
          }
          if(rs.next()){
            gw.api.util.LocationUtil.addRequestScopedWarningMessage("too many results, add where condition")
          }
        }

        tableHeader.add(finalHeader)
        finalResults.put("Header",tableHeader)
        finalResults.put("Results",results)
      }

    }
    catch (e:Exception){
      gw.api.util.LocationUtil.addRequestScopedErrorMessage("Error ${e}}")
    }
    finally {
      {

      }
    }
    return finalResults
  }


  function clear() {
    _var_output = null
    _var_input = _var_output
  }

  private function createGosuParserAndValidateInput(): IGosuParser {
    var parser = GosuParserFactory.createParser(TypeSystem.getCompiledGosuClassSymbolTable(), ScriptabilityModifiers.SCRIPTABLE)
    parser.setThrowParseExceptionForWarnings(false)
    parser.setDontOptimizeStatementLists(false)
    parser.setWarnOnCaseIssue(false)
    parser.setEditorParser(false)
    parser.setScript(_var_input)
    return parser
  }

}