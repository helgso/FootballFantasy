package trunk.Java;

public class FootballPlayer {
	
	private final String teamName;
	private final String name;
	
	public Statistics[] stats;
	
	private double 	 pickProbability; //how likely is this player to be in final team squad.
	private int 	 goalsConceded;
	private String 	 teamFolder;
	private int 	 marketValue;
	private int		 score;
	private int 	 yellowCards;
	private Position position;
	private String 	 picturePath;
	private int		 ownGoals;
	private int 	 redCards;
	private int 	 minutes;
	private int 	 assists;
	private int 	 goals;
	private int 	 saves;
	
	//
	//CONSTUCTOR
	//
	public FootballPlayer( String name, String teamName, String pos ){
		this.teamName = teamName;
		this.name 	  = name;
		
		//FIND POSITION
		//	this.position gets object from enum Position
		//	that match with String value pos.
		
		for(Position position : Position.values() ){
			if( position.getPos().equals( pos ) ){
				this.position = position;
			}
		}
		
	}
	
	

	//
	//UPDATE METHODS
	//
	
	public void updateFootballPlayer(){
		this.pickProbabilityUpdate();
		this.marketValueUpdate();
		this.scoreUpdate();
	}
	
	//DONE
	private void marketValueUpdate(){
		if(this.minutes == 0){
			this.minutes = 1;
		}
		
		int marketValue = 50;
		if(this.position == Position.FW){
			marketValue = 50 + 40*goals + 100*saves + 20*assists + (int ) (( 1000*goals)/minutes) - 30*redCards - 20*yellowCards + 10*goalsConceded;
		}else if(this.position == Position.DF){
			marketValue = 50 + 70*goals + 40*saves + 40*assists + (int ) (( 10000*goals)/minutes) - 20*redCards - 10*yellowCards + 10*goalsConceded;
		}else if(this.position == Position.GK){
			marketValue = 50 + 200*goals + saves + 150*assists + (int ) (( 100000*goals)/minutes) - 60*redCards - 30*yellowCards + 10*goalsConceded;
		}else if(this.position == Position.MF){
			marketValue = 50+ 60*goals + 80*saves + 10*assists + (int ) (( 2000*goals)/minutes) - 10*redCards - 10*yellowCards + 10*goalsConceded;
		}
		
		this.setMarketValue( marketValue );
	}
	
	//LAGA
	private void scoreUpdate(){
		
		this.setScore( this.score + 1 );
	}
	
	//LAGA
	private void pickProbabilityUpdate(){
		this.setPickProbability( this.pickProbability + 0.01 );
	}
	
	
	//
	//SET METHODS
	//
	
	public void setPicturePath( String picturePath ){
		this.picturePath = picturePath;
	}
	public void setPickProbability( double pickProbability ){
		this.pickProbability = pickProbability;
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
	public void setScore(int score) {
		this.score = score;
	}
	


	//
	//GET METHODS
	//

	public double getPickProbability( ){
		return this.pickProbability;
	}
	public int getGoalsConceded( ){
		return this.goalsConceded;
	}
	public Statistics[] getStats( ){
		return this.stats;
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
	public Position getPosition( ){
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

	public int getScore() {
		return this.score;
	}
}
