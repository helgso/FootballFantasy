package trunk.Java;

public class FootballPlayer {
	
	private final String name;
	private final String teamName;
	
	public Statistics[] stats;
	
	private double 	 pickProbability; //how likely is this player to be in final team squad.
	private int 	 marketValue;
	private int		 score;
	private Position position;
	private String 	 picturePath;
	private int 	 minimumValue;
	
	//
	//CONSTUCTOR
	//
	public FootballPlayer( String name, String teamName, String pos ){
		this.teamName = teamName;
		this.name 	  = name;
		
		this.reset();
		
		this.stats = new Statistics[18];
		
		// 0-ta hólf inniheldur gögn frá Premier League.
		// 1-ta til og með 18-ta hólf eru stats fyrir round 1 til og með 18
		for(int i = 0; i<19; i++){
			this.stats[i] = new Statistics();
		}
		
		//FIND POSITION
		//	this.position gets object from enum Position
		//	that match with String value pos.
		
		for(Position position : Position.values() ){
			if( position.getPos().equals( pos ) ){
				this.position = position;
			}
		}
	}
	
	private void reset(){
		this.score = 0;
		this.minimumValue = 100;
	}
	
	//
	//UPDATE METHODS
	//
	public void updateFootballPlayer( int roundNumber){
		this.scoreUpdate( roundNumber );
	}
	
	public void updateFootballPlayer(){
		this.marketValueUpdate();
	}
	
	//Calculate football player value from all his statistics
	private void marketValueUpdate(){
		
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
		setMarketValue( marketValue  );
	}
	
	// Calculates a marketValue for this FootballPlayer.
	// factors is an array of factors which helps to
	// determine a different marketValue for a each
	// FootballPlayer based on his stats and position
	// (Forward, Midfielder, etc.)
	private int calcMarketValue( int[] factors ){
		int goals_per_min = (int)((stats[0].getGoals())/stats[0].getMinutes());
		int marketValue=(int)	(factors[0]*stats[0].getGoals()
								+factors[1]*stats[0].getSaves()
								+factors[2]*stats[0].getAssists()
								+factors[3]*goals_per_min
								-factors[4]*stats[0].getRedCards()
								-factors[5]*stats[0].getYellowCards()
								-factors[6]*stats[0].getGoalsConceded()
								-factors[7]*stats[0].getOwnGoals());		
		return marketValue;
	}

	//Calculate football player score from last game statistics
	private void scoreUpdate( int roundNumber ){
		
		//		goals|saves|assists|min|redC|yellowC|goalsC|ownG|bonusGoalsC
		int[] GK={6,	3, 	  3,	45,  3, 	 1, 	 2, 	2,	 4};
		int[] DF={6,    0,    3,    45,  3, 	 1, 	 2, 	2,	 4};
		int[] MF={5,    0,    3,    45,  3, 	 1, 	 1, 	2, 	 1};
		int[] FW={4,	0,    3,    45,  3, 	 1, 	 1, 	2,	 0};
		
		int newScore = this.score;
		if(this.position == Position.GK){
			newScore += this.calcScore(GK, roundNumber);
		} else if(this.position == Position.DF){
			newScore += this.calcScore(DF, roundNumber);
		}else if(this.position == Position.MF){
			newScore += this.calcScore(MF, roundNumber);
		}else if(this.position == Position.FW){
			newScore += this.calcScore(FW, roundNumber);
		}
		this.setScore( this.score + newScore );
	}
	
	// returns calculate score from LAST match
	private int calcScore(int[] a, int roundNumber ){
		
		//Get last match statistics
		Statistics lastGame = stats[ roundNumber ];
		
		int newScore = 1;
		
		//Bonus score if player has no goals conceded
		if(stats[0].getGoalsConceded() == 0){
			newScore += a[ a.length-1 ];
		}
		
		//main calculation
		newScore =(int)(   a[0]*lastGame.getGoals()
						+1/a[1]*lastGame.getSaves()
						+  a[2]*lastGame.getAssists()
						+1/a[3]*lastGame.getMinutes()
						-  a[4]*lastGame.getRedCards()
						-  a[5]*lastGame.getYellowCards()
						-1/a[6]*lastGame.getGoalsConceded()
						-  a[7]*lastGame.getOwnGoals());
		return newScore;
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
	public void setScore(int score) {
		this.score = score;
	}
	public void setMarketValue(int value) {
		this.marketValue = value;
	}

	//
	//GET METHODS
	//
	public double getPickProbability( ){
		return this.pickProbability;
	}
	public int getMarketValue( ){
		return this.marketValue;
	}
	public String getPicturePath( ){
		return this.picturePath;
	}
	public String getTeamName( ){
		return this.teamName;
	}
	public Position getPosition( ){
		return this.position;
	}
	public String getName( ){
		return this.name;
	}
	public int getScore() {
		return this.score;
	}
	public Statistics[] getStats() {
		return this.stats;
	}
}