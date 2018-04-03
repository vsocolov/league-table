package com.vsocolov.leaguetable.chain;

import com.vsocolov.leaguetable.data.LeagueTableEntry;
import com.vsocolov.leaguetable.data.Match;
import com.vsocolov.leaguetable.datastorage.LeagueDataStorage;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;

public class GoalsStatisticHandlerTest {

    private StatisticHandler handler;

    private LeagueDataStorage leagueDataStorage;

    @Before
    public void setUp() {
        leagueDataStorage = mock(LeagueDataStorage.class);
        handler = new GoalsStatisticHandler(leagueDataStorage);
    }

    @Test
    public void handleStatistic_should_create_new_entries_and_set_goals() {
        final Match match = new Match("Liverpool", "Arsenal", 3, 1);
        final ArgumentCaptor<LeagueTableEntry> leagueTableCaptor = ArgumentCaptor.forClass(LeagueTableEntry.class);

        doNothing().when(leagueDataStorage).saveTableEntry(leagueTableCaptor.capture());

        handler.handleStatistic(match);

        final List<LeagueTableEntry> entries = leagueTableCaptor.getAllValues();
        assertThat(entries, hasSize(2));
        assertThat(entries.get(0).getGoalsFor(), equalTo(3));
        assertThat(entries.get(0).getGoalsAgainst(), equalTo(1));
        assertThat(entries.get(0).getGoalDifference(), equalTo(2));
        assertThat(entries.get(1).getGoalsFor(), equalTo(1));
        assertThat(entries.get(1).getGoalsAgainst(), equalTo(3));
        assertThat(entries.get(1).getGoalDifference(), equalTo(-2));
        verify(leagueDataStorage).getTableEntry("Liverpool");
        verify(leagueDataStorage).getTableEntry("Arsenal");
    }

    @Test
    public void handleStatistic_should_update_entries_and_goals() {
        final Match match = new Match("Liverpool", "Arsenal", 3, 1);
        final ArgumentCaptor<LeagueTableEntry> leagueTableCaptor = ArgumentCaptor.forClass(LeagueTableEntry.class);

        final LeagueTableEntry liverpool = new LeagueTableEntry("Liverpool");
        liverpool.setGoalsFor(5);
        liverpool.setGoalDifference(5);

        final LeagueTableEntry arsenal = new LeagueTableEntry("Arsenal");
        arsenal.setGoalsFor(2);
        arsenal.setGoalDifference(2);

        when(leagueDataStorage.getTableEntry("Liverpool")).thenReturn(Optional.of(liverpool));
        when(leagueDataStorage.getTableEntry("Arsenal")).thenReturn(Optional.of(arsenal));
        doNothing().when(leagueDataStorage).saveTableEntry(leagueTableCaptor.capture());

        handler.handleStatistic(match);

        final List<LeagueTableEntry> entries = leagueTableCaptor.getAllValues();
        assertThat(entries, hasSize(2));
        assertThat(entries.get(0).getGoalsFor(), equalTo(8));
        assertThat(entries.get(0).getGoalsAgainst(), equalTo(1));
        assertThat(entries.get(0).getGoalDifference(), equalTo(7));
        assertThat(entries.get(1).getGoalsFor(), equalTo(3));
        assertThat(entries.get(1).getGoalsAgainst(), equalTo(3));
        assertThat(entries.get(1).getGoalDifference(), equalTo(0));
    }
}