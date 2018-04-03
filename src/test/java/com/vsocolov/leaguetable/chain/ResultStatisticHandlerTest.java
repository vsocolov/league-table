package com.vsocolov.leaguetable.chain;

import com.vsocolov.leaguetable.data.LeagueTableEntry;
import com.vsocolov.leaguetable.data.Match;
import com.vsocolov.leaguetable.datastorage.LeagueDataStorage;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;

public class ResultStatisticHandlerTest {

    private StatisticHandler handler;

    private LeagueDataStorage leagueDataStorage;

    @Before
    public void setUp() {
        leagueDataStorage = mock(LeagueDataStorage.class);
        handler = new ResultStatisticHandler(leagueDataStorage);
    }

    @Test
    public void handleStatistic_should_create_new_entries_and_set_results_when_first_team_win() {
        final Match match = new Match("Liverpool", "Arsenal", 3, 1);
        final ArgumentCaptor<LeagueTableEntry> leagueTableCaptor = ArgumentCaptor.forClass(LeagueTableEntry.class);

        doNothing().when(leagueDataStorage).saveTableEntry(leagueTableCaptor.capture());

        handler.handleStatistic(match);

        final List<LeagueTableEntry> entries = leagueTableCaptor.getAllValues();
        assertThat(entries, hasSize(2));
        assertThat(entries.get(0).getWon(), equalTo(1));
        assertThat(entries.get(1).getLost(), equalTo(1));
        assertThat(entries.get(0).getPlayed(), equalTo(1));
        assertThat(entries.get(1).getPlayed(), equalTo(1));
        verify(leagueDataStorage).getTableEntry("Liverpool");
        verify(leagueDataStorage).getTableEntry("Arsenal");
    }

    @Test
    public void handleStatistic_should_create_new_entries_and_set_results_when_second_team_win() {
        final Match match = new Match("Liverpool", "Arsenal", 1, 3);
        final ArgumentCaptor<LeagueTableEntry> leagueTableCaptor = ArgumentCaptor.forClass(LeagueTableEntry.class);

        doNothing().when(leagueDataStorage).saveTableEntry(leagueTableCaptor.capture());

        handler.handleStatistic(match);

        final List<LeagueTableEntry> entries = leagueTableCaptor.getAllValues();
        assertThat(entries, hasSize(2));
        assertThat(entries.get(0).getLost(), equalTo(1));
        assertThat(entries.get(1).getWon(), equalTo(1));
        assertThat(entries.get(0).getPlayed(), equalTo(1));
        assertThat(entries.get(1).getPlayed(), equalTo(1));
        verify(leagueDataStorage).getTableEntry("Liverpool");
        verify(leagueDataStorage).getTableEntry("Arsenal");
    }

    @Test
    public void handleStatistic_should_create_new_entries_and_set_results_when_its_draw() {
        final Match match = new Match("Liverpool", "Arsenal", 1, 1);
        final ArgumentCaptor<LeagueTableEntry> leagueTableCaptor = ArgumentCaptor.forClass(LeagueTableEntry.class);

        doNothing().when(leagueDataStorage).saveTableEntry(leagueTableCaptor.capture());

        handler.handleStatistic(match);

        final List<LeagueTableEntry> entries = leagueTableCaptor.getAllValues();
        assertThat(entries, hasSize(2));
        assertThat(entries.get(0).getDrawn(), equalTo(1));
        assertThat(entries.get(1).getDrawn(), equalTo(1));
        assertThat(entries.get(0).getPlayed(), equalTo(1));
        assertThat(entries.get(1).getPlayed(), equalTo(1));
        verify(leagueDataStorage).getTableEntry("Liverpool");
        verify(leagueDataStorage).getTableEntry("Arsenal");
    }
}