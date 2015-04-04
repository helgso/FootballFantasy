package trunk.Java;

import trunk.JSON.JSONObject;
import trunk.JSON.JSON;

import java.util.LinkedList;

public class DataConnection {   
    public static FootballTeam[] createFootballTeams( ){
    	// For a corresponding i of the loop below, a 0 in this array
    	// means that no picture is available for that particular
    	// FootballPlayer. A 1 means there exists a picture.
    	int[] thereExistsAPicture = {0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1,
    			1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 0,
    			1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0,
    			1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 0,
    			0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0,
    			1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,
    			1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1,
    			1, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0,
    			1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1,
    			0, 0, 0, 0};

    	JSONObject[] data = JSON.fetchData();
        
        FootballPlayer[] leikmenn = new FootballPlayer[240];

        for(int i = 0; i < 240; i++ ){
        	
            String name = data[i].getString("first_name") + " " + data[i].getString("second_name"); 
            String team = data[i].getString("team_name");
            if (team.equals("Man City")) team = "Manchester City";
            if (team.equals("Man Utd")) team = "Manchester United";
            
            String pos = data[i].getString("type_name");
            
        	FootballPlayer player = new FootballPlayer(name, team, pos);
        	
        	if (thereExistsAPicture[i] == 1) {
        		player.setPicturePath("trunk/Pictures/" + team.replaceAll(" ", "") + "/" + unformatName(name) + ".jpg");
        	} else {
        		player.setPicturePath("trunk/Pictures/AnonymousPlayer.jpg");
        	}
            
        	leikmenn[i] = player;
            
            int assists = data[i].getInt("assists");
            leikmenn[i].stats[0].setAssists(assists);
            
            int goals = data[i].getInt("goals_scored");
            leikmenn[i].stats[0].setGoals(goals);
            
            int goalsC = data[i].getInt("goals_conceded");
            leikmenn[i].stats[0].setGoalsConceded(goalsC);
            
            int Ogoals = data[i].getInt("own_goals");
            leikmenn[i].stats[0].setOwnGoals(Ogoals);
            
            int RC = data[i].getInt("red_cards");
            leikmenn[i].stats[0].setRedCards(RC);
            
            int minutes = data[i].getInt("minutes");
            leikmenn[i].stats[0].setMinutes(minutes);
            
            int saves = data[i].getInt("saves");
            leikmenn[i].stats[0].setSaves(saves);
            
            int YC = data[i].getInt("yellow_cards");
            leikmenn[i].stats[0].setYellowCards(YC);
            
            int value = makeMarketValue(player);
            leikmenn[i].setMarketValue(value);
            
            leikmenn[i].stats[0].setScore(0);
            
            // pickProbability will be calculated at
            // the bottom of this function
        }

        LinkedList<FootballPlayer> ArsenalTemp = new LinkedList<FootballPlayer>();
        LinkedList<FootballPlayer> ChelseaTemp = new LinkedList<FootballPlayer>();
        LinkedList<FootballPlayer> ManCityTemp = new LinkedList<FootballPlayer>();
        LinkedList<FootballPlayer> ManUtdTemp = new LinkedList<FootballPlayer>();
        LinkedList<FootballPlayer> SouthamptonTemp = new LinkedList<FootballPlayer>();
        LinkedList<FootballPlayer> SpursTemp = new LinkedList<FootballPlayer>();
        LinkedList<FootballPlayer> StokeTemp = new LinkedList<FootballPlayer>();
        LinkedList<FootballPlayer> SwanseaTemp = new LinkedList<FootballPlayer>();
        LinkedList<FootballPlayer> WestHamTemp = new LinkedList<FootballPlayer>();
        LinkedList<FootballPlayer> LiverpoolTemp = new LinkedList<FootballPlayer>();

        for(int i = 0; i < 240; i++){
            String team = data[i].getString("team_name");

            if(team.equals("Arsenal")){ArsenalTemp.add(leikmenn[i]);}
            else if(team.equals("Chelsea")){ChelseaTemp.add(leikmenn[i]);}
            else if(team.equals("Liverpool")){LiverpoolTemp.add(leikmenn[i]);}
            else if(team.equals("Man City")){ManCityTemp.add(leikmenn[i]);}
            else if(team.equals("Man Utd")){ManUtdTemp.add(leikmenn[i]);}
            else if(team.equals("Southampton")){SouthamptonTemp.add(leikmenn[i]);}
            else if(team.equals("Spurs")){SpursTemp.add(leikmenn[i]);}
            else if(team.equals("Stoke")){StokeTemp.add(leikmenn[i]);}
            else if(team.equals("Swansea")){SwanseaTemp.add(leikmenn[i]);}
            else {WestHamTemp.add(leikmenn[i]);}
        }

        FootballPlayer[] Arsenal = new FootballPlayer[ArsenalTemp.size()] ;
        FootballPlayer[] Chelsea = new FootballPlayer[ChelseaTemp.size()] ;
        FootballPlayer[] Liverpool = new FootballPlayer[LiverpoolTemp.size()];
        FootballPlayer[] ManCity = new FootballPlayer[ManCityTemp.size()];
        FootballPlayer[] ManUtd = new FootballPlayer[ManUtdTemp.size()];
        FootballPlayer[] Southampton = new FootballPlayer[SouthamptonTemp.size()];
        FootballPlayer[] Spurs = new FootballPlayer[SpursTemp.size()];
        FootballPlayer[] Stoke = new FootballPlayer[StokeTemp.size()];
        FootballPlayer[] Swansea = new FootballPlayer[SwanseaTemp.size()];
        FootballPlayer[] WestHam = new FootballPlayer[WestHamTemp.size()];

        for(int i = 0; i < Arsenal.length; i++){
        	Arsenal[i] = (FootballPlayer)ArsenalTemp.pop();
        }

        for(int i = 0; i < Chelsea.length; i++){
            Chelsea[i] = (FootballPlayer)ChelseaTemp.pop();
        }

        for(int i = 0; i < Liverpool.length; i++){
            Liverpool[i] = (FootballPlayer)LiverpoolTemp.pop();
        }

        for(int i = 0; i < ManCity.length; i++){
            ManCity[i] = (FootballPlayer)ManCityTemp.pop();
        }

        for(int i = 0; i < ManUtd.length; i++){
            ManUtd[i] = (FootballPlayer)ManUtdTemp.pop();
        }

        for(int i = 0; i < Southampton.length; i++){
            Southampton[i] = (FootballPlayer)SouthamptonTemp.pop();
        }

        for(int i = 0; i < Spurs.length; i++){
            Spurs[i] = (FootballPlayer)SpursTemp.pop();
        }

        for(int i = 0; i < Stoke.length; i++){
            Stoke[i] = (FootballPlayer)StokeTemp.pop();
        }

        for(int i = 0; i < Swansea.length; i++){
            Swansea[i] = (FootballPlayer)SwanseaTemp.pop();
        }

        for(int i = 0; i < WestHam.length; i++){
            WestHam[i] = (FootballPlayer)WestHamTemp.pop();
        }

        FootballTeam arsenal = new FootballTeam(Arsenal, "Arsenal", 0.5865385, "trunk/Pictures/Arsenal/Arsenal.png");
        FootballTeam chelsea = new FootballTeam(Chelsea, "Chelsea", 0.640776, "trunk/Pictures/Chelsea/Chelsea.png");
        FootballTeam liverpool = new FootballTeam(Liverpool, "Liverpool", 0.5480769, "trunk/Pictures/Liverpool/Liverpool.png");
        FootballTeam manCity = new FootballTeam(ManCity, "Manchester City", 0.6442308, "trunk/Pictures/ManCity/ManCity.png");
        FootballTeam manUtd = new FootballTeam(ManUtd, "Manchester United", 0.5961538, "trunk/Pictures/ManUtd/ManUtd.png");
        FootballTeam southampton = new FootballTeam(Southampton, "Southampton", 0.3750000, "trunk/Pictures/Southampton/Southampton.png");
        FootballTeam spurs = new FootballTeam(Spurs, "Spurs", 0.5480769, "trunk/Pictures/Spurs/Spurs.png");
        FootballTeam stoke = new FootballTeam(Stoke, "Stoke", 0.3269231, "trunk/Pictures/Stoke/Stoke.png");
        FootballTeam swansea = new FootballTeam(Swansea, "Swansea", 0.3173077, "trunk/Pictures/Swansea/Swansea.png");
        FootballTeam westHam = new FootballTeam(WestHam, "West Ham", 0.3173077, "trunk/Pictures/WestHam/WestHam.png");

        FootballTeam[] teamTotal = {arsenal,chelsea,liverpool,manCity,manUtd,southampton,spurs,stoke,swansea,westHam};
        
        // This is the most sane place to calculate the
        // pickProbability for each FootballPlayer
        for (FootballTeam team : teamTotal) {
        	int sumTeamMinutes = sumMinutes(team);
        	
        	for (FootballPlayer player : team.getFootballPlayers()) {
        		player.setPickProbability(1.0*player.stats[0].getMinutes()/sumTeamMinutes);
        	}
        	team.createSimulationTeam(); // With the pickProbabilities in place, we can finally create the simulation team
        }
        
        return teamTotal;
	}
    
