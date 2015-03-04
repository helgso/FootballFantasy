package trunk.Java;

public class MatchSimulation {
	private FootballTeam[] homeTeam;
	private FootballTeam[] awayTeam;
	private MatchResults results;
	
	public MatchSimulation( ){
		
		this.storeResults( this.results );
	}
	
	private void playMatch( ){
		/*
		 * Play match, store result 
		 */
		System.out.println("Test playMatch method" + this.homeTeam + this.awayTeam);
	}
	
	public void build( FootballTeam[] homeTeam, FootballTeam[] awayTeam ){
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.playMatch();
	}
	
	private void storeResults( MatchResults result ){
		this.results = result;
	}
}
