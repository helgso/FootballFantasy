package trunk.Java;

public class Simulate {
	private static int roundNum;
	private static MatchResults results;
	private static FootballTeam home;
	private static FootballTeam away;
	
	public static MatchResults match(FootballTeam homeTeam, FootballTeam awayTeam, int roundNumber ){
		// Create results for this match
		MatchResults matchResults = new MatchResults( homeTeam, awayTeam );
		
		// Let's keep these variables in the global scope of this class
		// to enable easier calls for future functions within Simulate.java 
		roundNum = roundNumber;
		results = matchResults;
		home = homeTeam;
		away = awayTeam;
		
		// Determine homeGoals and awayGoals
		results.setHomeGoals(Random.homeGoals(homeTeam, awayTeam));
		results.setAwayGoals(Random.awayGoals(homeTeam, awayTeam));
		
		// Update the statistics for each FootballPlayer in this match
		giveGoals();         // -> Ready for testing
		giveYellowCards();   // -> Ready for testing	
		giveRedCards();	     // -> Ready for testing
		giveGoalsConceded(); // -> Ready for testing
		giveAssists();       // Not implemented yet
		giveOwnGoals();      // Not implemented yet
		giveCleanSheet();    // -> Ready for testing
		giveSaves();         // Not implemented yet
		giveMinutes();       // -> Ready for testing
		
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
	// Give goals to homeTeam and awayTeam simulation players
	private static void giveGoals(){
		if (results.getHomeGoals() > 0) distributeGoalsWithin("homeTeam");
		if (results.getAwayGoals() > 0) distributeGoalsWithin("awayTeam");
	}
	
	// Give yellow cards to homeTeam and awayTeam simulation players
	private static void giveYellowCards() {
		int homeYCs = Random.yellowCards(); 
		int awayYCs = Random.yellowCards();
		
		if (homeYCs > 0) distributeYCsWithin("homeTeam", homeYCs);
		if (awayYCs > 0) distributeYCsWithin("awayTeam", awayYCs);
	}
	
	// Give red cards to homeTeam and awayTeam simulation players
	private static void giveRedCards() {
		int homeRCs = Random.redCards(); 
		int awayRCs = Random.redCards();
		
		if (homeRCs > 0) distributeRCsWithin("homeTeam", homeRCs);
		if (awayRCs > 0) distributeRCsWithin("awayTeam", awayRCs);
	}
	
	// Give goals conceded to homeTeam and awayTeam simulation players
	private static void giveGoalsConceded() {		
		for (FootballPlayer player : home.getSimulationTeam()) {
			player.stats[roundNum].setGoalsConceded(results.getHomeGoals());
		}
		for (FootballPlayer player : away.getSimulationTeam()) {
			player.stats[roundNum].setGoalsConceded(results.getAwayGoals());
		}
	}
	
	// Give assists to homeTeam and awayTeam simulation players
	private static void giveAssists() {
	}
	
	// Give ownGoals to homeTeam and awayTeam simulation players
	private static void giveOwnGoals() {
	}
	
	// Give clean sheets to homeTeam and awayTeam simulation players
	private static void giveCleanSheet() {
		if (results.getHomeGoals() == 0) {
			for (FootballPlayer player : home.getSimulationTeam()) {
				player.stats[roundNum].setCleanSheet(true);
			}
		}
		if (results.getAwayGoals() == 0) {
			for (FootballPlayer player : away.getSimulationTeam()) {
				player.stats[roundNum].setCleanSheet(true);
			}
		}
	}
	
	// Give saves to homeTeam and awayTeam simulation players
	private static void giveSaves() {
	}
	
	// Give minutes to homeTeam and awayTeam simulation players
	private static void giveMinutes() {
		distributeMinutesWithin(home);
		distributeMinutesWithin(away);
	}
	
	// ============
	// Distributers
	// ============
	// -> team is either "homeTeam" or "awayTeam". That will be the "specified team".
	// 		Distribute the goals randomly within the specified team. However, make it
	// more likely that a player that has scored in the past will score one of
	// these new goals
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
			specifiedTeam[iScored].stats[roundNum].incrGoalsBy(1);
			
			newGoals--;	
		}
	}
	
	// -> team is either "homeTeam" or "awayTeam". That will be the "specified team".
	// -> amountOfCards is the amount of yellow cards to be distributed.
	// 		Distribute the cards randomly within the specified team. However, make it
	// more likely that a player that has received a yellow card in the past will
	// get on of these new yellow cards
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
			
			specifiedTeam[playerIndex].stats[roundNum].incrYellowCardsBy(1);
			
			amountOfCards--;
		}
	}
	
	// -> team is either "homeTeam" or "awayTeam". That will be the "specified team".
	// -> amountOfCards is the amount of red cards to be distributed.
	// 		Distribute the cards randomly within the specified team. However, make it
	// more likely that a player that has received a red card in the past will
	// get on of these new red cards
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
			
			specifiedTeam[playerIndex].stats[roundNum].incrRedCardsBy(1);
			
			amountOfCards--;
		}
	}
	
	// pre: giveYellowCards and giveRedCards have to be called.
	// -> specifiedTeam is either "homeTeam" or "awayTeam".
	// 		A player that has received 2 or more yellow cards
	// or one red card will only receive a random amount of minutes
	// on the interval [0;90]. The rest will receive 90 minutes.
	private static void distributeMinutesWithin(FootballTeam specifiedTeam) {
		for (FootballPlayer player : specifiedTeam.getSimulationTeam()) {
			if (player.stats[roundNum].getYellowCards() < 2
					&& player.stats[roundNum].getRedCards() < 1) {
				player.stats[roundNum].setMinutes(90);
			} else {
				int minutes = (int)(Math.random()*91);
				player.stats[roundNum].setMinutes(minutes);
			}
		}
	}
	
	// =============
	// Sum functions
	// =============
	// Returns the sum of array
	private static int sumOf(int[] array) {
		int sum = 0;
		for (int num : array) {
			sum += num;
		}
		return sum;
	}
	
	// Returns an array of goal amounts for each
	// player in players up until this round
	private static int[] playerGoals(FootballPlayer[] players) {
		int playerGoals[] = new int[players.length];
		for (int i = 0; i < players.length; i++) {
			int thisPlayerGoals = 0;
			for (int j = 0; j < roundNum; j++) {
				thisPlayerGoals += players[i].stats[j].getGoals();
			}
			playerGoals[i] = thisPlayerGoals;
		}
		return playerGoals;		
	}
	
	// Returns an array of yellow card amounts for each
	// player in players up until this round
	private static int[] playerYellowCards(FootballPlayer[] players) {
		int playerYellowCards[] = new int[players.length];
		for (int i = 0; i < players.length; i++) {
			int thisPlayerYellowCards = 0;
			for (int j = 0; j < roundNum; j++) {
				thisPlayerYellowCards += players[i].stats[j].getYellowCards();
			}
			playerYellowCards[i] = thisPlayerYellowCards;
		}
		return playerYellowCards;		
	}
	
	// Returns an array of red card amounts for each
	// player in players up until this round
	private static int[] playerRedCards(FootballPlayer[] players) {
		int playerRedCards[] = new int[players.length];
		for (int i = 0; i < players.length; i++) {
			int thisPlayerRedCards = 0;
			for (int j = 0; j < roundNum; j++) {
				thisPlayerRedCards += players[i].stats[j].getRedCards();
			}
			playerRedCards[i] = thisPlayerRedCards;
		}
		return playerRedCards;		
	}
}