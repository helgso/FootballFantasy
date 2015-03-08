package trunk.Java;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CoreTest {

	
	public static Core ei;
	
	@Before
	public void setUp() {
		Core test = new Core();
		ei = test;
	}
		
	//here we check whether there are players in all teams
	//and whether they are not just "nulls"
	@Test
	public void test() {
		
		for(int i = 0; i<18; i++){
			
			String[] teams = ei.getNextRoundSchedule();
			assertEquals(teams.length, 5);
			for(int u = 0; u <5; u++){
				String team1 = teams[u];
				assertNotEquals(team1, teams[(u+1)%5]);
				assertNotNull(team1);
				assertNotEquals(team1, "");
			}
		}
		
		//We test here whether or not there are in total 90 matches
		//and that each match is not null or an empty string
		String[] total = ei.getTotalSchedule();
		assertEquals(total.length, 90);
		for(int u = 0; u <90; u++){
			String team1 = total[u];
			System.out.println(team1+" "+u);
			assertNotNull(team1);
			assertNotEquals(team1, "");
		}
	}
		
	@After
	public void tearDown(){
		this.ei = null;
	}

}
