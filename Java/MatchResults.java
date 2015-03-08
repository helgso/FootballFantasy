package trunk.Java;

import java.util.LinkedList;

public class MatchResults {
	
	private String matchName;
	private int homeTeamGoals;
	private int awayTeamGoals;
	private FootballTeam homeTeam;
	private FootballTeam awayTeam;
	private LinkedList<FootballPlayer> homeScoringPlayers;
	private LinkedList<FootballPlayer> awayScoringPlayers;
	
	public MatchResults( FootballTeam homeTeam, FootballTeam awayTeam ){
		this.matchName = homeTeam.getName() + " - " + awayTeam.getName();
		this.homeTeamGoals = 0;
		this.awayTeamGoals = 0;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;		
		this.homeScoringPlayers = new LinkedList<FootballPlayer>();
		this.awayScoringPlayers = new LinkedList<FootballPlayer>();
	}
	
	public int getHomeGoals( ){
		return this.homeTeamGoals;
	}
	
	public int getAwayGoals( ){ 
		return this.awayTeamGoals;
	}
	
	public FootballTeam getHomeTeam( ){		
		return this.homeTeam;
	}
	
	public FootballTeam getAwayTeam( ){
		return this.awayTeam;
	}
	
	//
	//Put every home scoring player into 
	//one array and return it
	//
	public FootballPlayer[] getHomeScoringPlayers( ){ 
		int size = this.homeScoringPlayers.size();
		FootballPlayer[] player = new FootballPlayer[ size ];
		for(int i=0;  i<size; i++){
			player[i] = this.homeScoringPlayers.removeFirst();
		}
		return player;
	}
	
	//
	//Put every away scoring player into 
	//one array and return it
	//
	public FootballPlayer[] getAwayScoringPlayers( ){
		int listSize = this.awayScoringPlayers.size();
		FootballPlayer[] player = new FootballPlayer[ listSize ];
		for(int i=0;  i<listSize; i++){
			player[i] = this.awayScoringPlayers.removeFirst();
		}
		return player;
	}
	
	public String getMatchName( ){
		return this.matchName;
	}	
	
	public void increaseGoalHomeTeam( FootballPlayer scorer ){
		this.homeTeamGoals++;
		this.homeScoringPlayers.add(scorer);
	}
	
	public void increaseGoalAwayTeam( FootballPlayer scorer ){
		this.awayTeamGoals++;
		this.awayScoringPlayers.add(scorer);
	}
	
	public void setHomeTeamGoals(int amount) {
		this.homeTeamGoals = amount;
	}
	
	public void setAwayTeamGoals(int amount) {
		this.awayTeamGoals = amount;
	}
	
	public String displayResults( ){
		String homeTeamName = this.homeTeam.getName();
		String awayTeamName = this.awayTeam.getName();
		int homeGoals = this.homeTeamGoals;
		int awayGoals = this.awayTeamGoals;
		String space  = " ";
		String dash   = "-";
		String center = space + dash + space;
		return homeTeamName + space + homeGoals + center + awayGoals + space + awayTeamName;
	}
}

