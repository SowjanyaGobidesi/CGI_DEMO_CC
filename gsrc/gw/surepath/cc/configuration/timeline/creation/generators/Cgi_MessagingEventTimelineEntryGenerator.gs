package gw.surepath.cc.configuration.timeline.creation.generators

uses gw.api.database.InOperation
uses gw.api.database.Query
uses gw.api.database.Restriction
uses gw.lang.reflect.IType
uses gw.lang.reflect.TypeSystem
uses gw.pl.persistence.core.Bundle
uses gw.surepath.cc.configuration.timeline.creation.TimelineUtil

/**
 * Author: ankit.nandanwar@cgi.com
 * <p>
 * Class that generates timeline entries for messages. Adds links for the messages in the claim timeline.
 */
class Cgi_MessagingEventTimelineEntryGenerator implements TimelineEntryGenerator {

  private static final var MESSAGE_ROOT_ELIGIBLE_FOR_LINKS : List<IType> = {
      Claim.Type
      , Exposure.Type
      , Activity.Type
      , Transaction.Type
      , Check.Type
      , ServiceRequest.Type
      , Note.Type
      , ClaimContact.Type
      , ClaimContactRole.Type
  }

  public function generateEntries(claim : Claim, date : Date) : List<TimelineEntry_SP> {
    var retList = new ArrayList<TimelineEntry_SP>()

    var msgHistEntries = getMsgHistEntriesRelatedToClaim(claim).select()
    msgHistEntries.each(\msgHist -> retList.add(generateMessageEntry(msgHist, claim.Bundle)))

    return retList
  }

  public function generateEntries(bundle : Bundle, claim : Claim) : List<TimelineEntry_SP> {
    var retList = new ArrayList<TimelineEntry_SP>()

    var msgHistEntriesQuery = getMsgHistEntriesRelatedToClaim(claim)

    var timelineLinksQuery = Query.make(TimelineLink_SP)
    timelineLinksQuery.compare(TimelineLink_SP#BeanType, Equals, MessageHistory.Type.toString())
        .join(TimelineLink_SP#TimelineEntry)
        .compare(TimelineEntry_SP#TimelineCategory, Equals, TimelineCategory_SP.TC_MESSAGING)
        .join(TimelineEntry_SP#Timeline)
        .compare(Timeline_SP#Claim, Equals, claim)

    msgHistEntriesQuery.subselect(MessageHistory#PublicID, InOperation.CompareNotIn, timelineLinksQuery, TimelineLink_SP#BeanID)
    msgHistEntriesQuery.select().each(\msgHist -> retList.add(generateMessageEntry(msgHist, bundle)))

    return retList
  }

  private function generateMessageEntry(msgHist : MessageHistory, bundle : Bundle) : TimelineEntry_SP {
    var entry = new TimelineEntry_SP(bundle)
    entry.EventDate = msgHist.CreationTime
    entry.TimelineCategory = TimelineCategory_SP.TC_MESSAGING
    generateMessageLinksAndSummary(msgHist, entry, bundle)
    return entry
  }

  private function generateMessageLinksAndSummary(msgHist : MessageHistory, entry : TimelineEntry_SP, bundle : Bundle) {
    var seq : Integer = 0

    var summary = "${constructLinkString(seq, "Message")} generated, "
    entry.addToTimelineLinks(generateTimelineLink(msgHist, seq, bundle))
    seq++

    var eventRootKeySplit = msgHist.EventRootKey.split(":")
    var rootType = TypeSystem.getByFullNameIfValid("entity.${eventRootKeySplit[0]}")
    var rootId = eventRootKeySplit[1]
    if (MESSAGE_ROOT_ELIGIBLE_FOR_LINKS.hasMatch(\type -> type.isAssignableFrom(rootType))) {
      var root = TimelineUtil.findByPublicId(rootType, "cc:${rootId}")
      if (root == null) {
        summary += "with root ${msgHist.EventRootKey} (retired), "
      } else {
        var rootLabel = root.DisplayName.contains("ID=")
            ? "${msgHist.EventRootKey}"
            : "${root.DisplayName} (${msgHist.EventRootKey})"
        summary += "with root " + constructLinkString(seq, rootLabel) + ", "
        entry.addToTimelineLinks(generateTimelineLink(root as KeyableBean, seq, bundle))
        seq++
      }
    } else {
      summary += "with root ${msgHist.EventRootKey}, "
    }

    summary += "against the event '${msgHist.EventName}' for destination ${msgHist.DestinationID} by ${constructLinkString(seq, msgHist.User.DisplayName)}"
    entry.addToTimelineLinks(generateTimelineLink(msgHist.User, seq, bundle))
    seq++

    entry.Summary = summary
  }

  private function constructLinkString(seq : Integer, context : String) : String {
    return "<#${seq}%${context}%>"
  }

  private function generateTimelineLink(bean : KeyableBean, seq : Integer, bundle : Bundle) : TimelineLink_SP {
    var link = new TimelineLink_SP(bundle)
    link.Sequence = seq
    link.Bean = selectRootBean(bean)
    return link
  }

  private function selectRootBean(bean : KeyableBean) : KeyableBean {
    return bean typeis ClaimContact
        ? bean.Contact
        : bean typeis ClaimContactRole
        ? bean.ClaimContact.Contact
        : bean
  }

  private function getMsgHistEntriesRelatedToClaim(claim : Claim) : Restriction<MessageHistory> {
    return Query.make(MessageHistory)
        .compare(MessageHistory#Claim, Equals, claim)
  }

  override function isOnlyMeantForBatchRun() : boolean {
    return true
  }
}