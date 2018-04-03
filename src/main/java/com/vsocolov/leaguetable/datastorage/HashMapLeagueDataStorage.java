package com.vsocolov.leaguetable.datastorage;

import com.vsocolov.leaguetable.data.LeagueTableEntry;

import java.util.*;

public class HashMapLeagueDataStorage implements LeagueDataStorage {

    private final Map<String, LeagueTableEntry> dataStorage;

    public HashMapLeagueDataStorage() {
        dataStorage = new HashMap<>();
    }

    @Override
    public Optional<LeagueTableEntry> getTableEntry(final String team) {
        return Optional.ofNullable(dataStorage.get(team));
    }

    @Override
    public void saveTableEntry(final LeagueTableEntry tableEntry) {
        dataStorage.put(tableEntry.getTeamName(), tableEntry);
    }

    @Override
    public List<LeagueTableEntry> listTableEntries() {
        return new ArrayList<>(dataStorage.values());
    }
}
