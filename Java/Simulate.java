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
		
		// Update the statistics for each FootballPlayer
		giveGoals();
		giveYellowCards();	
		giveRedCards();	
		giveSaves();
		giveGoalsConceded();
		giveMinutes();
		giveAssists();
		
		return matchResults;
	}
	
	private static void giveGoals(){
		if (results.getHomeGoals() > 0) distributeGoalsWithin("homeTeam");
		if (results.getAwayGoals() > 0) distributeGoalsWithin("awayTeam");
	}
	
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
		int sum_oldGoalsOfAllPlayers = 0;
		int oldGoalsOfPlayer[] = new int[specifiedTeam.length];
		for (int i = 0; i < specifiedTeam.length; i++) {
			int sum_oldGoalsThisPlayer = 0;
			for (int j = 0; j <= roundNum; j++) {
				sum_oldGoalsThisPlayer += specifiedTeam[i].stats[j].getGoals();
			}
			oldGoalsOfPlayer[i] = sum_oldGoalsThisPlayer;
			sum_oldGoalsOfAllPlayers += sum_oldGoalsThisPlayer;
		}
		
		// Use the sum to construct an array of chances. chances[0] is how
		// likely it is that the 0th TootballPlayer of specifiedTeam scores
		// one of the goals of this match
		for (int i = 0; i < specifiedTeam.length; i++){
			chancesOfNewGoal[i] =  (1.0*oldGoalsOfPlayer[i])/sum_oldGoalsOfAllPlayers;
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

	private static void giveYellowCards() {
	}

	private static void giveRedCards() {
	}
	
	private static void giveSaves() {
	}
	
	private static void giveGoalsConceded() {
	}
	
	private static void giveMinutes() {
	}
	
	private static void giveAssists() {
	}
}