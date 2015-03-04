package trunk.Java;

public class Fantasy {
	private int roundsDone;
	//private FootballTeam[] teamTotal;
	//private FootballTeam[][][] schedule;
	private MatchResults[] matchResults;
	
	public Fantasy( ){
		
	}
	
	
	public MatchResults[] simulateNextRound( ){
		return this.matchResults;
	}
	
	public int getRoundsDone(){
		return this.roundsDone;
	}
	
	
	private MatchResults simulateMatch( FootballTeam homeTeam, FootballTeam awayTeam ){
		
		//initialize MatchResults object
		String homeName = homeTeam.getName();
		String awayName = awayTeam.getName();
		MatchResults result = new MatchResults( homeName, awayName );
		
		
		//
		//SIMULATE
		//
		
		
		
		//result.matchFinish();
		return result;
	}
}
