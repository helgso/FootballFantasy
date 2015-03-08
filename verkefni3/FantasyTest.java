package trunk.verkefni3;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import trunk.Java.Fantasy;


public class FantasyTest {
		
	private Fantasy fantasy;
	
	@Before
	public void setUp(){
		this.fantasy = new Fantasy();
	}
	
	@After
	public void tearUp(){
		this.fantasy = null;
	}
	
	//
	@Test
	public void testFantasySimulateRounds(){
		
		int numberOfMatches = fantasy.getNumAllMatches();
		int numRounds = fantasy.getNumRounds();

		//Play all matches in the game
		for(int i=0; i<numRounds; ++i){
			fantasy.simulateNextRound();
		}
		
		//Check if every 
		for( int i=0; i<numberOfMatches; ++i ){
			assertEquals(2, fantasy.getMatchResults(i).getHomeGoals());
			assertEquals(1, fantasy.getMatchResults(i).getAwayGoals());		
		}
		
	}
}