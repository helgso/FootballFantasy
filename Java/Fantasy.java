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
	
	public Scheduler getScheduler() {
		return schedule;
	}
	
	public FootballTeam[] getTeamTotal() {
		return teamTotal;
	}
	
	public void simulateNextRound( ){
		FootballTeam[][] teams = schedule.getNextRoundSchedule();
		for (int i = 0; i < 5; i++) {
			simulateMatch(teams[i][0], teams[i][1]);
		}
		roundsDone++;
	}
	
	private MatchResults simulateMatch( FootballTeam homeTeam, FootballTeam awayTeam ){
		
		//initialize MatchResults object
		MatchResults result = new MatchResults( homeTeam, awayTeam );
		
		
		//
		//SIMULATE
		//
		
		
		
		//result.matchFinish();
		return result;
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
}
