package com.vsocolov.leaguetable.chain;

import com.vsocolov.leaguetable.data.LeagueTableEntry;
import com.vsocolov.leaguetable.data.Match;
import com.vsocolov.leaguetable.datastorage.LeagueDataStorage;

import java.util.function.BiConsumer;

public class PointsStatisticHandler extends StatisticHandler {

    public PointsStatisticHandler(final LeagueDataStorage leagueDataStorage) {
        super(leagueDataStorage);
    }

    @Override
    public void handleStatistic(final Match match) {
        final BiConsumer<LeagueTableEntry, LeagueTableEntry> handlerLogic = (homeTeamTableEntry, awayTeamTableEntry) -> {
            if (match.getHomeScore() > match.getAwayScore()) {
                homeTeamTableEntry.setPoints(homeTeamTableEntry.getPoints() + 3);
            } else if (match.getHomeScore() < match.getAwayScore()) {
                awayTeamTableEntry.setPoints(awayTeamTableEntry.getPoints() + 3);
            } else {
                homeTeamTableEntry.setPoints(homeTeamTableEntry.getPoints() + 1);
                awayTeamTableEntry.setPoints(awayTeamTableEntry.getPoints() + 1);
            }
        };

        doHandleStatistic(match, handlerLogic);
    }
}
