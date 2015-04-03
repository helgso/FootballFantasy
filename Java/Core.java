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
	
	// ======================
	// 1 TEST 1 TEST 1 TEST 1
	// ======================
	/*public static void main(String[] args) {
		
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
		}
	}*/
	
	// ======================
	// 2 TEST 2 TEST 2 TEST 2
	// ======================
	/*public static void main(String[] args) {
		
		Core base = new Core();
		
		FootballTeam[] teams = base.getAllFootballTeams();
		
		for (int j = 0; j < teams.length; j++) {
			FootballPlayer[] players = teams[j].getFootballPlayers(); 
			int sumOwnGoals = 0;
			int sumGoals = 0;
			for (int i = 0; i < players.length; i++) {
				sumGoals += players[i].stats[0].getGoals();
				sumOwnGoals += players[i].stats[0].getOwnGoals();
			}
			
			System.out.println(teams[j].getName() + " has sumOwnGoals: " + sumOwnGoals + ", sumGoals: " + sumGoals + ", ratio: " + (1.0*sumOwnGoals)/sumGoals);
		}
	}*/
	
	// ======================
	// 3 TEST 3 TEST 3 TEST 3
	// ======================
	/*public static void main(String[] args) {
		
		Core base = new Core();
		
		FootballTeam[] teams = base.getAllFootballTeams();
		
		int sumsumAss = 0;
		int sumsumGKAss = 0;
		int sumsumDFAss = 0;
		int sumsumMFAss = 0;
		int sumsumFWAss = 0;
		int sumsumGoals = 0;
		for (int j = 0; j < teams.length; j++) {
			FootballPlayer[] players = teams[j].getFootballPlayers(); 
			int sumAss = 0;
			int sumGKAss = 0;
			int sumDFAss = 0;
			int sumMFAss = 0;
			int sumFWAss = 0;
			int sumGoals = 0;
			for (int i = 0; i < players.length; i++) {
				sumGoals += players[i].stats[0].getGoals();
				sumAss += players[i].stats[0].getAssists();
				
				if (players[i].getPosition() == Position.GK)
					sumGKAss += players[i].stats[0].getAssists();
				if (players[i].getPosition() == Position.DF)
					sumDFAss += players[i].stats[0].getAssists();
				if (players[i].getPosition() == Position.MF)
					sumMFAss += players[i].stats[0].getAssists();
				if (players[i].getPosition() == Position.FW)
					sumFWAss += players[i].stats[0].getAssists();
			}
			
			sumsumAss += sumAss;
			sumsumGKAss += sumGKAss;
			sumsumDFAss += sumDFAss;
			sumsumMFAss += sumMFAss;
			sumsumFWAss += sumFWAss;
			sumsumGoals += sumGoals;
			
			//System.out.println(teams[j].getName() + ":");
			//System.out.println("GKAss: " + sumGKAss + ", DFAss: " + sumDFAss + ", MFAss: " + sumMFAss + ", FDAss: " + sumFWAss);
			//System.out.println("TotalAss: " + sumAss + ", TotalGoals: " + sumGoals + ", ratio: " + (1.0*sumAss)/sumGoals + "\n");
		}
		
		System.out.println("==========================================");
		System.out.println("sumsumGKAss: " + sumsumGKAss + ", sumsumDFAss: " + sumsumDFAss + ", sumsumMFAss: " + sumsumMFAss + ", sumsumFWAss: " + sumsumFWAss);
		System.out.println("sumsumAss: " + sumsumAss + ", sumsumGoals: " + sumsumGoals + ", ratio: " + (1.0*sumsumAss)/sumsumGoals);
	}*/
	
	// ======================
	// 4 TEST 4 TEST 4 TEST 4
	// ======================
	/*public static void main(String[] args) {
		
		Core base = new Core();
		
		FootballTeam[] teams = base.getAllFootballTeams();
		
		int sumsumSaves = 0;
		int sumsumGKSaves = 0;
		int sumsumDFSaves = 0;
		int sumsumMFSaves = 0;
		int sumsumFWSaves = 0;
		for (int j = 0; j < teams.length; j++) {
			FootballPlayer[] players = teams[j].getFootballPlayers(); 
			int sumSaves = 0;
			int sumGKSaves = 0;
			int sumDFSaves = 0;
			int sumMFSaves = 0;
			int sumFWSaves = 0;
			for (int i = 0; i < players.length; i++) {
				sumSaves += players[i].stats[0].getSaves();
				
				if (players[i].getPosition() == Position.GK)
					sumGKSaves += players[i].stats[0].getSaves();
				if (players[i].getPosition() == Position.DF)
					sumDFSaves += players[i].stats[0].getSaves();
				if (players[i].getPosition() == Position.MF)
					sumMFSaves += players[i].stats[0].getSaves();
				if (players[i].getPosition() == Position.FW)
					sumFWSaves += players[i].stats[0].getSaves();
			}
			
			sumsumSaves += sumSaves;
			sumsumGKSaves += sumGKSaves;
			sumsumDFSaves += sumDFSaves;
			sumsumMFSaves += sumMFSaves;
			sumsumFWSaves += sumFWSaves;
		}
		
		System.out.println("sumsumGKSaves: " + sumsumGKSaves + ", sumsumDFSaves: " + sumsumDFSaves + ", sumsumMFSaves: " + sumsumMFSaves + ", sumsumFWSaves: " + sumsumFWSaves);
		System.out.println("sumsumSaves: " + sumsumSaves + ", games played: 30, average saves per game: " + (1.0*sumsumSaves)/(10*30));
	}*/
	
	// ======================
	// 5 TEST 5 TEST 5 TEST 5
	// ======================
	/*public static void main(String[] args) {
		
		Core base = new Core();
		
		FootballTeam[] teams = base.getAllFootballTeams();
		
		LinkedList<Integer> gkSaves = new LinkedList<Integer>();
		LinkedList<Integer> gkMinutes = new LinkedList<Integer>();
		LinkedList<Double> gkTeamFactor = new LinkedList<Double>();
		
		for (int j = 0; j < teams.length; j++) {
			FootballPlayer[] players = teams[j].getFootballPlayers(); 

			for (int i = 0; i < players.length; i++) {
				if (players[i].getPosition() == Position.GK) {
					gkSaves.add(players[i].stats[0].getSaves());
					gkMinutes.add(players[i].stats[0].getMinutes());
					gkTeamFactor.add(base.getFootballTeam(players[i].getTeamName()).getFactor());
				}
			}
		}
		
		for (int i = 0; i < 1; i++) {
			System.out.print(gkSaves.get(i));
			System.out.println(gkMinutes.get(i));
			System.out.println(gkTeamFactor.get(i));
		}
		
		/*for (int i = 0; i < gkSaves.size(); i++) {
			System.out.print(1.0*gkSaves.get(i)/gkMinutes.get(i) + ", ");
		} System.out.println();
		System.out.println();
		for (int i = 0; i < gkSaves.size(); i++) {
			System.out.print(gkTeamFactor.get(i) + ", ");
		}
		System.out.println();*/
	
		/*for (int entity : gkSaves) {
			System.out.print(entity/30.0 + ",\t ");
		}*/
		/*System.out.println("\n\nminutes: ");
		for (int entity : gkMinutes) {
			System.out.print(entity + ",\t ");
		}
		System.out.println("\n\nratio: ");
		for (int i = 0; i < gkSaves.size(); i++) {
			System.out.print(1.0*gkSaves.get(i)/gkMinutes.get(i) + ", ");
		}*/
	
	// ======================
	// 6 TEST 6 TEST 6 TEST 6
	// ======================
	/*public static void main(String[] args) {
		
		Core base = new Core();
		
		FootballTeam[][][] schal = base.getScheduleForAllRounds();
		
		for (int u = 0; u < 18; u++) {
			base.simulateNextRound();
			for (int k = 0; k < 5; k++) {
				FootballTeam A = base.getFootballTeam(schal[u][k][0].getName());
				FootballTeam B = base.getFootballTeam(schal[u][k][1].getName());
				
				String[] AAfter = new String[11];
				int i = 0;
				for (FootballPlayer player : A.getSimulationTeam()) {
					AAfter[i] = player.getName() + "|m:" + player.stats[u+1].getMinutes() + "|a:" + player.stats[u+1].getAssists() + "|g:" +
				                 player.stats[u+1].getGoals() + "|gc:" + player.stats[u+1].getGoalsConceded() + "|og:" +
							     player.stats[u+1].getOwnGoals() + "|cs:" + player.stats[u+1].getCleanSheet() + "|yc:" +
				                 player.stats[u+1].getYellowCards() + "|rc:" + player.stats[u+1].getRedCards() + "|sc:" + player.getScore();
				    i++;
				}
				String[] BAfter = new String[11];
				i = 0;
				for (FootballPlayer player : B.getSimulationTeam()) {
					BAfter[i] = player.getName() + "|m:" + player.stats[u+1].getMinutes() + "|a:" + player.stats[u+1].getAssists() + "|g:" +
			                 player.stats[u+1].getGoals() + "|gc:" + player.stats[u+1].getGoalsConceded() + "|og:" +
						     player.stats[u+1].getOwnGoals() + "|cs:" + player.stats[u+1].getCleanSheet() + "|yc:" +
			                 player.stats[u+1].getYellowCards() + "|rc:" + player.stats[u+1].getRedCards() + "|sc:" + player.getScore();
				    i++;
				}
				
				System.out.println(base.getMatchResults(u*5+k).displayResults());
				FootballPlayer[] homeScoringPlayers = base.getMatchResults(u*5+k).getHomeScoringPlayers();
				FootballPlayer[] awayScoringPlayers = base.getMatchResults(u*5+k).getAwayScoringPlayers();
				for (int p = 0; p < homeScoringPlayers.length; p++) {
					System.out.print(homeScoringPlayers[p].getName() + ", ");
				} System.out.print(" - ");
				for (int p = 0; p < awayScoringPlayers.length; p++) {
					System.out.print(awayScoringPlayers[p].getName() + ", ");
				} System.out.println("\n");
				System.out.println("name|minutes|assists|goals|goalsConceded|ownGoals|cleanSheet|YC|RC|SC");
				for (int j = 0; j < 11; j++) {
					System.out.println(AAfter[j]);
				}System.out.println();
				for (int j = 0; j < 11; j++) {
					System.out.println(BAfter[j]);
				}
				System.out.println("\n=====================================\n");
			}
			System.out.println("\n=====================================");
			System.out.println("==|=====|=======|======|======|======");
			System.out.println("=====================================\n");
		}
	}*/
}
