package com.vsocolov.leaguetable.chain;

import com.vsocolov.leaguetable.data.LeagueTableEntry;
import com.vsocolov.leaguetable.data.Match;
import com.vsocolov.leaguetable.datastorage.LeagueDataStorage;

public class WinStatisticHandler extends TeamStatisticHandler {

    public WinStatisticHandler(final LeagueDataStorage leagueDataStorage) {
        super(leagueDataStorage);
    }

    @Override
    public void handleStatistic(final Match match) {
        final LeagueTableEntry homeTeamTableEntry = leagueDataStorage.getTableEntry(match.getHomeTeam());
        final LeagueTableEntry awayTeamTableEntry = leagueDataStorage.getTableEntry(match.getAwayTeam());

        if (match.getHomeScore() > match.getAwayScore()) {
            homeTeamTableEntry.setWon(homeTeamTableEntry.getWon() + 1);
            awayTeamTableEntry.setLost(awayTeamTableEntry.getLost() + 1);
        } else if (match.getHomeScore() < match.getAwayScore()) {
            homeTeamTableEntry.setLost(homeTeamTableEntry.getLost() + 1);
            awayTeamTableEntry.setWon(awayTeamTableEntry.getWon() + 1);
        } else {
            homeTeamTableEntry.setDrawn(homeTeamTableEntry.getDrawn() + 1);
            awayTeamTableEntry.setDrawn(awayTeamTableEntry.getDrawn() + 1);
        }

        leagueDataStorage.saveTableEntry(homeTeamTableEntry);
        leagueDataStorage.saveTableEntry(awayTeamTableEntry);

        if (successor != null) {
            successor.handleStatistic(match);
        }
    }
}
