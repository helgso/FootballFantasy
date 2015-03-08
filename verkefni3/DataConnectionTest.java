package trunk.verkefni3;

import static org.junit.Assert.*;
import trunk.Java.FootballPlayer;
import trunk.Java.Scheduler;
import trunk.Java.DataConnection;
import trunk.Java.FootballTeam;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class DataConnectionTest {
	
	public static FootballTeam[] ei;
	
	@Before
	public void setUp() {
		FootballTeam[] test;
		test = DataConnection.createFootballTeams();
		ei = test;
	}
	
	//here we check whether there are players in all teams
	//and whether they are not just "nulls"
	@Test
	public void test() {
		int sumPlayers=0;
		for(int i = 0; i < 10; i++){
			FootballPlayer[] testurinn = ei[i].getPlayers();
			sumPlayers += testurinn.length;
			
			for(int u = 0; u < testurinn.length; u++){
				String player = testurinn[u].getName();
				assertNotEquals(player, null);
			}
		}
		System.out.print(sumPlayers);
		assertEquals(sumPlayers, 240);
		
		
	}

	@After
	public void tearDown(){
		this.ei = null;
	}
	
}
