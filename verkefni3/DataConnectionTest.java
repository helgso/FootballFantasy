package trunk.verkefni3;

import static org.junit.Assert.*;
import trunk.Java.FootballPlayer;
import trunk.Java.DataConnection;
import trunk.Java.FootballTeam;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class DataConnectionTest {
	
	public static FootballTeam[] ei, test;
	//public static FootballTeam[] test;
	
	@Before
	public void setUp() {
		
	}
	
	//Check if DataConnection gets information from internet
	//and can create FootballTeam's. Also we check whether there are
	//players in all teams and whether they are not just "nulls"
	@Test
	public void test() {
		test = DataConnection.createFootballTeams();
		assertNotEquals(null, test);
		
		ei = test;
		
		int sumPlayers=0;
		for(int i = 0; i < 10; i++){
			FootballPlayer[] testurinn = ei[i].getPlayers();
			sumPlayers += testurinn.length;
			
			for(int u = 0; u < testurinn.length; u++){
				String player = testurinn[u].getName();
				assertNotEquals(player, null);
			}
		}
		assertEquals(sumPlayers, 240);
	}

	@After
	public void tearDown(){
		ei = null;
		test = null;
	}
}
