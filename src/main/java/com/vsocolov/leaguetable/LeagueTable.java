package com.vsocolov.leaguetable;

import com.vsocolov.leaguetable.data.LeagueTableEntry;
import com.vsocolov.leaguetable.data.Match;
import com.vsocolov.leaguetable.datastorage.HashMapLeagueDataStorage;
import com.vsocolov.leaguetable.datastorage.LeagueDataStorage;
import com.vsocolov.leaguetable.statisticschain.GoalsStatisticHandler;
import com.vsocolov.leaguetable.statisticschain.PointsStatisticHandler;
import com.vsocolov.leaguetable.statisticschain.ResultStatisticHandler;
import com.vsocolov.leaguetable.statisticschain.StatisticHandler;
import org.apache.commons.collections.CollectionUtils;

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
     * @return a sorted list of
     */
    public List<LeagueTableEntry> getTableEntries() {
        final List<LeagueTableEntry> entries = leagueDataStorage.listTableEntries();

        // sort entries by points, goal diff, goals for, and team names
        entries.sort(comparingInt(LeagueTableEntry::getPoints)
                .thenComparingInt(LeagueTableEntry::getGoalDifference)
                .thenComparingInt(LeagueTableEntry::getGoalsFor)
                .reversed()
                .thenComparing(comparing(LeagueTableEntry::getTeamName)));

        return entries;
    }

    /**
     * This method set up a statistics chain of responsibility to process step by step specific data of the mach.
     * Ex.: games played, goals marked, win/lose statistics etc.
     *
     * @param matches a list of matches which should be processed and stored
     */
    private void processMatchInfo(final List<Match> matches) {
        if (CollectionUtils.isNotEmpty(matches)) {
            final StatisticHandler rootStatisticHandler = new ResultStatisticHandler(leagueDataStorage);
            final StatisticHandler pointsStatisticHandler = new PointsStatisticHandler(leagueDataStorage);
            final StatisticHandler goalsStatisticHandler = new GoalsStatisticHandler(leagueDataStorage);
            rootStatisticHandler.setNextHandler(pointsStatisticHandler);
            pointsStatisticHandler.setNextHandler(goalsStatisticHandler);

            matches.forEach(rootStatisticHandler::handleStatistic);
        }
    }
}
