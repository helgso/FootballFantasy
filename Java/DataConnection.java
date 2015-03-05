package trunk.Java;
import java.util.LinkedList;

public class SQL
{   
    
    public static FootballTeam[] createFootballTeams( ){
        
    	JSONObject[] data = Json.fetchData();

        
        FootballPlayer[] leikmenn = new FootballPlayer[240];

        for(int i = 0; i < 1; i++ ){
            String name = data[i].getString("first_name") + " " + data[i].getString("last_name"); 
            String team = data[i].getString("team_name");
        	FootballPlayer player = new FootballPlayer(name ,team);
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
            
            leikmenn[i].setPosition(data[i].getInt("type_name"));
        	
        	
        }

        
        LinkedList ArsenalTemp = new LinkedList();
        LinkedList ChelseaTemp = new LinkedList();
        LinkedList ManCityTemp = new LinkedList();
        LinkedList ManUtdTemp = new LinkedList();
        LinkedList SouthamptonTemp = new LinkedList();
        LinkedList SpursTemp = new LinkedList();
        LinkedList StokeTemp = new LinkedList();
        LinkedList SwanseaTemp = new LinkedList();
        LinkedList WestHamTemp = new LinkedList();
        LinkedList LiverpoolTemp = new LinkedList();

        

        for(int i = 0; i < 1; i++){
            String team = data[i].getString("team_name");

            if(team.equals("Arsenal")){ArsenalTemp.add(leikmenn[i]);}
            else if(team.equals("Chelsea")){ChelseaTemp.add(leikmenn[i]);}
            else if(team.equals("Liverpool")){LiverpoolTemp.add(leikmenn[i]);}
            else if(team.equals("ManCity")){ManCityTemp.add(leikmenn[i]);}
            else if(team.equals("ManUtd")){ManUtdTemp.add(leikmenn[i]);}
            else if(team.equals("Southampton")){SouthamptonTemp.add(leikmenn[i]);}
            else if(team.equals("Spurs")){SpursTemp.add(leikmenn[i]);}
            else if(team.equals("Stoke")){StokeTemp.add(leikmenn[i]);}
            else if(team.equals("Swansea")){SwanseaTemp.add(leikmenn[i]);}
            else {WestHamTemp.add(leikmenn[i]);}
        }

        FootballPlayer[] Arsenal = new FootballPlayer[27] ;
        /*FootballPlayer[] Chelsea = new FootballPlayer[21] ;
        FootballPlayer[] Liverpool = new FootballPlayer[25];
        FootballPlayer[] ManCity = new FootballPlayer[23];
        FootballPlayer[] ManUtd = new FootballPlayer[28];
        FootballPlayer[] Southampton = new FootballPlayer[24];
        FootballPlayer[] Spurs = new FootballPlayer[23];
        FootballPlayer[] Stoke = new FootballPlayer[21];
        FootballPlayer[] Swansea = new FootballPlayer[25];
        FootballPlayer[] WestHam = new FootballPlayer[23];*/

        for(int i = 0; i < 1/* Arsenal.length*/; i++){
            Arsenal[i] = ArsenalTemp.pop();
        }

      /*  for(int i = 0; i < Chelsea.length; i++){
            Chelsea[i] = ChelseaTemp.pop();
        }

        for(int i = 0; i < Liverpool.length; i++){
            Liverpool[i] = LiverpoolTemp.pop();
        }

        for(int i = 0; i < ManCity.length; i++){
            ManCity[i] = ManCityTemp.pop();
        }

        for(int i = 0; i < ManUtd.length; i++){
            ManUtd[i] = ManUtdTemp.pop();
        }

        for(int i = 0; i < Southampton.length; i++){
            Southampton[i] = SouthamptonTemp.pop();
        }

        for(int i = 0; i < Spurs.length; i++){
            Spurs[i] = SpursTemp.pop();
        }

        for(int i = 0; i < Stoke.length; i++){
            Stoke[i] = StokeTemp.pop();
        }

        for(int i = 0; i < Swansea.length; i++){
            Swansea[i] = SwanseaTemp.pop();
        }

        for(int i = 0; i < WestHam.length; i++){
            WestHam[i] = WestHamTemp.pop();
        }

*/
        return new FootballTeam[] {Arsenal/*,Chelsea,Liverpool,ManCity,ManUtd,Southampton,Spurs,Stoke,Swansea,WestHam*/};
		
	}
	
	
	
    public static void main (String[] args) throws Exception
    {	
    	FootballTeam[] nyttlid = createFootballTeams();
        FootballPlayer[] ja = nyttlid[0].getSimulationTeam();

    	
    }
    
    
}
    
