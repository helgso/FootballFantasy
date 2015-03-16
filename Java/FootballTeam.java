package trunk.Java;

public class FootballTeam {
	private int numPlayers;
	private String teamPath;
	private FootballPlayer[] players;
	private String name;
	private double factor;
	
	//Constructor
	public FootballTeam( FootballPlayer[] players, String teamName, double fact ){
		this.players = players;
		this.name = teamName;
		this.factor = fact;
	}
	
	public FootballPlayer[] getSimulationTeam( ){
		System.out.println( this.name );
		System.out.println( this.numPlayers );
		System.out.println( this.teamPath );
		System.out.println( this.name );
		return this.players;
	}
	
	public double getFactor(){
		return this.factor;
	}
	
	public FootballPlayer[] getPlayers(){
		return players;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getPath( ){
		return "test";
	}
	
	public int getFootballPlayerAmount() {
		return players.length;
	}
}
