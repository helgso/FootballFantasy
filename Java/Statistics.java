package trunk.Java;

public class Statistics {
	
	private boolean cleanSheet;
	private int goalsConceded;
	private int yellowCards;
	private int ownGoals;
	private int redCards;
	private int minutes;
	private int assists;
	private int goals;
	private int saves;
	private int score;

	// ============
	// Incrementers
	// ============
	public void incrGoalsConcededBy(int amount) {
		this.yellowCards += amount;
	}
	public void incrYellowCardsBy(int amount) {
		this.yellowCards += amount;
	}
	public void incrRedCardsBy(int amount){
		this.redCards += amount;
	}
	public void incrOwnGoalsBy(int amount) {
		this.ownGoals += amount;
	}
	public void incrAssistsBy(int amount) {
		this.assists += amount;
	}
	public void incrGoalsBy(int amount) {
		this.goals += amount;
	}
	public void incrSavesBy(int amount) {
		this.saves += amount;
	}
	public void incrMinutesBy(int amount) {
		this.saves += amount;
	}
	public void incrScoreBy(int amount) {
		this.score += amount;
	}
	
	// =======
	// Setters
	// =======
	public void setGoalsConceded( int goalsConceded ){
		this.goalsConceded= goalsConceded;
	}
	public void setCleanSheet( boolean cleanSheet ){
		this.cleanSheet = cleanSheet;
	}
	public void setYellowCards( int yellowCards ){
		this.yellowCards  = yellowCards ;
	}
	public void setRedCards( int redCards ){
		this.redCards = redCards;
	}
	public void setOwnGoals( int ownGoals ){
		this.ownGoals  = ownGoals;
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
	public void setScore( int score ){
		this.score = score;
	}
	
	// =======
	// Getters
	// =======
	public boolean getCleanSheet( ){
		return this.cleanSheet;
	}
	public int getGoalsConceded( ){
		return this.goalsConceded;
	}
	public int getYellowCards( ){
		return this.yellowCards;
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
	public int getGoals( ){
		return this.goals;
	}
	public int getSaves( ){
		return this.saves;
	}
	public int getScore( ){
		return this.score;
	}
}
