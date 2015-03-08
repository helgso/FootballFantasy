package trunk.verkefni3;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import trunk.Java.FootballTeam;
import trunk.Java.DataConnection;
import trunk.Java.MatchResults;
import trunk.Java.Simulate;


public class SimulateTest {
	
	private MatchResults results;
	
	private FootballTeam homeTeam;
	private FootballTeam awayTeam;
	
	
	@Before
	public void setUp(){
		FootballTeam[] teams = DataConnection.createFootballTeams();		
		this.homeTeam = teams[0];
		this.awayTeam = teams[1];
	}
	
	@After
	public void tearUp(){
		this.homeTeam = null;
		this.awayTeam = null;
		this.results  = null;
	}
	
	//Test if Simulation.match can take two teams as a argument and
	//let both teams score (see more test in "MatchResultTest.java")
	@Test
	public void testSimulation(){
		
		this.results = Simulate.match(this.homeTeam, this.awayTeam);
		
		assertEquals(2, results.getHomeGoals());
		assertEquals(1, results.getAwayGoals());
		assertNotNull(results.getHomeScoringPlayers());
		assertNotNull(results.getAwayScoringPlayers());	
	}
}
