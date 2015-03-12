package trunk.Java;

//Takes in two football teams (homeTeam and awayTeam).
//homeTeam wins with two goals against one goal.
//Returns the match results.
public class MockSimulate implements GameMatchInterFace{
	public MatchResults match(FootballTeam homeTeam, FootballTeam awayTeam ){
		MatchResults results = new MatchResults( homeTeam, awayTeam );
		
		//
		//HARD CODED SIMULATION:
		
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
