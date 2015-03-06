package trunk.Java;

public class Scheduler {
	public FootballTeam[][][] teamSchedule;
	public FootballTeam[][]   nextRound;
	
	public Scheduler( FootballTeam[] teamTotal ){
		
		
		String[] lid = new String [10];
		boolean[] check = new boolean[10];
		for(int i = 0; i<10; i++){
			
			int index = (int)(Math.random()*10);
			
			while(check[index]){
				index = (int)(Math.random()*10);
			}
			
			lid[i] = teamTotal[index].getName();
			check[index] = true;
		}
		
		for(int i = 0; i < 10; i++){
			System.out.println(lid[i]);
		}
		
		
		
	}
	
	public FootballTeam[][][] getTotalSchedule( ){
		return null;
	}
	
	public FootballTeam[][] getNextRoundSchedule( ){
		return null;
	}
}
