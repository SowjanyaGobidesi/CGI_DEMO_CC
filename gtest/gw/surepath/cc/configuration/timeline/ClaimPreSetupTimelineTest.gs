package gw.surepath.cc.configuration.timeline

uses gw.api.databuilder.ClaimBuilder
uses gw.testharness.TestBase

/**
 * Created by schan on 6/19/2015.
 */
class ClaimPreSetupTimelineTest extends TestBase {
  function testPreSetupNonNullTimeline() {

    var claim = ClaimBuilder.auto().createAndCommit()
    assertThat(claim.Timeline_SP).isNotEqualTo(null)
  }

  function testPreSetupEmptyTimeline(){
    var claim = ClaimBuilder.auto().createAndCommit()
    assertThat(claim.Timeline_SP.TimelineEntries.length).isEqualTo(0)

  }


}