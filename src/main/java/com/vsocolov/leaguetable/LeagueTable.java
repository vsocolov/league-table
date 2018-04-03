package com.vsocolov.leaguetable;

import com.vsocolov.leaguetable.data.LeagueTableEntry;
import com.vsocolov.leaguetable.data.Match;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class LeagueTable {


  public LeagueTable(final List<Match> matches) {
  }

  /**
   * Get the ordered list of league table entries for this league table.
   *
   * @return
   */
  public List<LeagueTableEntry> getTableEntries() {
    throw new NotImplementedException();
  }
}
