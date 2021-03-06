package trunk.verkefni3;
import static org.junit.Assert.*;
import trunk.Java.Scheduler;
import trunk.Java.DataConnection;
import trunk.Java.FootballTeam;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;


public class SchedulerTest {
	
	public static FootballTeam[][][] ei;
	public static FootballTeam[] test;
	
	@Before
	public void setUp() {
		test = DataConnection.createFootballTeams();
	}
		
	//here we check whether there are players in all teams
	//and whether they are not just "nulls"
	@Test
	public void test() {
		Scheduler Stest = new Scheduler(test);
		assertNotEquals(null, Stest);
			
		FootballTeam[][][] result = Stest.getTotalSchedule();
		assertNotEquals(null, result);
		
		ei = result;
		
		for(int i = 0; i < 18; i++){
			String[] teamsInRound = new String[10];
			for(int u = 0; u < 5; u++){
				String team1 = ei[i][u][0].getName();
				String team2 = ei[i][u][1].getName();
				teamsInRound[(u*2)+0] = team1;
				teamsInRound[(u*2)+1] = team2;
				assertNotEquals(team1, team2);
			}
			for(int u = 0; u <10; u++){
				String team1 = teamsInRound[u];
				assertNotEquals(team1, teamsInRound[(u+1)%10]);
			}
		}
	}
		
	@After
	public void tearDown(){
		ei = null;
		test = null;
	}
		
}

