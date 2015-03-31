package trunk.Java;

public class Simulate {
	private static int roundNum;
	private static MatchResults results;
	
	public static MatchResults match(FootballTeam homeTeam, FootballTeam awayTeam, int roundNumber ){
		// Create results for this match
		MatchResults matchResults = new MatchResults( homeTeam, awayTeam );
		
		// Let's keep these variables in the global scope of this class
		// to enable easier calls for future functions within Simulate.java 
		roundNum = roundNumber;
		results = matchResults;
		
		// Determine homeGoals and awayGoals
		results.setHomeGoals(Random.homeGoals(homeTeam, awayTeam));
		results.setAwayGoals(Random.awayGoals(homeTeam, awayTeam));
		
		// Update the statistics for each FootballPlayer in this match
		giveGoals();         // -> Ready for testing
		giveYellowCards();   // -> Ready for testing	
		giveRedCards();	     // -> Ready for testing
		giveSaves();         // Not implemented yet
		giveGoalsConceded(); // -> Ready for testing
		giveMinutes();       // -> Ready for testing
		giveAssists();       // Not implemented yet
		giveCleanSheet();    // Not implemented yet
		giveOwnGoals();      // Not implemented yet
		
		// Finally, update each player's score
		for (FootballPlayer player : homeTeam.getSimulationTeam()) {
			player.scoreUpdate(roundNum);
		}
		for (FootballPlayer player : awayTeam.getSimulationTeam()) {
			player.scoreUpdate(roundNum);
		}
		
		return matchResults;
	}
	
	// ======
	// Givers
	// ======
	private static void giveGoals(){
		if (results.getHomeGoals() > 0) distributeGoalsWithin("homeTeam");
		if (results.getAwayGoals() > 0) distributeGoalsWithin("awayTeam");
	}
	
	private static void giveYellowCards() {
		int homeYCs = Random.yellowCards(); 
		int awayYCs = Random.yellowCards();
		
		if (homeYCs > 0) distributeYCsWithin("homeTeam", homeYCs);
		if (awayYCs > 0) distributeYCsWithin("awayTeam", awayYCs);
	}
	
	private static void giveRedCards() {
		int homeRCs = Random.redCards(); 
		int awayRCs = Random.redCards();
		
		if (homeRCs > 0) distributeRCsWithin("homeTeam", homeRCs);
		if (awayRCs > 0) distributeRCsWithin("awayTeam", awayRCs);
	}
	
	private static void giveSaves() {
	}
	
	private static void giveGoalsConceded() {		
		for (FootballPlayer player : results.getHomeTeam().getSimulationTeam()) {
			player.stats[roundNum+1].setGoalsConceded(results.getHomeGoals());
		}
		for (FootballPlayer player : results.getAwayTeam().getSimulationTeam()) {
			player.stats[roundNum+1].setGoalsConceded(results.getAwayGoals());
		}
	}
	
	private static void giveMinutes() {
		for (FootballPlayer player : results.getHomeTeam().getSimulationTeam()) {
			if (player.stats[roundNum+1].getYellowCards() < 2
					&& player.stats[roundNum+1].getRedCards() < 1) {
				player.stats[roundNum+1].setMinutes(90);
			} else {
				int minutes = (int)(Math.random()*91);
				player.stats[roundNum+1].setMinutes(minutes);
			}
		}
	}
	
	private static void giveAssists() {
	}
	
	private static void giveCleanSheet() {
	}
	
	private static void giveOwnGoals() {
	}
	
	// ============
	// Distributers
	// ============
	private static void distributeGoalsWithin(String team) {
		FootballPlayer[] specifiedTeam = null;
		int newGoals = 0;
		if (team.equals("homeTeam")) {
			specifiedTeam = results.getHomeTeam().getSimulationTeam();
			newGoals = results.getHomeGoals();
		}
		if (team.equals("awayTeam")) {
			specifiedTeam = results.getAwayTeam().getSimulationTeam();
			newGoals = results.getAwayGoals();
		}
		
		double chancesOfNewGoal[] = new double[specifiedTeam.length];
		
		// i)  Store indiviual goals for each player in oldGoalsOfPlayer[]
		// ii) Calculate the sum of i) and store in sum_oldGoalsOfAllPlayers
		int oldGoalsOfPlayer[]   = playerGoals(specifiedTeam);
		int sum_oldGoalsOfPlayer = sumOf(oldGoalsOfPlayer);
		
		// Use the sum to construct an array of chances. chances[0] is how
		// likely it is that the 0th TootballPlayer of specifiedTeam scores
		// one of the goals of this match
		for (int i = 0; i < specifiedTeam.length; i++){
			chancesOfNewGoal[i] =  (1.0*oldGoalsOfPlayer[i])/sum_oldGoalsOfPlayer;
		}
		
		// Finally, distribute all of the new goals to players of the specifiedTeam
		while (newGoals > 0 ){
			// Given the chances array, tell us which player actually scored that goal
			int iScored = Random.determineValue(chancesOfNewGoal);
			
			// Add that scorer to matchResults
			if (team.equals("homeTeam")) results.addHomeScorer(specifiedTeam[iScored]);
			if (team.equals("awayTeam")) results.addAwayScorer(specifiedTeam[iScored]);
			
			// Also add that goal to the FootballPlayers statistics
			specifiedTeam[iScored].stats[roundNum+1].incrGoalsBy(1);
			
			newGoals--;	
		}
	}
	
