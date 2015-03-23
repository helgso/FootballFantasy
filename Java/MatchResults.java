package trunk.Java;

import java.util.LinkedList;

public class MatchResults {
	
	private String matchName;
	private int homeGoals;
	private int awayGoals;
	private FootballTeam homeTeam;
	private FootballTeam awayTeam;
	private LinkedList<FootballPlayer> homeScoringPlayers;
	private LinkedList<FootballPlayer> awayScoringPlayers;
	
	public MatchResults( FootballTeam homeTeam, FootballTeam awayTeam ){
		this.matchName = homeTeam.getName() + " - " + awayTeam.getName();
		this.homeGoals = 0;
		this.awayGoals = 0;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.homeScoringPlayers = new LinkedList<FootballPlayer>();
		this.awayScoringPlayers = new LinkedList<FootballPlayer>();
	}
	
	public int getHomeGoals( ){
		return this.homeGoals;
	}
	
	public int getAwayGoals( ){ 
		return this.awayGoals;
	}
	
	public FootballTeam getHomeTeam( ){		
		return this.homeTeam;
	}
	
	public FootballTeam getAwayTeam( ){
		return this.awayTeam;
	}
	
	//
	//Put every home scoring player into 
	//one array and return the player
	//
	public FootballPlayer[] getHomeScoringPlayers( ){ 
		int size = this.homeScoringPlayers.size();
		FootballPlayer[] player = new FootballPlayer[ size ];
		for(int i=0;  i<size; i++){
			player[i] = this.homeScoringPlayers.get(i);
		}
		return player;
	}
	
	//
	//Put every away scoring player into 
	//one array and return the player
	//
	public FootballPlayer[] getAwayScoringPlayers( ){
		int listSize = this.awayScoringPlayers.size();
		FootballPlayer[] player = new FootballPlayer[ listSize ];
		for(int i=0;  i<listSize; i++){
			player[i] = this.awayScoringPlayers.get(i);
		}
		return player;
	}
	
	public String getMatchName( ){
		return this.matchName;
	}	
	
	public void addHomeScorer( FootballPlayer scorer ){
		//this.homeGoals++;
		this.homeScoringPlayers.add(scorer);
	}
	
	public void addAwayScorer( FootballPlayer scorer ){
		//this.awayGoals++;
		this.awayScoringPlayers.add(scorer);
	}
	
	public void setHomeGoals(int amount) {
		this.homeGoals = amount;
	}
	
	public void setAwayGoals(int amount) {
		this.awayGoals = amount;
	}
	
	public String displayResults( ){
		String homeTeamName = this.homeTeam.getName();
		String awayTeamName = this.awayTeam.getName();
		String space  = " ";
		String dash   = "-";
		String center = space + dash + space;
		return homeTeamName + space + homeGoals + center + awayGoals + space + awayTeamName;
	}
}

