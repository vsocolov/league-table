package com.vsocolov.leaguetable.datastorage;

import com.vsocolov.leaguetable.data.LeagueTableEntry;

import java.util.List;
import java.util.Optional;

public interface LeagueDataStorage {

    Optional<LeagueTableEntry> getTableEntry(String team);

    void saveTableEntry(final LeagueTableEntry tableEntry);

    List<LeagueTableEntry> listTableEntries();
}
