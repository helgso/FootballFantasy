package trunk.verkefni3;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import trunk.Java.DataConnection;
import trunk.Java.FootballTeam;
import trunk.Java.MatchResults;

public class MatchResultsTest {
	
	FootballTeam[] teamTotal;
	MatchResults results;
	FootballTeam arsenal;
	FootballTeam chelsea;

	@Before
	public void setUp() throws Exception {
		// Collect real data from the English Premier League
		teamTotal = DataConnection.createFootballTeams();
		// Use two teams for a Match
		arsenal = new FootballTeam(teamTotal[0].getPlayers(), "Arsenal");
		chelsea = new FootballTeam(teamTotal[1].getPlayers(), "Chelsea");
		// Create a MatchResults object for that Match
	}
	
	// Tests incrementHomeGoals, getHomeGoals and getHomeScoringPlayers
	@Test
	public void testACouple() {
		results = new MatchResults(arsenal, chelsea);
		
		// Arsenal scores two goals
		results.incrementHomeGoals(arsenal.getPlayers()[10]);
		results.incrementHomeGoals(arsenal.getPlayers()[5]);
		
		assertEquals(2, results.getHomeGoals());
		assertEquals(2, results.getHomeScoringPlayers().length);
		assertNotNull(results.getHomeScoringPlayers()[0]);
		assertNotNull(results.getHomeScoringPlayers()[1]);	
	}
	
	@Test
	public void testGetMatchName() {
		results = new MatchResults(arsenal, chelsea);
		
		assertEquals("Arsenal - Chelsea", results.getMatchName());
	}
	
	@Test
	public void testDisplayResults() {
		results = new MatchResults(arsenal, chelsea);
		
		// Arsenal scores two goals
		results.incrementHomeGoals(arsenal.getPlayers()[10]);
		results.incrementHomeGoals(arsenal.getPlayers()[5]);
		
		assertEquals("Arsenal 2 - 0 Chelsea", results.displayResults());
	}

}

