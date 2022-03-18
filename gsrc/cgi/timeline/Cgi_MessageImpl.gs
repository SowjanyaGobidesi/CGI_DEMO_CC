package cgi.timeline

uses com.guidewire.cc.domain.messaging.impl.MessageCoreExtMethodsImpl
uses gw.api.database.Query
uses gw.api.database.Relop
uses gw.surepath.cc.configuration.timeline.processes.TimelineCreationWorkQueue

/**
 * Author: ankit.nandanwar@cgi.com
 * <p>
 * Class to override the OOTB messaging methods with a custom implementation.
 */
class Cgi_MessageImpl extends MessageCoreExtMethodsImpl implements Cgi_MessagePublicMethods {
  construct(message : Message) {
    super(message)
  }

  override function reportAck() {
    super.reportAck()
    var claim = Owner.Claim
    if (claim != null) { //Safe ordered mesages
      var isTimelineWorkItemAbsentForClaim = Query.make(TimeCreationWorkItem_SP)
          .compare(TimeCreationWorkItem_SP#Claim, Equals, Owner.Claim)
          .compare(TimeCreationWorkItem_SP#Status, Relop.NotEquals, WorkItemStatusType.TC_FAILED)
          .select()
          .Empty
      if (isTimelineWorkItemAbsentForClaim) {
        new TimelineCreationWorkQueue().createWorkItem(Owner.Claim, Owner.Bundle)
        print("EventFired:EventName=${Owner.EventName}::Root=${Owner.EventRootKey}::WorkItem created for ${Owner.Claim}")
      }
    }
  }
}