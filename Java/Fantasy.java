package trunk.Java;

public class Fantasy {
	private int roundsDone;
	private FootballTeam[] teamTotal;
	private Scheduler schedule;
	private MatchResults[] matchResults;
	
	public Fantasy( ){
		roundsDone = 0;
		teamTotal = DataConnection.createFootballTeams();
		schedule = new Scheduler(teamTotal);
		matchResults = new MatchResults[90];
	}
	
	public void simulateNextRound( ){
		
	}
	
	// pre:  0 <= matchNumber <= 89
	// post: Returns MatchResults from the game matchNumber.
	public MatchResults getMatchResults(int matchNumber) {
		if (0 <= matchNumber && matchNumber <= 89)
			return matchResults[matchNumber];
		return null;
	}
	
	public int getRoundsDone(){
		return this.roundsDone;
	}
	
	public Scheduler getScheduler() {
		return schedule;
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
