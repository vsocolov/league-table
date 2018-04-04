package com.vsocolov.leaguetable.statisticschain;

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

public class PointsStatisticHandlerTest {

    private StatisticHandler handler;

    private LeagueDataStorage leagueDataStorage;

    @Before
    public void setUp() {
        leagueDataStorage = mock(LeagueDataStorage.class);
        handler = new PointsStatisticHandler(leagueDataStorage);
    }

    @Test
    public void handleStatistic_should_create_new_entries_and_set_win_and_lost_points() {
        final Match match = new Match("Liverpool", "Arsenal", 3, 1);
        final ArgumentCaptor<LeagueTableEntry> leagueTableCaptor = ArgumentCaptor.forClass(LeagueTableEntry.class);

        doNothing().when(leagueDataStorage).saveTableEntry(leagueTableCaptor.capture());

        handler.handleStatistic(match);

        assertResults(leagueTableCaptor, 3, 0);
    }

    @Test
    public void handleStatistic_should_create_new_entries_and_set_drawn_points() {
        final Match match = new Match("Liverpool", "Arsenal", 1, 1);
        final ArgumentCaptor<LeagueTableEntry> leagueTableCaptor = ArgumentCaptor.forClass(LeagueTableEntry.class);

        doNothing().when(leagueDataStorage).saveTableEntry(leagueTableCaptor.capture());

        handler.handleStatistic(match);

        assertResults(leagueTableCaptor, 1, 1);
    }

    private void assertResults(ArgumentCaptor<LeagueTableEntry> leagueTableCaptor, int firstTeamPoints, int secondTeamPoints) {
        final List<LeagueTableEntry> entries = leagueTableCaptor.getAllValues();
        assertThat(entries, hasSize(2));
        assertThat(entries.get(0).getPoints(), equalTo(firstTeamPoints));
        assertThat(entries.get(1).getPoints(), equalTo(secondTeamPoints));
        verify(leagueDataStorage).getTableEntry("Liverpool");
        verify(leagueDataStorage).getTableEntry("Arsenal");
    }
}