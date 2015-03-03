package Java.Impl;

import java.util.LinkedList;

public class MatchResults {
	
	private String matchName;
	private String homeTeamName;
	private String awayTeamName;
	private int homeGoals;
	private int awayGoals;
	
	private LinkedList<FootballPlayer> homeScoringPlayers;
	private LinkedList<FootballPlayer> awayScoringPlayers;
	
	public MatchResults( String homeTeamName, String awayTeamName ){
		this.homeScoringPlayers = new LinkedList<FootballPlayer>();
		this.awayScoringPlayers = new LinkedList<FootballPlayer>();
		this.setHomeTeamName( homeTeamName );
		this.setAwayTeamName( awayTeamName );
		this.setHomeGoals(0);
		this.setAwayGoals(0);
	}

	//ÞETTA FALL ER KALLAÐ Í RESTINA Í LEIK Í MATCHSIMULATION
	public void matchFinish(){
		
		
		this.setMatchName( );		
	}
	
	
	//
	// scorer scores for home team, scorer put into scorer list.
	//
	public void increaseGoalHomeTeam( FootballPlayer scorer ){
		this.homeScoringPlayers.add( scorer );
		this.homeGoals++;
	}
	
	//
	// scorer scores for away team, scorer put into scorer list.
	//
	public void increaseGoalAwayTeam( FootballPlayer scorer ){		
		this.awayScoringPlayers.add( scorer );
		this.awayGoals++;
	}
	
	//
	//Put every home scoring player into 
	//one array and return the array
	// array[0] is the first football player to
	// score for away team
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
	//one array and return the array
	// array[0] is the first football player to
	// score for away team
	//
	public FootballPlayer[] getAwayScoringPlayers( ){
		int listSize = this.awayScoringPlayers.size();
		FootballPlayer[] player = new FootballPlayer[ listSize ];
		for(int i=0;  i<listSize; i++){
			player[i] = this.awayScoringPlayers.removeFirst();
		}
		return player;
	}
	

	
	//ER EKKI VISS HVAÐ Á AÐ GERA HÉR
	public void displayResults( ){
		
	}

	
	
///////////////////
	//SET METHODS
///////////////////

	//homeTeamName and awayTeamName is put together into String where
	//goals number for each team follows their team, format: 
	//	"homeTeamName [number of home goals] - [number of away goals] awayTeamName" 
	//
	private void setMatchName( ){
		String homeGoals   = Integer.toString( this.getHomeGoals() );
		String awayGoals   = Integer.toString( this.getAwayGoals() );
		String space 	   = " ";
		String connectSign = "-";
		String center 	= space     + connectSign + space;
		String homeTeam = homeTeamName + space  + homeGoals;
		String awayTeam = awayGoals    + space  + awayTeamName;
		this.matchName  = homeTeam     + center + awayTeam;
	}
	
	private void setHomeGoals( int goals ){
		this.homeGoals = goals;
	}	
	private void setAwayGoals( int goals ){
		this.awayGoals = goals;
	}
	private void setHomeTeamName(String homeTeamName) {
		this.homeTeamName = homeTeamName;
	}
	private void setAwayTeamName(String awayTeamName) {
		this.awayTeamName = awayTeamName;
	}

	
///////////////////
	//GET METHODS
///////////////////
	public int getHomeGoals( ){
		return this.homeGoals;		
	}
	public int getAwayGoals( ){ 
		return this.awayGoals;
	}

	public String getHomeTeamName() {
		return this.homeTeamName;
	}
	public String getAwayTeamName() {
		return this.awayTeamName;
	}
	
	public String getMatchName( ){
		return this.matchName;
	}
	
}
