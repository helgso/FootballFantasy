package trunk.verkefni3;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import trunk.Java.DataConnection;
import trunk.Java.FootballTeam;
import trunk.Java.MatchResults;

public class MatchResultsTest {
	
	// Collect real data from the English Premier League
	FootballTeam[] teamTotal = DataConnection.createFootballTeams();
	MatchResults results;
	// Use Arsenal and Chelsea as test teams
	FootballTeam arsenal = new FootballTeam(teamTotal[0].getPlayers(), "Arsenal");
	FootballTeam chelsea = new FootballTeam(teamTotal[1].getPlayers(), "Chelsea");
	
	@Before
	public void setUp() throws Exception {
		results = new MatchResults(arsenal, chelsea);
	}
	
	// Tests incrementHomeGoals, getHomeGoals and getHomeScoringPlayers
	@Test
	public void testACouple() {
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
		assertEquals("Arsenal - Chelsea", results.getMatchName());
	}
	
	@Test
	public void testDisplayResults() {		
		// Arsenal scores two goals
		results.incrementHomeGoals(arsenal.getPlayers()[10]);
		results.incrementHomeGoals(arsenal.getPlayers()[5]);
		
		assertEquals("Arsenal 2 - 0 Chelsea", results.displayResults());
	}

}

