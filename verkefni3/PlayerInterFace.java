package trunk.verkefni3;

import trunk.Java.Position;
import trunk.Java.Statistics;

public interface PlayerInterFace {
	public void updateFootballPlayer( );
	
	//
	//SET METHODS
	//
	
	public void setPicturePath( String picturePath );
	public void setPickProbability( double pickProbability );
	public void setGoalsConceded( int goalsConceded );
	public void setYellowCards( int yellowCards );
	public void setMarketValue( int marketValue );
	public void setTeamFolder( String teamFolder );
	public void setOwnGoals( int ownGoals );
	public void setRedCards( int redCards );
	public void setMinutes( int minutes );
	public void setAssists( int assists );
	public void setGoals( int goals );
	public void setSaves( int saves );
	public void setScore(int score);

	//
	//GET METHODS
	//
	public double getPickProbability( );
	public int getGoalsConceded( );
	public Statistics[] getStats( );
	public int getMarketValue( );
	public String getPicturePath( );
	public String getTeamFolder( );
	public int getYellowCards( );
	public String getTeamName( );
	public Position getPosition( );
	public int getRedCards( );
	public int getOwnGoals( );
	public int getMinutes( );
	public int getAssists( );
	public String getName( );
	public int getGoals( );
	public int getSaves( );
	public int getScore();
}
