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
	
	public MatchResults[] simulateNextRound() {
		return fantasy.simulateNextRound();
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
	
	// ==============
	// TEST TEST TEST
	// ==============
	public static void main(String[] args) {
		/*
		Core base = new Core();
		
		FootballTeam[][] schal = base.getScheduleForNextRound();
		
		System.err.println("Schedule for next round:");
		for (FootballTeam[] match : schal) {
			System.err.println("-> " + match[0].getName() + " vs. " + match[1].getName());
		} System.err.println();
		
		FootballTeam A = base.getFootballTeam(schal[0][0].getName());
		FootballTeam B = base.getFootballTeam(schal[0][1].getName());

		String[] ABefore = new String[11];
		int i = 0;
		for (FootballPlayer player : A.getSimulationTeam()) {
			ABefore[i] = player.getName() + "|" + player.stats[1].getMinutes() + "|" + player.stats[1].getAssists() + "|" +
		                 player.stats[1].getGoals() + "|" + player.stats[1].getGoalsConceded() + "|" +
					     player.stats[1].getOwnGoals() + "|" + player.stats[1].getCleanSheet() + "|" +
		                 player.stats[1].getYellowCards() + "|"+ "|" + player.stats[1].getRedCards();
		    i++;
		}
		String[] BBefore = new String[11];
		i = 0;
		for (FootballPlayer player : B.getSimulationTeam()) {
			BBefore[i] = player.getName() + "|" + player.stats[1].getMinutes() + "|" + player.stats[1].getAssists() + "|" +
		                 player.stats[1].getGoals() + "|" + player.stats[1].getGoalsConceded() + "|" +
					     player.stats[1].getOwnGoals() + "|" + player.stats[1].getCleanSheet() + "|" +
		                 player.stats[1].getYellowCards() + "|"+ "|" + player.stats[1].getRedCards();
		    i++;
		}

		MatchResults[] results = base.simulateNextRound();
		
		String[] AAfter = new String[11];
		i = 0;
		for (FootballPlayer player : A.getSimulationTeam()) {
			AAfter[i] = player.getName() + "|" + player.stats[1].getMinutes() + "|" + player.stats[1].getAssists() + "|" +
		                 player.stats[1].getGoals() + "|" + player.stats[1].getGoalsConceded() + "|" +
					     player.stats[1].getOwnGoals() + "|" + player.stats[1].getCleanSheet() + "|" +
		                 player.stats[1].getYellowCards() + "|"+ "|" + player.stats[1].getRedCards();
		    i++;
		}
		String[] BAfter = new String[11];
		i = 0;
		for (FootballPlayer player : B.getSimulationTeam()) {
			BAfter[i] = player.getName() + "|" + player.stats[1].getMinutes() + "|" + player.stats[1].getAssists() + "|" +
		                 player.stats[1].getGoals() + "|" + player.stats[1].getGoalsConceded() + "|" +
					     player.stats[1].getOwnGoals() + "|" + player.stats[1].getCleanSheet() + "|" +
		                 player.stats[1].getYellowCards() + "|"+ "|" + player.stats[1].getRedCards();
		    i++;
		}
		
		System.err.println("name|minutes|assists|goals|goalsConceded|ownGoals|cleanSheet|YC|RC");
		for (int j = 0; j < 11; j++) {
			System.err.println(ABefore[j]);
			System.err.println(AAfter[j]);
			System.err.println("\n");
			System.err.println(BBefore[j]);
			System.err.println(BAfter[j]);
		}
		
		
		System.err.println("\n\nAre the 5 match results equal?");
		for (int j = 0; j < 5; j++) {
			System.err.println(results[j] == base.getMatchResults(j));
		}
		
		System.err.println("\n Results are:");
		for (int j = 0; j < 5; j++) {
			System.err.println(results[j].displayResults());
		}*/
	}
}
