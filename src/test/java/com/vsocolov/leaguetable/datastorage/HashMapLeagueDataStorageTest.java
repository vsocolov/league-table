package com.vsocolov.leaguetable.datastorage;

import com.vsocolov.leaguetable.data.LeagueTableEntry;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;

public class HashMapLeagueDataStorageTest {

    private LeagueDataStorage dataStorage;

    @Before
    public void setUp() {
        dataStorage = new HashMapLeagueDataStorage();
    }

    @Test
    public void getTableEntry_should_return_entry_if_team_name_exist_in_storage() {
        //insert entry in storage
        final LeagueTableEntry entry = new LeagueTableEntry("Zimbru");
        dataStorage.saveTableEntry(entry);

        final Optional<LeagueTableEntry> resultEntry = dataStorage.getTableEntry("Zimbru");

        assertThat(resultEntry.isPresent(), is(true));
        assertThat(resultEntry.get(), sameInstance(entry));
    }

    @Test
    public void getTableEntry_should_return_optional_empty_if_team_name_does_not_exist_in_storage() {
        final Optional<LeagueTableEntry> resultEntry = dataStorage.getTableEntry("Zimbru");

        assertThat(resultEntry.isPresent(), is(false));
    }

    @Test
    public void saveTableEntry_should_insert_new_entry_in_storage() {
        final LeagueTableEntry entry = new LeagueTableEntry("Dacia");

        dataStorage.saveTableEntry(entry);

        //verify if new entry was created
        final Optional<LeagueTableEntry> createdEntry = dataStorage.getTableEntry(entry.getTeamName());
        assertThat(createdEntry.isPresent(), is(true));
        assertThat(createdEntry.get(), sameInstance(entry));
    }
}