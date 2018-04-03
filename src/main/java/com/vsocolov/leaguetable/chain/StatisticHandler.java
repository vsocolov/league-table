package com.vsocolov.leaguetable.chain;

import com.vsocolov.leaguetable.data.LeagueTableEntry;
import com.vsocolov.leaguetable.data.Match;
import com.vsocolov.leaguetable.datastorage.LeagueDataStorage;

import java.util.function.BiConsumer;

public abstract class StatisticHandler {

    protected StatisticHandler nextHandler;

    protected final LeagueDataStorage leagueDataStorage;

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

        handlerLogic.accept(homeTeamTableEntry, awayTeamTableEntry);

        leagueDataStorage.saveTableEntry(homeTeamTableEntry);
        leagueDataStorage.saveTableEntry(awayTeamTableEntry);

        if (nextHandler != null) {
            nextHandler.handleStatistic(match);
        }
    }

    public abstract void handleStatistic(final Match match);
}