    // post: Makes the names of players more suitable as filenames. "Jussi Jääskeläinen" would turn into "Jussi-Jaaskelainen"
    private static String unformatName(String name) {
    	String[] find    = {"Ö", "á", "é", "ü", "à", "ï", "ë", "í", "á", "Á", "è", "'", "ä", " "};
    	String[] replace = {"O", "a", "e", "u", "a", "i", "e", "i", "a", "A", "e", "",  "a", "-"};
    	
    	for (int i = 0; i < find.length; i++) {
    		name = name.replaceAll(find[i], replace[i]);
    	}
    	return name;
    }
    
    //Calculate football player value from all his statistics
  	private static int makeMarketValue(FootballPlayer player) {
  		
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
  		
  		double marketValue = 100; // Minimum value is 100, let's add to that number
  		if (player.getPosition() == Position.GK){
  			marketValue *= GKCoefficient;
  			marketValue += calcMarketValue( GK, player );
  		} else if(player.getPosition() == Position.DF){
  			marketValue *= DFCoefficient;
  			marketValue += calcMarketValue( DF, player );
  		} else if(player.getPosition() == Position.MF){			
  			marketValue *= MFCoefficient;
  			marketValue += calcMarketValue( MF, player );	
  		} else if (player.getPosition() == Position.FW){
  			marketValue *= FWCoefficient;
  			marketValue += calcMarketValue( FW, player );
  		}
  		return (int) Math.round( marketValue );
  	}
  	
  	// Calculates a marketValue for this FootballPlayer.
  	// factors is an array of factors which helps to
  	// determine a different marketValue for a each
  	// FootballPlayer based on his stats and position
  	// (Forward, Midfielder, etc.)
  	private static int calcMarketValue( int[] factors, FootballPlayer player ){
  		int goals_per_min = (int)((player.stats[0].getGoals())/player.stats[0].getMinutes());
  		int marketValue=(int)	(factors[0]*player.stats[0].getGoals()
  								+factors[1]*player.stats[0].getSaves()
  								+factors[2]*player.stats[0].getAssists()
  								+factors[3]*goals_per_min
  								-factors[4]*player.stats[0].getRedCards()
  								-factors[5]*player.stats[0].getYellowCards()
  								-factors[6]*player.stats[0].getGoalsConceded()
  								-factors[7]*player.stats[0].getOwnGoals());		
  		return marketValue;
  	}
  	
  	private static int sumMinutes(FootballTeam team) {
  		int sum = 0;
  		for (FootballPlayer player : team.getFootballPlayers()) {
  			sum += player.stats[0].getMinutes();
  		}
  		return sum;
  	}
}
    
