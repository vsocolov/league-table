package com.vsocolov.leaguetable;

import com.vsocolov.leaguetable.data.LeagueTableEntry;
import com.vsocolov.leaguetable.data.Match;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;

public class LeagueTableTest {

    private LeagueTable leagueTable;

    @Before
    public void setUp() {
        final List<Match> matches = matchesStub();
        leagueTable = new LeagueTable(matches);
    }

    @Test
    public void getTableEntries_test() {
        final List<LeagueTableEntry> tableEntries = leagueTable.getTableEntries();
        assertThat(tableEntries, is(not(empty())));
    }

    private List<Match> matchesStub() {
        final Match match1 = new Match("Chelsea", "Arsenal", 3, 1);
        final Match match2 = new Match("Chelsea", "Liverpool", 2, 3);
        final Match match3 = new Match("Chelsea", "Manchester United", 0, 1);
        final Match match4 = new Match("Liverpool", "Arsenal", 4, 2);
        final Match match5 = new Match("Liverpool", "Manchester United", 2, 2);
        final Match match6 = new Match("Arsenal", "Manchester United", 1, 1);

        return Arrays.asList(match1, match2, match3, match4, match5, match6);
    }

}