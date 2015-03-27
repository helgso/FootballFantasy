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
	//       {"Arsenal - Chelsea", "Stoke - Southampton", ...} 5 strings.
	public String[] getScheduleForNextRound() {
		FootballTeam[][] nextRoundSchedule = schedule.getNextRoundSchedule();
		
		String[] results = new String[5];
		for (int i = 0; i < 5; i++) {
			String homeTeam = nextRoundSchedule[i][0].getName();
			String awayTeam = nextRoundSchedule[i][1].getName();
			results[i] = homeTeam + " - " + awayTeam;
		}	
		
		return results;
	}
	
	// post: Same as getNextRoundSchedule but returns 90 pairs. All Matches.
	//       Example: {"Arsenal - Chelsea", "Stoke - Southampton", ...} 90 strings.
	public String[] getScheduleForAllRounds() {
		FootballTeam[][][] allRoundsSchedule = schedule.getTotalSchedule();
		
		String[] totalSchedule = new String[90];
		for (int i = 0; i < 18; i++) {
			for (int j = 0; j < 5; j++) {
				String homeTeam = allRoundsSchedule[i][j][0].getName();
				String awayTeam = allRoundsSchedule[i][j][1].getName();
				totalSchedule[(i*5)+j] = homeTeam + " - " + awayTeam;
			}
		}
		return totalSchedule;
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
		
		FootballTeam team = core.getFootballTeam( "Liverpoo" );
		System.out.println(team.getName());
		
		
		/*
		FootballPlayer total = core.getAllFootballPlayers();
		
		for (FootballPlayer player : total) {
			System.out.println(player.getName());
		}*/
	}
}
