package trunk.Java;
import java.util.LinkedList;

public class DataConnection
{   
    
    public static FootballTeam[] createFootballTeams( ){
    	JSONObject[] data = null;
    	try{
    		data = JSON.fetchData();
    	}catch (Exception e) {
    		
    	}
    	

        
        FootballPlayer[] leikmenn = new FootballPlayer[240];

        for(int i = 0; i < 240; i++ ){
        	
            String name = data[i].getString("first_name") + " " + data[i].getString("second_name"); 
            String team = data[i].getString("team_name");
            String pos = data[i].getString("type_name");
            
            
        	FootballPlayer player = new FootballPlayer(name ,team, pos);
            leikmenn[i] = player;
            
            int assists = data[i].getInt("assists");
            leikmenn[i].setAssists(assists);
            
            int goals = data[i].getInt("goals_scored");
            leikmenn[i].setGoals(goals);
            
            int goalsC = data[i].getInt("goals_conceded");
            leikmenn[i].setGoalsConceded(goalsC);
            
            int value = 0;
            leikmenn[i].setMarketValue(value);
            
            int Ogoals = data[i].getInt("own_goals");
            leikmenn[i].setOwnGoals(Ogoals);
            
            int RC = data[i].getInt("red_cards");
            leikmenn[i].setRedCards(RC);
            
            int minutes = data[i].getInt("minutes");
            leikmenn[i].setMinutes(minutes);
            
            int saves = data[i].getInt("saves");
            leikmenn[i].setSaves(saves);
            
            leikmenn[i].setTeamFolder("");
            
            int YC = data[i].getInt("yellow_cards");
            leikmenn[i].setYellowCards(YC);
            
        	
        	
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

        FootballPlayer[] Arsenal = new FootballPlayer[27] ;
        FootballPlayer[] Chelsea = new FootballPlayer[21] ;
        FootballPlayer[] Liverpool = new FootballPlayer[25];
        FootballPlayer[] ManCity = new FootballPlayer[23];
        FootballPlayer[] ManUtd = new FootballPlayer[28];
        FootballPlayer[] Southampton = new FootballPlayer[24];
        FootballPlayer[] Spurs = new FootballPlayer[23];
        FootballPlayer[] Stoke = new FootballPlayer[21];
        FootballPlayer[] Swansea = new FootballPlayer[25];
        FootballPlayer[] WestHam = new FootballPlayer[23];

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


        FootballTeam arsenal = new FootballTeam(Arsenal);
        FootballTeam chelsea = new FootballTeam(Chelsea);
        FootballTeam liverpool = new FootballTeam(Liverpool);
        FootballTeam manCity = new FootballTeam(ManCity);
        FootballTeam manUtd = new FootballTeam(ManUtd);
        FootballTeam southampton = new FootballTeam(Southampton);
        FootballTeam spurs = new FootballTeam(Spurs);
        FootballTeam stoke = new FootballTeam(Stoke);
        FootballTeam swansea = new FootballTeam(Swansea);
        FootballTeam westHam = new FootballTeam(WestHam);
        
        return new FootballTeam[] {arsenal,chelsea,liverpool,manCity,manUtd,southampton,spurs,stoke,swansea,westHam};
		
	}
	
	
    
    
}
    
