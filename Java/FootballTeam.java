package trunk.Java;

public class FootballTeam {
	private FootballPlayer[] players;
	private String name;
	private double factor;
	private String logoPath;
	
	//Constructor
	public FootballTeam( FootballPlayer[] players, String teamName, double fact, String logoPath ){
		this.players = players;
		this.name = teamName;
		this.factor = fact;
		this.logoPath = logoPath;
	}
	
	public FootballPlayer[] getSimulationTeam( ){
		return this.players;
	}
	
	public double getFactor(){
		return this.factor;
	}
	
	public FootballPlayer[] getFootballPlayers(){
		return players;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getLogoPath( ){
		return logoPath;
	}
	
	public int getFootballPlayerAmount() {
		return players.length;
	}
}
