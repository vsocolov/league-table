package com.vsocolov.leaguetable.datastorage;

import com.vsocolov.leaguetable.data.LeagueTableEntry;

import java.util.HashMap;
import java.util.Map;

public class HashMapLeagueDataStorage implements LeagueDataStorage {

    private final Map<String, LeagueTableEntry> dataStorage;

    public HashMapLeagueDataStorage() {
        dataStorage = new HashMap<>();
    }

    @Override
    public LeagueTableEntry getTableEntry(final String team) {
        return dataStorage.get(team);
    }

    @Override
    public void saveTableEntry(final LeagueTableEntry tableEntry) {
        dataStorage.put(tableEntry.getTeamName(), tableEntry);
    }
}
