package com.vsocolov.leaguetable;

import com.vsocolov.leaguetable.data.LeagueTableEntry;
import com.vsocolov.leaguetable.data.Match;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;

public class LeagueTableTest {

    private LeagueTable leagueTable;

    @Test
    public void getTableEntries_should_sort_result_by_points() {
        final Match match1 = new Match("Chelsea", "Arsenal", 3, 1);
        final Match match2 = new Match("Chelsea", "Liverpool", 0, 0);
        final Match match3 = new Match("Liverpool", "Arsenal", 0, 1);
        leagueTable = new LeagueTable(Arrays.asList(match1, match2, match3));

        final List<LeagueTableEntry> tableEntries = leagueTable.getTableEntries();

        assertThat(tableEntries, hasSize(3));
        assertThat(tableEntries.get(0).getTeamName(), equalTo("Chelsea"));
        assertThat(tableEntries.get(1).getTeamName(), equalTo("Arsenal"));
        assertThat(tableEntries.get(2).getTeamName(), equalTo("Liverpool"));
    }

    @Test
    public void getTableEntries_should_sort_result_by_points_then_goals_difference() {
        final Match match1 = new Match("Chelsea", "Arsenal", 2, 1);
        final Match match2 = new Match("Chelsea", "Liverpool", 0, 3);
        final Match match3 = new Match("Liverpool", "Arsenal", 0, 1);
        leagueTable = new LeagueTable(Arrays.asList(match1, match2, match3));

        final List<LeagueTableEntry> tableEntries = leagueTable.getTableEntries();

        assertThat(tableEntries, hasSize(3));
        assertThat(tableEntries.get(0).getTeamName(), equalTo("Liverpool"));
        assertThat(tableEntries.get(1).getTeamName(), equalTo("Arsenal"));
        assertThat(tableEntries.get(2).getTeamName(), equalTo("Chelsea"));
    }

    @Test
    public void getTableEntries_should_sort_result_by_points_then_goals_difference_then_goals_for() {
        final Match match1 = new Match("Chelsea", "Arsenal", 1, 1);
        final Match match2 = new Match("Chelsea", "Liverpool", 3, 3);
        final Match match3 = new Match("Liverpool", "Arsenal", 2, 2);
        leagueTable = new LeagueTable(Arrays.asList(match1, match2, match3));

        final List<LeagueTableEntry> tableEntries = leagueTable.getTableEntries();

        assertThat(tableEntries, hasSize(3));
        assertThat(tableEntries.get(0).getTeamName(), equalTo("Liverpool"));
        assertThat(tableEntries.get(1).getTeamName(), equalTo("Chelsea"));
        assertThat(tableEntries.get(2).getTeamName(), equalTo("Arsenal"));
    }

    @Test
    public void getTableEntries_should_sort_result_by_points_then_goals_difference_then_goals_for_then_names() {
        final Match match1 = new Match("Chelsea", "Arsenal", 1, 1);
        final Match match2 = new Match("Chelsea", "Liverpool", 1, 1);
        final Match match3 = new Match("Liverpool", "Arsenal", 1, 1);
        leagueTable = new LeagueTable(Arrays.asList(match1, match2, match3));

        final List<LeagueTableEntry> tableEntries = leagueTable.getTableEntries();

        assertThat(tableEntries, hasSize(3));
        assertThat(tableEntries.get(0).getTeamName(), equalTo("Arsenal"));
        assertThat(tableEntries.get(1).getTeamName(), equalTo("Chelsea"));
        assertThat(tableEntries.get(2).getTeamName(), equalTo("Liverpool"));
    }

    @Test
    public void getTableEntries_should_return_empty_result_if_matches_list_is_empty() {
        leagueTable = new LeagueTable(Collections.emptyList());

        final List<LeagueTableEntry> tableEntries = leagueTable.getTableEntries();

        assertThat(tableEntries, is(empty()));
    }

    @Test
    public void getTableEntries_should_return_empty_result_if_matches_list_is_null() {
        leagueTable = new LeagueTable(null);

        final List<LeagueTableEntry> tableEntries = leagueTable.getTableEntries();

        assertThat(tableEntries, is(empty()));
    }
}