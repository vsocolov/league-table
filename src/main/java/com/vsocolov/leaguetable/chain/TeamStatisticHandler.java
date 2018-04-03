package com.vsocolov.leaguetable.chain;

import com.vsocolov.leaguetable.data.Match;
import com.vsocolov.leaguetable.datastorage.LeagueDataStorage;

public abstract class TeamStatisticHandler {

  protected TeamStatisticHandler successor;

  protected final LeagueDataStorage leagueDataStorage;

  protected TeamStatisticHandler(final LeagueDataStorage leagueDataStorage) {
    this.leagueDataStorage = leagueDataStorage;
  }

  public void setSuccessor(final TeamStatisticHandler successor) {
    this.successor = successor;
  }

  public abstract void handleStatistic(final Match match);
}
