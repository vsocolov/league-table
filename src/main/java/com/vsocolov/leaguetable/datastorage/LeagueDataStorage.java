package com.vsocolov.leaguetable.datastorage;

import com.vsocolov.leaguetable.data.LeagueTableEntry;

public interface LeagueDataStorage {

    LeagueTableEntry getTableEntry(String team);

    void saveTableEntry(final LeagueTableEntry tableEntry);
}
