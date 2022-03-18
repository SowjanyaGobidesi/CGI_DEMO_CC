package gw.surepath.cc.configuration.timeline.creation

//uses gw.api.database.Queries
uses gw.api.database.Query
uses gw.api.database.QuerySelectColumns
uses gw.api.database.Relop
uses gw.api.path.Paths
//uses gw.api.properties.RuntimePropertyRetriever
//uses gw.surepath.suite.configuration.annotation.IncludeInDocumentation
uses org.json.JSONArray
uses org.json.JSONObject


uses javax.servlet.http.HttpServlet
uses javax.servlet.http.HttpServletRequest
uses javax.servlet.http.HttpServletResponse
uses java.lang.Integer

/**
 * This servlet has the method to get the Timeline Entry for the claim and based on the
 * event date it orders in the descending order and returns the list. The number of entries
 * per page is also set in this servlet.
 */
//@IncludeInDocumentation
@gw.servlet.Servlet(TimelineServlet.SERVLET_PATH)
class TimelineServlet extends HttpServlet {

  public static final var SERVLET_PATH: String = "/sp_timeline"

  override function doGet(req: HttpServletRequest, resp: HttpServletResponse) {
    var claimNumber = req.getParameter("claimNumber")
    var pageSize = Integer.parseInt(req.getParameter("pageSize"))
    var startingIndex = Integer.parseInt(req.getParameter("startingIndex"))
    var categoryCodes = req.getParameter("categoryCodes").split(",").map(\elt ->TimelineCategory_SP.get(elt)) //cgi-impl
    var lastEntryID = req.getParameter("lastEntryID")

    //var q = Queries.createQuery<TimelineEntry_SP>(TimelineEntry_SP)
    var q = Query.make(TimelineEntry_SP)
        .compareIn(TimelineEntry_SP#TimelineCategory, categoryCodes) //cgi-impl
        .join(TimelineEntry_SP#Timeline)
        .join(Timeline_SP#Claim)
        .compare(Claim#ClaimNumber, Relop.Equals, claimNumber)
        .select()
        .orderByDescending(QuerySelectColumns.path(Paths.make(TimelineEntry_SP#EventDate)))
    q.setPageSize(pageSize)
    var entryIterator = q.iterator(startingIndex)
    var count = 0
    var timelineJSON = new JSONArray()
    while (entryIterator.hasNext() && count < pageSize) {
      var entry = entryIterator.next()
      timelineJSON.put(entry.toJSONObject())
      if (entry.PublicID == lastEntryID) {
        timelineJSON = new JSONArray()
      }
      count++
    }
    var responseJSON = new JSONObject()
    responseJSON.put("hasMore", entryIterator.hasNext())
    responseJSON.put("nextStartingIndex", startingIndex + count)
    responseJSON.put("entries", timelineJSON)
    resp.ContentType = "application/json"
    resp.setStatus(HttpServletResponse.SC_OK)
    responseJSON.write(resp.getWriter())
  }
}