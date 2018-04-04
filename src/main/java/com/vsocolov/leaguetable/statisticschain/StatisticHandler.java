package com.vsocolov.leaguetable.statisticschain;

import com.vsocolov.leaguetable.data.LeagueTableEntry;
import com.vsocolov.leaguetable.data.Match;
import com.vsocolov.leaguetable.datastorage.LeagueDataStorage;

import java.util.function.BiConsumer;

public abstract class StatisticHandler {

    private StatisticHandler nextHandler;

    private final LeagueDataStorage leagueDataStorage;

    protected StatisticHandler(final LeagueDataStorage leagueDataStorage) {
        this.leagueDataStorage = leagueDataStorage;
    }

    public void setNextHandler(final StatisticHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    protected void doHandleStatistic(final Match match, final BiConsumer<LeagueTableEntry, LeagueTableEntry> handlerLogic) {
        final LeagueTableEntry homeTeamTableEntry = leagueDataStorage.getTableEntry(match.getHomeTeam())
                .orElse(new LeagueTableEntry(match.getHomeTeam()));
        final LeagueTableEntry awayTeamTableEntry = leagueDataStorage.getTableEntry(match.getAwayTeam())
                .orElse(new LeagueTableEntry(match.getAwayTeam()));

        // basic logic for specific chain
        handlerLogic.accept(homeTeamTableEntry, awayTeamTableEntry);

        leagueDataStorage.saveTableEntry(homeTeamTableEntry);
        leagueDataStorage.saveTableEntry(awayTeamTableEntry);

        if (nextHandler != null) {
            nextHandler.handleStatistic(match);
        }
    }

    public abstract void handleStatistic(final Match match);
}
