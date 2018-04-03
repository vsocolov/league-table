package com.vsocolov.leaguetable.chain;

import com.vsocolov.leaguetable.data.LeagueTableEntry;
import com.vsocolov.leaguetable.data.Match;
import com.vsocolov.leaguetable.datastorage.LeagueDataStorage;

import java.util.function.BiConsumer;

public class ResultStatisticHandler extends StatisticHandler {

    public ResultStatisticHandler(final LeagueDataStorage leagueDataStorage) {
        super(leagueDataStorage);
    }

    @Override
    public void handleStatistic(final Match match) {
        final BiConsumer<LeagueTableEntry, LeagueTableEntry> handlerLogic = (homeTeamTableEntry, awayTeamTableEntry) -> {
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

            homeTeamTableEntry.setPlayed(homeTeamTableEntry.getPlayed() + 1);
            awayTeamTableEntry.setPlayed(awayTeamTableEntry.getPlayed() + 1);
        };

        doHandleStatistic(match, handlerLogic);
    }
}
