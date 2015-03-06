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
	//       {"Arsenal - Chelsea", "Stoke - Southampton", ...}
	public String[] getNextRoundSchedule() {
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
	public String[][] getTotalSchedule() {
		FootballTeam[][][] allRoundsSchedule = schedule.getTotalSchedule();
		
		String[] totalSchedule = new String[90];
		for (int i = 0; i < 90; i++) {
			String homeTeam = allRoundsSchedule[i][0].getName();
			String awayTeam = allRoundsSchedule[i][1].getName();
			totalSchedule[i] = homeTeam + " - " + awayTeam;
		}
		
		return totalSchedule;
	}
	
	public void simulateNextRound() {
		
	}
	
	public FootballPlayer[] getAllFootballPlayers() {
		return null;
	}
	
	public MatchResults getMatchResults(int matchNumber) {
		return null;
	}
	
}
