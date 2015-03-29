package trunk.Java;

public class Core {
	private Fantasy fantasy;
	private Scheduler schedule;
	
	public Core() {
		fantasy = new Fantasy();
		schedule = fantasy.getScheduler();
	}
	
	// post: Returns five pairs of FootballTeams that will play the
	//       next round. Example:
	//       {{Arsenal, helsea}, {Stoke, Southampton}, ...}.
	public FootballTeam[][] getScheduleForNextRound() {
		return schedule.getNextRoundSchedule();
	}
	
	// post: Same as getNextRoundSchedule but returns 90 pairs. All Matches.
	//       Example: {{{Arsenal, Chelsea}, {Stoke, Southampton}..x5}, ...x18} 90 FootballTeams.
	public FootballTeam[][][] getScheduleForAllRounds() {
		return schedule.getTotalSchedule();
	}
	
	public void simulateNextRound() {
		fantasy.simulateNextRound();
	}
	
	public FootballTeam[] getAllFootballTeams() {
		return fantasy.getTeamTotal();
	}
	
	public String[] getAllFootballTeamNames() {
		String[] teams = new String[10];
		FootballTeam[] teamTotal = fantasy.getTeamTotal();
		
		for (int i = 0; i < teamTotal.length; i++) {
			teams[i] = teamTotal[i].getName();
		}
		return teams;
	}
	
	public FootballPlayer[] getAllFootballPlayers() {
		FootballTeam[] allTeams = fantasy.getTeamTotal();
		FootballPlayer[] allPlayers = new FootballPlayer[240];
		
		for (int i = 0, k = 0; i < 10; i++) {
			FootballPlayer[] playersInTeam = allTeams[i].getFootballPlayers();
			for (int j = 0; j < playersInTeam.length; j++, k++) {
				allPlayers[k] = playersInTeam[j];
			}
		}
		return allPlayers;
	}
	
	public FootballTeam getFootballTeam( String teamName ) throws IllegalArgumentException{
		FootballTeam[] teamTotal = this.fantasy.getTeamTotal();
		
		for(int i=0; i<teamTotal.length; i++){
			FootballTeam tempTeam = teamTotal[i];
			if( tempTeam.getName().equals( teamName )){
				return tempTeam;
			}
		}
		throw new IllegalArgumentException("The teamName \"" + teamName + "\" doesn't exist. Call base.getAllFootballTeamNames() for a full list of available teams");
	}
	
	public MatchResults getMatchResults(int matchNumber) {
		return fantasy.getMatchResults(matchNumber);
	}
	
	public void buyPlayer(FootballPlayer player) {
		player.setMarketValue(player.getMarketValue() + 4);
	}
	
	public void sellPlayer(FootballPlayer player) {
		player.setMarketValue(player.getMarketValue() - 4);
	}
}
