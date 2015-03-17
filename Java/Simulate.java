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
		
		FootballPlayer[] home = team.getFootballPlayers(); 
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