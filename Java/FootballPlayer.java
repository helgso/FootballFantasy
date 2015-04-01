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
	
	//
	//CONSTUCTOR
	//
	public FootballPlayer( String name, String teamName, String pos ){
		this.teamName = teamName;
		this.name 	  = name;
		
		this.stats = new Statistics[19];
		
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
	
	//
	//UPDATE METHODS
	//
	//Calculate football player score from last game statistics
	public void scoreUpdate( int roundNumber ){
		
		//		goals|saves|assists|min|redC|yellowC|goalsC|ownG|bonusGoalsC
		int[] GK={6,	3, 	  3,	45,  3, 	 1, 	 2, 	2,	 4};
		int[] DF={6,    1,    3,    45,  3, 	 1, 	 2, 	2,	 4};
		int[] MF={5,    1,    3,    45,  3, 	 1, 	 1, 	2, 	 1};
		int[] FW={4,	1,    3,    45,  3, 	 1, 	 1, 	2,	 0};
		
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
		Statistics lastGame = stats[ roundNumber+1 ];
		
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