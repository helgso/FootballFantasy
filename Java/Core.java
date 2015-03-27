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
	
	// post: getTeamLogo("Arsenal") would return "trunk/Pictures/Arsenal/Arsenal.png"
	public String getTeamLogo(String FootballTeam) {
		FootballTeam[] teamTotal = fantasy.getTeamTotal();
		
		for (FootballTeam team : teamTotal) {
			if (team.getName().equals(FootballTeam)) {				
				return team.getLogoPath();
			}
		}
		return "";
	}
	
	public void simulateNextRound() {
		fantasy.simulateNextRound();
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
	
	public MatchResults getMatchResults(int matchNumber) {
		return fantasy.getMatchResults(matchNumber);
	}
	
	
	public FootballTeam getFootballTeam( String teamName ) throws IllegalArgumentException{
		FootballTeam[] teamTotal = this.fantasy.getTeamTotal();
		
		for(int i=0; i<teamTotal.length; i++){
			FootballTeam tempTeam = teamTotal[i];
			if( tempTeam.getName().equals( teamName )){
				return tempTeam;
			}
		}
		throw new IllegalArgumentException("The teamName \"" + teamName + "\" doesn't exist. Call base.getAllTeamNames() for a full list of available teams");
	}
	
	
	public static void main(String[] args) {
		Core core = new Core();
		
		FootballTeam team = core.getFootballTeam( "Liverpool" );
		System.out.println(team.getName());
		
		
		/*
		FootballPlayer total = core.getAllFootballPlayers();
		
		for (FootballPlayer player : total) {
			System.out.println(player.getName());
		}*/
	}
}
