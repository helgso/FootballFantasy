package trunk.Java;

import java.util.Arrays; // For an easy comparison when picking players for the simulation team

public class FootballTeam {
	private FootballPlayer[] players;
	private FootballPlayer[] simulationPlayers;
	private String name;
	private double factor;
	private String logoPath;
	
	//Constructor
	public FootballTeam( FootballPlayer[] players, String teamName, double fact, String logoPath ){
		this.players = players;
		this.simulationPlayers = createSimulationTeam();
		this.name = teamName;
		this.factor = fact;
		this.logoPath = logoPath;
	}
	
	private FootballPlayer[] createSimulationTeam() {
		FootballPlayer [] simulationTeam = new FootballPlayer[11];
		
		// Containers for 1 goalKeeper, 4 defenders, 4 midfielders and 2 forwarders
		FootballPlayer[] GK  = new FootballPlayer[1]; int GKindex = 0;
		FootballPlayer[] DFs = new FootballPlayer[4]; int DFindex = 0;
		FootballPlayer[] MFs = new FootballPlayer[4]; int MFindex = 0;
		FootballPlayer[] FWs = new FootballPlayer[2]; int FWindex = 0;
		
		double[] chancesOfPick = new double[players.length];
		for (int i = 0; i < players.length; i++) {
			chancesOfPick[i] = players[i].getPickProbability();
		}
		
		// While there's still a player left to
		// be picked for the simulation team
		while (GKindex + DFindex + MFindex + FWindex < 11) {
			
			// Pick a player for us based on the chancesOfPick array:
			int pickedPlayerIndex = Random.determineValue(chancesOfPick);
			FootballPlayer pickedPlayer = players[pickedPlayerIndex];
			
			// Based on the pickedPlayer's position, put him in his
			// corresponding container. Also make sure we won't
			// pick the same player twice
			if (pickedPlayer.getPosition() == Position.GK) {
				if (GKindex == 0) { // If no goal keeper has been picked until now
					GK[0] = pickedPlayer;
					GKindex++; // A goal keeper has been selected :D
				}
			}
			if (pickedPlayer.getPosition() == Position.DF) {
				if (DFindex != 4 && // If we have yet to pick 4 defenders
				    !Arrays.asList(DFs).contains(pickedPlayer)) { // if we haven't picked this player until now
					DFs[DFindex] = pickedPlayer;
					DFindex++;
				}
			}
			if (pickedPlayer.getPosition() == Position.MF) {
				if (MFindex != 4 &&
				    !Arrays.asList(MFs).contains(pickedPlayer)) {
					MFs[MFindex] = pickedPlayer;
					MFindex++;
				}
			}
			if (pickedPlayer.getPosition() == Position.FW) {
				if (FWindex != 2 &&
				    !Arrays.asList(FWs).contains(pickedPlayer)) {
					FWs[FWindex] = pickedPlayer;
					FWindex++;
				}
			}
		}
		
		FootballPlayer[][] simulationTeamTemp = {GK, DFs, MFs, FWs};
		int i = 0;
		for (FootballPlayer[] container : simulationTeamTemp) {
			for (FootballPlayer player : container) {
				simulationTeam[i] = player;
				i++;
			}
		}
		return simulationTeam;
	}
	
	public FootballPlayer[] getSimulationTeam( ){
		return this.simulationPlayers;
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
