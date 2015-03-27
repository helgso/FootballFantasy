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
		FootballPlayer[] simulationHome = homeTeam.getSimulationTeam();
		FootballPlayer[] simulationAway = awayTeam.getSimulationTeam();
		
		results = pickScorers(results, simulationHome, results.getHomeGoals(), roundNum, true);
		results = pickScorers(results, simulationAway, results.getAwayGoals(), roundNum, false);
		
		
		return results;
	}
	
	public static MatchResults pickScorers(MatchResults res, FootballPlayer[] team, int goals, int numRound, boolean isHome){
		double sum_goals = 0;
		double chances[] = new double[11];
		
		FootballPlayer[] home = team; 
		for(FootballPlayer x : home){
			sum_goals += x.getGoals();
		}
		
		double x = 0;
		for(int i = 0; i < 11; i++){
			chances[i] =  (home[i].getGoals())/sum_goals;
			x += chances[i];
		}
		
		int index = 0;
		
		while(goals > 0 ){
			int i = Random.determineValue(chances);
			if(isHome){
				res.addHomeScorer(home[i]);
			}else{
				res.addAwayScorer(home[i]);
			}
			Statistics[] temp = home[index].getStats();
			temp[0].incGoals();
			
			goals--;	
		}
		return res;
	}
}