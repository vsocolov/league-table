package com.vsocolov.leaguetable.statisticschain;

import com.vsocolov.leaguetable.data.LeagueTableEntry;
import com.vsocolov.leaguetable.data.Match;
import com.vsocolov.leaguetable.datastorage.LeagueDataStorage;

import java.util.function.BiConsumer;

public class GoalsStatisticHandler extends StatisticHandler {

    public GoalsStatisticHandler(final LeagueDataStorage leagueDataStorage) {
        super(leagueDataStorage);
    }

    @Override
    public void handleStatistic(final Match match) {
        final BiConsumer<LeagueTableEntry, LeagueTableEntry> handlerLogic = (homeTeamTableEntry, awayTeamTableEntry) -> {
            homeTeamTableEntry.setGoalsFor(homeTeamTableEntry.getGoalsFor() + match.getHomeScore());
            homeTeamTableEntry.setGoalsAgainst(homeTeamTableEntry.getGoalsAgainst() + match.getAwayScore());
            homeTeamTableEntry.setGoalDifference(homeTeamTableEntry.getGoalDifference()
                    + (match.getHomeScore() - match.getAwayScore()));

            awayTeamTableEntry.setGoalsFor(awayTeamTableEntry.getGoalsFor() + match.getAwayScore());
            awayTeamTableEntry.setGoalsAgainst(awayTeamTableEntry.getGoalsAgainst() + match.getHomeScore());
            awayTeamTableEntry.setGoalDifference(awayTeamTableEntry.getGoalDifference()
                    + (match.getAwayScore() - match.getHomeScore()));
        };

        doHandleStatistic(match, handlerLogic);
    }
}
