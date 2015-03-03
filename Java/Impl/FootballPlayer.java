package Java.Impl;

public class FootballPlayer {
	
	private final String teamName;
	private final String name;
	
	public Statistics[] stats;
	
	private double 	pickProbability; //how likely is this player to be in final team squad.
	private int 	goalsConceded;
	private String 	teamFolder;
	private int 	marketValue;
	private int 	yellowCards;
	private String 	position;
	private String 	picturePath;
	private int		ownGoals;
	private int 	redCards;
	private int 	minutes;
	private int 	assists;
	private int 	goals;
	private int 	saves;
	
	//Constructor
	public FootballPlayer( String name, String teamName ){
		this.teamName = teamName;
		this.name 	  = name;
	}
	
	//Það þarf kannski að búa til klasa sem býr finnur út pickProbability ??
	
	
	public void setPicturePath( String picturePath ){
		this.picturePath = picturePath;
	}
	public void setPosition( String position ){
		this.position = position;
	}
	public void setGoalsConceded( int goalsConceded ){
		this.goalsConceded = goalsConceded;
	}
	public void setYellowCards( int yellowCards ){
		this.yellowCards = yellowCards;
	}
	public void setMarketValue( int marketValue ){
		this.marketValue  = marketValue;
	}
	public void setTeamFolder( String teamFolder ){
		this.teamFolder = teamFolder;
	}
	public void setOwnGoals( int ownGoals ){
		this.ownGoals = ownGoals;
	}
	public void setRedCards( int redCards ){
		this.redCards = redCards;
	}
	public void setMinutes( int minutes ){
		this.minutes = minutes;
	}
	public void setAssists( int assists ){
		this.assists = assists;
	}
	public void setGoals( int goals ){
		this.goals = goals;
	}
	public void setSaves( int saves ){
		this.saves = saves;
	}
	
	

	
	public double getPickProbability( ){
		return this.pickProbability;
	}
	public int getGoalsConceded( ){
		return this.goalsConceded;
	}
	public int getMarketValue( ){
		return this.marketValue;
	}
	public String getPicturePath( ){
		return this.picturePath;
	}
	public String getTeamFolder( ){
		return this.teamFolder;
	}
	public int getYellowCards( ){
		return this.yellowCards;
	}
	public String getTeamName( ){
		return this.teamName;
	}
	public String getPosition( ){
		return this.position;
	}
	public int getRedCards( ){
		return this.redCards;
	}
	public int getOwnGoals( ){
		return this.ownGoals;
	}
	public int getMinutes( ){
		return this.minutes;
	}
	public int getAssists( ){
		return this.assists;
	}
	public String getName( ){
		return this.name;
	}
	public int getGoals( ){
		return this.goals;
	}
	public int getSaves( ){
		return this.saves;
	}
	
	
}
