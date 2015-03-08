package trunk.Java;

public class Simulate {
	
	
	public static MatchResults match(FootballTeam homeTeam, FootballTeam awayTeam ){
			
		MatchResults results = new MatchResults( homeTeam, awayTeam );
		
		
		//
		//HARD CODED SIMULATION
		//
		
		
		// homeTeam scores two goals
		results.incrementHomeGoals(homeTeam.getPlayers()[4]);
		results.incrementHomeGoals(homeTeam.getPlayers()[7]);
		
		// awayTeam scores one goals
		results.incrementAwayGoals(awayTeam.getPlayers()[10]);
		
	
		/*
		 * SIMULATE
		 * 	put infotmation into result
		 */
		
		
		
		return results;
	}
}
