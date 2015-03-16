package trunk.Java;

public class Simulate {
	public static MatchResults match(FootballTeam homeTeam, FootballTeam awayTeam, int roundNum ){
		
		// Initializing a MatchResults instance for this Match
		MatchResults results = new MatchResults( homeTeam, awayTeam );
		
		// Using Random, goals for homeTeam and awayTeam are determined
		results.setHomeGoals(Random.homeGoals(homeTeam, awayTeam));
		results.setAwayGoals(Random.awayGoals(homeTeam, awayTeam));
		
		// Next, using probability, assign each goal to the likeliest
		// FootballPlayer of the corresponding team
		results = pickScorers(results, homeTeam, results.getHomeGoals(), roundNum, true);
		results = pickScorers(results, awayTeam, results.getAwayGoals(), roundNum, false);
		
		return results;
	}
	
	public static MatchResults pickScorers(MatchResults res, FootballTeam team, int goals, int numRound,boolean isHome){
		int sum_goals = 0;
		double chances[] = new double[11];
		
		FootballPlayer[] home = team.getPlayers(); 
		for(FootballPlayer x : home){
			sum_goals += x.getGoals();
		}
		
		
		for(int i = 0; i < 11; i++){
			chances[i] =  home[i].getGoals()/sum_goals;
			if(chances[i] == 0){
				chances[i] = 0.1;
			}
		}
		
		
		int index = 0;
		
		while(goals > 0 ){
			//System.out.println("what the poop");
			double random = Math.random();
			if(random < chances[index]){
				if(isHome){
					res.incrementHomeGoals(home[index]);
				}else{
					res.incrementAwayGoals(home[index]);
				}
				Statistics[] temp = home[index].getStats();
				temp[0].incGoals();
				
				goals--;
			}
			index++;
			if(index == 11){
				index = 0;
			}
			
		}
		return res;
	}
}




/*  * ============
 * Öryggisafrit
 * ============
 * 
 * Hérna er eins og Jósúa skildi við Simulate.java klukkan 20:00 þann 16.03.2015: 
 *
public class Simulate {
	
	private static final double DRAW = 0.12560155;
	private static final double HOME_TEAM_WINS = 0.4484082+DRAW;
	//private static final double AWAY_TEAM_WINS = 0.3002887+DRAW;
	//private double 		0.2512031;
	
	private static final double CHANCE_OF_RD = 0.13;
	private static final double CHANCE_OF_YC = 0.13;
	
	private static double TEMP_HOME;
	private static double TEMP_AWAY;
	
	
	public static MatchResults match(FootballTeam homeTeam, FootballTeam awayTeam, int roundNum ){
			
		MatchResults results = new MatchResults( homeTeam, awayTeam );
		
		double factor = homeTeam.getFactor()-awayTeam.getFactor();
		
		TEMP_HOME = HOME_TEAM_WINS*(1+factor);
		int goals = (int)(Math.random()*6);
		
		System.out.println(TEMP_HOME+" "+goals);
		
		int home_goals = 0;
		int away_goals = 0;
		
		for(int i = 0; i<goals; i++){
			
			double chance = Math.random();
			
			if(chance < TEMP_HOME){
				home_goals++;
			}else{
				away_goals++;
			}
			
		}
		
		System.out.println(home_goals +" - "+ away_goals);
		
		results = pickScorers(results, homeTeam, home_goals, roundNum, true);
		results = pickScorers(results, awayTeam, away_goals, roundNum, false);

		
		
		return results;
	}
	
	public static MatchResults pickScorers(MatchResults res, FootballTeam team, int goals, int numRound,boolean isHome){
		int sum_goals = 0;
		double chances[] = new double[11];
		
		FootballPlayer[] home = team.getPlayers(); 
		for(FootballPlayer x : home){
			sum_goals += x.getGoals();
		}
		
		
		for(int i = 0; i < 11; i++){
			chances[i] =  home[i].getGoals()/sum_goals;
			if(chances[i] == 0){
				chances[i] = 0.1;
			}
		}
		
		
		int index = 0;
		
		while(goals > 0 ){
			//System.out.println("what the poop");
			double random = Math.random();
			if(random < chances[index]){
				if(isHome){
					res.incrementHomeGoals(home[index]);
				}else{
					res.incrementAwayGoals(home[index]);
				}
				Statistics[] temp = home[index].getStats();
				temp[0].incGoals();
				
				goals--;
			}
			index++;
			if(index == 11){
				index = 0;
			}
			
		}
		return res;
	}
}
*/