	private static void distributeYCsWithin(String team, int amountOfCards) {
		FootballPlayer[] specifiedTeam = null;
		if (team.equals("homeTeam")) {
			specifiedTeam = results.getHomeTeam().getSimulationTeam();
		}
		if (team.equals("awayTeam")) {
			specifiedTeam = results.getAwayTeam().getSimulationTeam();
		}
		
		double chancesOfNewYCs[] = new double[specifiedTeam.length];
		
		int oldYCsOfPlayer[]   = playerYellowCards(specifiedTeam);
		int sum_oldYCsOfPlayer = sumOf(oldYCsOfPlayer);
		
		for (int i = 0; i < specifiedTeam.length; i++){
			chancesOfNewYCs[i] =  (1.0*oldYCsOfPlayer[i])/sum_oldYCsOfPlayer;
		}
		
		// Distribute the yellow cards amongst players
		while (amountOfCards > 0) {
			// Player #playerIndex receives a yellow card
			int playerIndex = Random.determineValue(chancesOfNewYCs);
			
			specifiedTeam[playerIndex].stats[roundNum+1].incrYellowCardsBy(1);
			
			amountOfCards--;
		}
	}
	
	private static void distributeRCsWithin(String team, int amountOfCards) {
		FootballPlayer[] specifiedTeam = null;
		if (team.equals("homeTeam")) {
			specifiedTeam = results.getHomeTeam().getSimulationTeam();
		}
		if (team.equals("awayTeam")) {
			specifiedTeam = results.getAwayTeam().getSimulationTeam();
		}
		
		double chancesOfNewRCs[] = new double[specifiedTeam.length];
		
		int oldRCsOfPlayer[]   = playerRedCards(specifiedTeam);
		int sum_oldRCsOfPlayer = sumOf(oldRCsOfPlayer);
		
		for (int i = 0; i < specifiedTeam.length; i++){
			chancesOfNewRCs[i] =  (1.0*oldRCsOfPlayer[i])/sum_oldRCsOfPlayer;
		}
		
		// Distribute the yellow cards amongst players
		while (amountOfCards > 0) {
			// Player #playerIndex receives a red card
			int playerIndex = Random.determineValue(chancesOfNewRCs);
			
			specifiedTeam[playerIndex].stats[roundNum+1].incrRedCardsBy(1);
			
			amountOfCards--;
		}
	}
	
	// =============
	// Sum functions
	// =============
	private static int sumOf(int[] array) {
		int sum = 0;
		for (int num : array) {
			sum += num;
		}
		return sum;
	}
	
	private static int[] playerGoals(FootballPlayer[] players) {
		int playerGoals[] = new int[players.length];
		for (int i = 0; i < players.length; i++) {
			int thisPlayerGoals = 0;
			for (int j = 0; j <= roundNum; j++) {
				thisPlayerGoals += players[i].stats[j].getGoals();
			}
			playerGoals[i] = thisPlayerGoals;
		}
		return playerGoals;		
	}
	
	private static int[] playerYellowCards(FootballPlayer[] players) {
		int playerYellowCards[] = new int[players.length];
		for (int i = 0; i < players.length; i++) {
			int thisPlayerYellowCards = 0;
			for (int j = 0; j <= roundNum; j++) {
				thisPlayerYellowCards += players[i].stats[j].getYellowCards();
			}
			playerYellowCards[i] = thisPlayerYellowCards;
		}
		return playerYellowCards;		
	}
	
	private static int[] playerRedCards(FootballPlayer[] players) {
		int playerRedCards[] = new int[players.length];
		for (int i = 0; i < players.length; i++) {
			int thisPlayerRedCards = 0;
			for (int j = 0; j <= roundNum; j++) {
				thisPlayerRedCards += players[i].stats[j].getRedCards();
			}
			playerRedCards[i] = thisPlayerRedCards;
		}
		return playerRedCards;		
	}
}