package com.vsocolov.leaguetable;

import com.vsocolov.leaguetable.chain.GoalsStatisticHandler;
import com.vsocolov.leaguetable.chain.StatisticHandler;
import com.vsocolov.leaguetable.chain.PointsStatisticHandler;
import com.vsocolov.leaguetable.chain.ResultStatisticHandler;
import com.vsocolov.leaguetable.data.LeagueTableEntry;
import com.vsocolov.leaguetable.data.Match;
import com.vsocolov.leaguetable.datastorage.HashMapLeagueDataStorage;
import com.vsocolov.leaguetable.datastorage.LeagueDataStorage;

import java.util.List;

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
        return leagueDataStorage.listTableEntries();
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
