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
	//private int 	 coe = 5;
	private int 	 minimumValue = 100;
/*	private int[]    	A;
	private String[]    B;*/
	
	
	//
	//CONSTUCTOR
	//
	public FootballPlayer( String name, String teamName, String pos ){
		this.teamName = teamName;
		this.name 	  = name;
		
	/*	this.A = new int[50];
		this.B = new String[50];*/
		
		
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
		
		//Different coefficient Value for each position
		double GKCoefficient = 1.3;
		double DFCoefficient = 1.4;
		double MFCoefficient = 1.5;
		double FWCoefficient = 1.7;
		
		//			goals  saves	assists	goals/min  redC 	yellowC 	goalsC		ownG
		int[] GK = { 15,  	1, 		4, 		10000*1, 	5, 		2, 			1/15, 		0 };
		int[] DF = { 15,  	0,  	4,  	1000*1, 	5, 		2, 			1/20, 		0 };
		int[] MF = { 6,  	0,  	4,   	200*1, 		5, 		2, 			1/20, 		0 };
		int[] FW = { 5, 	0,  	4,   	200*1, 		5, 		2, 			1/30, 		0 };
		
		int marketValue = minimumValue;
		if(this.position == Position.GK){
			marketValue *= GKCoefficient;
			marketValue += calcMarketValue( GK );
		} else if(this.position == Position.DF){
			marketValue *= DFCoefficient;
			marketValue += calcMarketValue( DF );
		} else if(this.position == Position.MF){			
			marketValue *= MFCoefficient;
			marketValue += calcMarketValue( MF );	
		} else if(this.position == Position.FW){
			marketValue *= FWCoefficient;
			marketValue += calcMarketValue( FW );
		}
		marketValue = (int) Math.floor( marketValue );
		this.setMarketValue( marketValue  );
	}
	
	//Algorithm for to calculate value for footballPlayer
	private int calcMarketValue( int[] a ){
		int goals_per_min = (int)((goals)/minutes);
		int marketValue=(int)	(a[0]*goals
								+a[1]*saves
								+a[2]*assists
								+a[3]*goals_per_min
								-a[4]*redCards
								-a[5]*yellowCards
								-a[6]*goalsConceded
								-a[7]*ownGoals);		
		return marketValue;
	}
	
	
	//LAGA
	private void scoreUpdate(){
		if(this.minutes == 0){
			this.minutes = 1;
		}
		
		int score = 1;
		if(this.position == Position.FW){
			score = 4*goals + 3*assists - 3*redCards - yellowCards + (minutes/60) - 2*ownGoals;
		}else if(this.position == Position.DF){
			score = 6*goals + 3*assists - 3*redCards - yellowCards - (goalsConceded/2) - 2*ownGoals + (minutes/60);
			if(this.goalsConceded == 0){
				score += 4;
			}
		}else if(this.position == Position.GK){
			score = 6*goals + (saves/3) + 3*assists - 3*redCards - yellowCards + (goalsConceded/2) + (minutes/60) - 2*ownGoals;
			if(this.goalsConceded == 0){
				score += 4;
			}
		}else if(this.position == Position.MF){
			score = 5*goals + 3*assists - 3*redCards - yellowCards  + (minutes/60) - 2*ownGoals;
			if(this.goalsConceded == 0){
				score += 1;
			}
		}
		this.setScore( score );
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
