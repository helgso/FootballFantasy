package trunk.Java;

public class Scheduler {
	public FootballTeam[][][] teamSchedule;
	public FootballTeam[][]   nextRound;
	private int numMatchInRound = 5;
	private int numRounds = 18;
	private int numTeams = 10;
	private int index;
	
	public Scheduler( FootballTeam[] teamTotal ){
		
		//initialize the round counter
		this.index = 0;
		
		//here we use a round robin approach that follows the following diagram
		//				A                          
		//				o  
		//      	    |
		//	    I o-----+-----o B  
		//				|
		//		  	  J o
        //   H o----------------o C 
		//
		//  	G o-----------o D          
        //      
		//    		  o--o                  
		//      	F      E 
		//this works as following:
		//the diagram as shown here displays all the matches
		//for one round. after those matches have been recorded
		// we rotate the letters A-I but not J to get the next round.
		//by doing this we ensure that each team plays each other.
		
		FootballTeam[] lid = new FootballTeam [numTeams];
		boolean[] check = new boolean[numTeams];
		for(int i = 0; i<numTeams; i++){
			
			int index = (int)(Math.random()*numTeams);
			
			while(check[index]){
				index = (int)(Math.random()*numTeams);
			}
			
			lid[i] = teamTotal[index];
			check[index] = true;
		}
		
		FootballTeam[][][] shcedule = new FootballTeam[numRounds][numMatchInRound][2];
		
		//initialize the matches for each round
		for(int i = 0; i < numRounds; i++){
			shcedule[i][0][0] = lid[0];
			shcedule[i][0][1] = lid[9];
			shcedule[i][1][0] = lid[1];
			shcedule[i][1][1] = lid[8];
			shcedule[i][2][0] = lid[2];
			shcedule[i][2][1] = lid[7];
			shcedule[i][3][0] = lid[3];
			shcedule[i][3][1] = lid[6];
			shcedule[i][4][0] = lid[4];
			shcedule[i][4][1] = lid[5];
			
			
			//When the round has been set. we shift the lid array to 
			//get a new combination of teams to play;
			
			FootballTeam[] aux = new FootballTeam[numTeams];
			for(int u = 0; u<8; u++){
				aux[u+1] = lid[u];
			}
			aux[0] = lid[8];
			aux[9] = lid[9];
			lid = aux;
		}
		
		this.teamSchedule =shcedule;
		
	}

	public FootballTeam[][][] getTotalSchedule( ){
		return this.teamSchedule;
	}
	
	public FootballTeam[][] getNextRoundSchedule( ){
		
		FootballTeam[][] round = new FootballTeam[numMatchInRound][2];
		
		//load the next round into the round array
		for(int i = 0; i < numMatchInRound; i++){
			
			round[i][0] = this.teamSchedule[this.index][i][0];
			round[i][1] = this.teamSchedule[this.index][i][1];
			
		}
		
		//increment the round counter
		this.index++;
		return round;
	}
	
	public int getNumMatchInRound (){
		return this.numMatchInRound;
	}
	public int getNumRounds (){
		return this.numRounds;
	}
	
}
