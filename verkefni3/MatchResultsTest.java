package trunk.verkefni3;
import static org.junit.Assert.*;
import org.junit.After;
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
	
	@After
	public void tearUp(){
		results = null;
	}
	
	// Tests incrementHomeGoals, getHomeGoals and getHomeScoringPlayers
	@Test
	public void testACouple() {
		// Arsenal scores two goals
		results.incrementHomeGoals(arsenal.getPlayers()[10]);
		results.incrementHomeGoals(arsenal.getPlayers()[5]);
		results.incrementAwayGoals(arsenal.getPlayers()[5]);
		
		assertEquals(2, results.getHomeGoals());
		assertEquals(1, results.getAwayGoals());
		assertEquals(2, results.getHomeScoringPlayers().length);
		assertEquals(1, results.getAwayScoringPlayers().length);
		assertNotNull(results.getHomeScoringPlayers()[0]);
		assertNotNull(results.getHomeScoringPlayers()[1]);
		assertNotNull(results.getAwayScoringPlayers()[0]);
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
		// Chelsea scores one goal
		results.incrementAwayGoals(arsenal.getPlayers()[3]);
		
		assertEquals("Arsenal 2 - 1 Chelsea", results.displayResults());
	}
}