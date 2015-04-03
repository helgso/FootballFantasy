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
		
		// Update the statistics for each FootballPlayer in this match
		giveGoalsAndOwnGoals();  // -> Ready for testing
		giveYellowAndRedCards(); // -> Ready for testing
		giveGoalsConceded();     // -> Ready for testing
		giveAssists();           // -> Ready for testing
		giveCleanSheet();        // Tested -> OK
		giveSaves();             // -> Ready for testing
		giveMinutes();           // Tested -> OK
		
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
	// Give goals to home and away simulation players
	private static void giveGoalsAndOwnGoals(){
		int homeGoals = Random.homeGoals(home, away);
		int awayGoals = Random.awayGoals(home, away);
		
		int homeOwnGoals = 0;
		int awayOwnGoals = 0;
		
		// It is 2% likely that any scored goal is an own goal
		double[] ownGoalProbs = {0.98, 0.02};
		for (int i = 0; i < homeGoals; i++) {
			// If Random picks the 0.02 chances, it returns 1 which
			// increments homeOwnGoals by one
			homeOwnGoals += Random.determineValue(ownGoalProbs);
		}
		for (int i = 0; i < awayGoals; i++) {
			awayOwnGoals += Random.determineValue(ownGoalProbs);
		}
		
		// Substract own goals from actual goals
		awayGoals -= homeOwnGoals;
		homeGoals -= awayOwnGoals;
		
		if (homeOwnGoals > 0) distributeOwnGoalsWithin(home, homeOwnGoals);
		if (awayOwnGoals > 0) distributeOwnGoalsWithin(away, awayOwnGoals);
		
		if (homeGoals > 0) distributeGoalsWithin(home, homeGoals);
		if (awayGoals > 0) distributeGoalsWithin(away, awayGoals);
	}
	
	// Give yellow and red cards to home and away simulation players
	private static void giveYellowAndRedCards() {
		int homeRCs = Random.redCards(); 
		int awayRCs = Random.redCards();
		
		// RCs are substracted from YCs because if a player
		// receives a RC he has already received a YC
		int homeYCs = Random.yellowCards() - homeRCs;
		int awayYCs = Random.yellowCards() - awayRCs;
		
		if (homeRCs > 0) distributeRCsWithin(home, homeRCs);
		if (awayRCs > 0) distributeRCsWithin(away, awayRCs);
		
		if (homeYCs > 0) distributeYCsWithin(home, homeYCs);
		if (awayYCs > 0) distributeYCsWithin(away, awayYCs);
	}
	
	// Give goals conceded to home and away simulation players
	private static void giveGoalsConceded() {		
		for (FootballPlayer player : home.getSimulationTeam()) {
			player.stats[roundNum].setGoalsConceded(results.getHomeGoals());
		}
		for (FootballPlayer player : away.getSimulationTeam()) {
			player.stats[roundNum].setGoalsConceded(results.getAwayGoals());
		}
	}
	
	// Give assists to home and away simulation players
	private static void giveAssists() {
		int homeGoals = results.getHomeGoals();
		int awayGoals = results.getAwayGoals();
		
		double[] probsOfAssist = {0.0831509846827133,  // It's not an assist
				                  0.9168490153172867}; // It's an assist!
		
		// Let's calculate how many assists were made in both teams
		int homeAssists = 0;
		for (int i = 0; i < homeGoals; i++) {
			homeAssists += Random.determineValue(probsOfAssist);
		}
		int awayAssists = 0;
		for (int i = 0; i < awayGoals; i++) {
			awayAssists += Random.determineValue(probsOfAssist);
		}
		
		if (homeAssists > 0) distributeAssistsWithin(home, homeAssists);
		if (awayAssists > 0) distributeAssistsWithin(away, awayAssists);
	}
	
	// Give clean sheets to home and away simulation players
	private static void giveCleanSheet() {
		if (results.getAwayGoals() == 0) {
			for (FootballPlayer player : home.getSimulationTeam()) {
				player.stats[roundNum].setCleanSheet(true);
			}
		}
		if (results.getHomeGoals() == 0) {
			for (FootballPlayer player : away.getSimulationTeam()) {
				player.stats[roundNum].setCleanSheet(true);
			}
		}
	}
	
	// Give saves to home and away simulation goalkeepers
	// Apparently, no one else gets saves in the English Premier league
	private static void giveSaves() {
		// According to Skyrsla.pdf, the average of saves each match is 2.63 saves
		// So we try to make the probs array in such a way that the numbers will
		// generally have the average of 2.63
		
		// Probability of 0, 1, 2, 3, 4, 5 saves
		double[] probs = {0.15, 0.10, 0.15, 0.27, 0.23, 0.10};
		
		// When the probs above are tested for 1 million iterations, the average
		// of the produced random numbers is 2.628925. Looks sufficient.
		
		int homeGKSaves = Random.determineValue(probs);
		int awayGKSaves = Random.determineValue(probs);
		
		// Let's find the goalkeeper of the home team
		for (FootballPlayer player : home.getSimulationTeam()) {
			if (player.getPosition() == Position.GK) {
				// and assign the homeGKSaves to him
				player.stats[roundNum].setSaves(homeGKSaves);
				break;
			}
		}
		
		// Let's find the goalkeeper of the home team
		for (FootballPlayer player : away.getSimulationTeam()) {
			if (player.getPosition() == Position.GK) {
				// and assign the awayGKSaves to him
				player.stats[roundNum].setSaves(awayGKSaves);
				break;
			}
		}
	}
	
	// Give minutes to home and away simulation players
	private static void giveMinutes() {
		distributeMinutesWithin(home);
		distributeMinutesWithin(away);
	}
	
	// ============
	// Distributers
	// ============
	// -> team is either home or away.
	// -> amountOfGoals is the amount of goals to be distributed within within team.
	// 		Distributes the goals randomly within the specified team. However, makes it
	// more likely that a player that has scored in the past will score one of
	// these new goals
	private static void distributeGoalsWithin(FootballTeam team, int amountOfGoals) {
		FootballPlayer[] specifiedTeam = team.getSimulationTeam();
		
		// i)  Store indiviual goals for each player in initialGoalsOfPlayers[]
		// ii) Calculate the sum of i) and store in sum_initialGoalsOfPlayers
		int initialGoalsOfPlayers[]   = playerGoals(specifiedTeam);
		int sum_initialGoalsOfPlayers = sumOf(initialGoalsOfPlayers);
		
		// Use the sum to construct an array of chances. chances[0] is how
		// likely it is that the 0th TootballPlayer of specifiedTeam scores
		// one of the goals of this match and so on
		double chancesOfNewGoal[] = new double[specifiedTeam.length];
		for (int i = 0; i < specifiedTeam.length; i++){
			chancesOfNewGoal[i] =  (1.0*initialGoalsOfPlayers[i])/sum_initialGoalsOfPlayers;
		}
		
		// Finally, distribute all of the new goals to players of the specifiedTeam
		while (amountOfGoals > 0) {
			// Given the chances array, tell us which player actually scored that goal
			int thisManScored = Random.determineValue(chancesOfNewGoal);
			
			// Add that scorer to matchResults, incrementing that team's goals
			if (team == home) results.addHomeScorer(specifiedTeam[thisManScored]);
			if (team == away) results.addAwayScorer(specifiedTeam[thisManScored]);
			
			// Also add that goal to the FootballPlayer's own statistics
			specifiedTeam[thisManScored].stats[roundNum].incrGoalsBy(1);
			
			amountOfGoals--;	
		}
	}
		
	// -> team is either home or away.
	// -> amountOfOwnGoals is the amount of own goals to be distributed within team.
	// 		Distributes the own goals randomly within the specified team.
	private static void distributeOwnGoalsWithin(FootballTeam team, int amountOfOwnGoals) {
		FootballPlayer[] specifiedTeam = team.getSimulationTeam();
		
		// Let's collect all the defenders and the Midfielders of this team
		FootballPlayer[] DFs = new FootballPlayer[4]; int DFsIndex = 0;
		FootballPlayer[] MFs = new FootballPlayer[4]; int MFsIndex = 0;
		FootballPlayer[][] players = {DFs, MFs};
		
		for (FootballPlayer player : specifiedTeam) {
			if (player.getPosition() == Position.DF) {
				DFs[DFsIndex] = player;
				DFsIndex++;
			}
			if (player.getPosition() == Position.MF) {
				MFs[MFsIndex] = player;
				MFsIndex++;
			}
		}
		
		// Distribute the own goals between those 8 players
		while (amountOfOwnGoals > 0) {
			// Make it 80% likely that a defender scored the
			// ownGoal and 20% likely that a midfielder did
			int whichTypeScored = Random.determineValue(new double[] {0.8, 0.2});
			int whichPlayerScored = (int)(Math.random()*4);
			
			FootballPlayer itsHim = players[whichTypeScored][whichPlayerScored];
			
			itsHim.stats[roundNum].incrOwnGoalsBy(1);
			
			if (team == home) results.addAwayScorer(itsHim);
			if (team == away) results.addHomeScorer(itsHim);
			
			amountOfOwnGoals--;
		}
	}
	
	// -> team is either home or away.
	// -> amountOfRCs is the amount of red cards to be distributed within team.
	// 		Distributes the cards randomly within the specified team. However, makes it
	// more likely that a player that has received a red card in the past will
	// get on of these new red cards
	private static void distributeRCsWithin(FootballTeam team, int amountOfRCs) {
		FootballPlayer[] specifiedTeam = team.getSimulationTeam();
		
		int initialRCsPlayers[]   = playerRedCards(specifiedTeam);
		int sum_initialRCsPlayers = sumOf(initialRCsPlayers);
		
		double chancesOfaRC[] = new double[specifiedTeam.length];
		for (int i = 0; i < specifiedTeam.length; i++){
			chancesOfaRC[i] =  (1.0*initialRCsPlayers[i])/sum_initialRCsPlayers;
		}
		
		// Distribute the red cards amongst players
		while (amountOfRCs > 0) {
			// Player #playerIndex receives a red card
			int playerIndex = Random.determineValue(chancesOfaRC);
			
			// A player can only receive a red card once. Also, since he receives
			// the red card, he has also received a yellow card in the past
			if (specifiedTeam[playerIndex].stats[roundNum].getRedCards() != 1) {
				
				specifiedTeam[playerIndex].stats[roundNum].setRedCards(1);
				specifiedTeam[playerIndex].stats[roundNum].setYellowCards(1);
				amountOfRCs--;
			}
		}
	}
	
	// -> team is either home or away.
	// -> amountOfYCs is the amount of yellow cards to be distributed within team.
	// 		Distributes the cards randomly within the specified team. However, makes it
	// more likely that a player that has received a yellow card in the past will
	// get on of these new yellow cards
	private static void distributeYCsWithin(FootballTeam team, int amountOfYCs) {
		FootballPlayer[] specifiedTeam = team.getSimulationTeam();
		
		int initialYCsPlayers[]   = playerYellowCards(specifiedTeam);
		int sum_initialYCsPlayers = sumOf(initialYCsPlayers);
		
		double chancesOfaYC[] = new double[specifiedTeam.length];
		for (int i = 0; i < specifiedTeam.length; i++){
			chancesOfaYC[i] =  (1.0*initialYCsPlayers[i])/sum_initialYCsPlayers;
		}
		
		// Distribute the yellow cards amongst players
		while (amountOfYCs > 0) {
			// Player #playerIndex receives a yellow card
			int playerIndex = Random.determineValue(chancesOfaYC);
			
			// if he hasn't received one already
			if (specifiedTeam[playerIndex].stats[roundNum].getYellowCards() == 0) {
				
				specifiedTeam[playerIndex].stats[roundNum].setYellowCards(1);
				amountOfYCs--;
			}
		}
	}
	
	// pre: giveYellowAndRedCards is called.
	// -> team is either home or away.
	// 		A player that has received a red card
	// will only receive a random amount of minutes
	// on the interval [0;90]. The rest will receive 90 minutes.
	private static void distributeMinutesWithin(FootballTeam team) {
		for (FootballPlayer player : team.getSimulationTeam()) {
			if (player.stats[roundNum].getRedCards() != 1) {
				player.stats[roundNum].setMinutes(90);
			} else {
				int minutes = (int)(Math.random()*91);
				player.stats[roundNum].setMinutes(minutes);
			}
		}
	}
	
	// -> team is either home or away.
	// -> amountOfAssists is the amount of assists to be distributed within team.
	// 		Distributes the assists randomly within the specified team.
	private static void distributeAssistsWithin(FootballTeam team, int amountOfAssists) {
		FootballPlayer[] specifiedTeam = team.getSimulationTeam();
		
		// Probability of assists from:
		double[] probsOfAssist = {//0,                 // a goalkeeper
				                  0.1479713603818616,  // a defender
				                  0.6491646778042959,  // a midfielder
				                  0.2028639618138425}; // a forwarder
		
		// Let's collect the defenders, midfielders and forwarders of team
		FootballPlayer[] DFs = new FootballPlayer[4]; int DFsIndex = 0;
		FootballPlayer[] MFs = new FootballPlayer[4]; int MFsIndex = 0;
		FootballPlayer[] FWs = new FootballPlayer[2]; int FWsIndex = 0;
		FootballPlayer[][] players = {DFs, MFs, FWs};
		
		for (FootballPlayer player : specifiedTeam) {
			if (player.getPosition() == Position.DF) {
				DFs[DFsIndex] = player;
				DFsIndex++;
			}
			if (player.getPosition() == Position.MF) {
				MFs[MFsIndex] = player;
				MFsIndex++;
			}
			if (player.getPosition() == Position.FW) {
				FWs[FWsIndex] = player;
				FWsIndex++;
			}
		}
		
		// Distribute the assists within those 10 players
		while (amountOfAssists > 0) {
			int whichTypeAssisted = Random.determineValue(probsOfAssist);
			
			int whichPlayerAssisted = 0;
			if (whichTypeAssisted == 2) {
				whichPlayerAssisted = (int)(Math.random()*2);
			}
			else {
				whichPlayerAssisted = (int)(Math.random()*4);
			}
			
			FootballPlayer itsHim = players[whichTypeAssisted][whichPlayerAssisted];
			
			itsHim.stats[roundNum].incrAssistsBy(1);
			
			amountOfAssists--;
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
	
	// Returns an array of initial goal amounts
	// for each player in players from stats[0]
	private static int[] playerGoals(FootballPlayer[] players) {
		int playerGoals[] = new int[players.length];
		for (int i = 0; i < players.length; i++) {
			playerGoals[i] = players[i].stats[0].getGoals();
		}
		return playerGoals;		
	}
	
	// Returns an array of initial yellow cards amounts
	// for each player in players from stats[0]
	private static int[] playerYellowCards(FootballPlayer[] players) {
		int playerYellowCards[] = new int[players.length];
		for (int i = 0; i < players.length; i++) {
			playerYellowCards[i] = players[i].stats[0].getYellowCards();
		}
		return playerYellowCards;		
	}
	
	// Returns an array of initial red cards amounts
	// for each player in players from stats[0]
	private static int[] playerRedCards(FootballPlayer[] players) {
		int playerRedCards[] = new int[players.length];
		for (int i = 0; i < players.length; i++) {
			playerRedCards[i] = players[i].stats[0].getRedCards();
		}
		return playerRedCards;		
	}
}