package Java;

import java.util.LinkedList;

public class MatchResults {
	
	private String matchName;
	private int homeGoals;
	private int awayGoals;
	
	private LinkedList<FootballPlayer> homeScoringPlayers;
	private LinkedList<FootballPlayer> awayScoringPlayers;
	
	public MatchResults( String homeTeamName, String awayTeamName ){
		//this.homeScoringPlayers = new LinkedList();
		
		
		//Kannski gera eitthvað hér ? enda svo á að gefa stig og segja
		//hvað leikurin fór.
		
		
		this.homeScoringPlayers.add(new FootballPlayer("Gerrard", "Liverpool"));
		this.awayScoringPlayers.add(new FootballPlayer("Gerrard", "Liverpool"));
		this.setHomeGoals(0);
		this.setAwayGoals(0);
		this.setMatchName( homeTeamName, awayTeamName );
	}
	public void increaseGoalHomeTeam( FootballPlayer[] scorer ){
		
	}
	public void increaseGoalAwayTeam( FootballPlayer[] scorer ){
		
	}
	public int getHomeGoals( ){
		return this.homeGoals;		
	}
	public int getAwayGoals( ){ 
		return this.awayGoals;
	}
	public String getHomeTeam( ){		
		return "test getHomeTeam metod:MatchResult";
	}
	public String getAwayTeam( ){
		return "test getAwayTeam metod:MatchResult";
	}

	private void setMatchName( String homeTeamName, String awayTeamName ){
		String homeGoals   = Integer.toString( this.getHomeGoals() );
		String awayGoals   = Integer.toString( this.getAwayGoals() );
		String space 	   = " ";
		String connectSign = "-";
		String center 	   = space + connectSign + space;
		this.matchName = homeTeamName + space + homeGoals + center + awayGoals + space + awayTeamName;
	}
	
	private void setHomeGoals( int goals ){
		this.homeGoals = goals;
	}

	private void setAwayGoals( int goals ){
		this.awayGoals = goals;
	}
	
	/*public FootballPlayers[] getHomeScoringPlayers( ){
		LinkedList footballPayers = new LinkedList(); 
		return footballPlayers;
	}
	public LinkedList getAwayScoringPlayers( ){
		
	}*/
	public String getMatchName( ){
		return this.matchName;
	}
	public void displayResults( ){
		
	}
	
	
}
