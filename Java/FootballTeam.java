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
		FootballPlayer [] simulationTeam = new FootballPlayer[11];
		
		int[] timeG = new int[1];
		int[] timeD = new int[4];
		int[] timeM = new int[4];
		int[] timeF = new int[2];
		
		FootballPlayer[] goals = new FootballPlayer[1];
		FootballPlayer[] def = new FootballPlayer[4];
		FootballPlayer[] midf = new FootballPlayer[4];
		FootballPlayer[] frw = new FootballPlayer[2];
		
		for(FootballPlayer x: players){
			if(x.getPosition() == Position.GK){
				for(int i = 0; i<1; i++){
					if(x.getMinutes()>timeG[i]){
						replaceLowest(timeG,goals,x);
					}
				}
				
			}
			else if(x.getPosition() == Position.DF){
				for(int i = 0; i<4; i++){
					if(x.getMinutes()>timeD[i]){
						replaceLowest(timeD,def,x);
						break;
					}
				}
			}else if(x.getPosition() == Position.MF){
				for(int i = 0; i<4; i++){
					if(x.getMinutes()>timeM[i]){
						replaceLowest(timeM,midf,x);
						break;
					}
				}
			}else{
				for(int i = 0; i<2; i++){
					if(x.getMinutes()>timeF[i]){
						replaceLowest(timeF,frw,x);
						break;
					}
				}
			}
		}
		
		simulationTeam[0] = goals[0];
		simulationTeam[1] = midf[0];
		simulationTeam[2] = midf[1];
		simulationTeam[3] = midf[2];
		simulationTeam[4] = midf[3];
		simulationTeam[5] = def[0];
		simulationTeam[6] = def[1];
		simulationTeam[7] = def[2];
		simulationTeam[8] = def[3];
		simulationTeam[9] = frw[0];
		simulationTeam[10] = frw[1];
		
		return simulationTeam;
	}
	
	private void replaceLowest(int[] t, FootballPlayer[] p, FootballPlayer x){
		int min = 10000000;
		int index = 0;
		for(int i = 0; i<t.length; i++){
			if(t[i] < min){
				min = t[i];
				index = i;
			}
		}
		p[index] = x;
		t[index] = x.getMinutes();
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
