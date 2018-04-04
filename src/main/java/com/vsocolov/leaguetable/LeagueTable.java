package com.vsocolov.leaguetable;

import com.vsocolov.leaguetable.chain.GoalsStatisticHandler;
import com.vsocolov.leaguetable.chain.StatisticHandler;
import com.vsocolov.leaguetable.chain.PointsStatisticHandler;
import com.vsocolov.leaguetable.chain.ResultStatisticHandler;
import com.vsocolov.leaguetable.data.LeagueTableEntry;
import com.vsocolov.leaguetable.data.Match;
import com.vsocolov.leaguetable.datastorage.HashMapLeagueDataStorage;
import com.vsocolov.leaguetable.datastorage.LeagueDataStorage;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;

public class LeagueTable {

    private final LeagueDataStorage leagueDataStorage;

    public LeagueTable(final List<Match> matches) {
        leagueDataStorage = new HashMapLeagueDataStorage();
        processMatchInfo(matches);
    }

    /**
     * Get the ordered list of league table entries for this league table.
     *
     * @return
     */
    public List<LeagueTableEntry> getTableEntries() {
        final List<LeagueTableEntry> entries = leagueDataStorage.listTableEntries();

        // sort entries by points, goal diff, goals for and team names
        entries.sort(comparingInt(LeagueTableEntry::getPoints).reversed()
                .thenComparingInt(LeagueTableEntry::getGoalDifference).reversed()
                .thenComparingInt(LeagueTableEntry::getGoalsFor).reversed()
                .thenComparing(comparing(LeagueTableEntry::getTeamName)));

        return entries;
    }

    private void processMatchInfo(final List<Match> matches) {
        final StatisticHandler rootStatisticHandler = new ResultStatisticHandler(leagueDataStorage);
        final StatisticHandler pointsStatisticHandler = new PointsStatisticHandler(leagueDataStorage);
        final StatisticHandler goalsStatisticHandler = new GoalsStatisticHandler(leagueDataStorage);
        rootStatisticHandler.setNextHandler(pointsStatisticHandler);
        pointsStatisticHandler.setNextHandler(goalsStatisticHandler);

        matches.forEach(rootStatisticHandler::handleStatistic);
    }
}
