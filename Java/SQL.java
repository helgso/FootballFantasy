package trunk.Java;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;

 
public class SQL
{	
	
	public static FootballTeam createFootballTeams( String teamName )throws Exception{
			
		
		int NumPlayers = 0;
		String [][] Player = new String [348][16];
		// return FootballTeam[]
		// register the driver 
        String sDriverName = "org.sqlite.JDBC";
        Class.forName(sDriverName);
 
        // now we set up a set of fairly basic string variables to use in the body of the code proper
        String sTempDb = "Data.db";
        String sJdbc = "jdbc:sqlite";
        String sDbUrl = sJdbc + ":" + sTempDb;
        // which will produce a legitimate Url for SqlLite JDBC :
        // jdbc:sqlite:hello.db
        int iTimeout = 1;
        //String sDrop = "DROP TABLE R";
        //String sMakeTable = "CREATE TABLE R (key numeric, value text)";
        //String sMakeInsert = "INSERT INTO dummy VALUES(1,'Hello from the database')";
        String sMakeSelect = "SELECT name FROM Player where teamName ='"+teamName+"'";
        
 
        // create a database connection
        Connection conn = DriverManager.getConnection(sDbUrl);
        try {
            Statement stmt = conn.createStatement();
            try {
       
                stmt.setQueryTimeout(iTimeout);

                ResultSet rs = stmt.executeQuery(sMakeSelect);
                	
                	int index = 0;
                	
                    try {
                       while(rs.next())
                            {	
                                String sResult = rs.getString(1);
                                Player[index++][0] = sResult;
                            }
                    } finally {
                        try { rs.close(); } catch (Exception ignore) {}
                    }
                    String countQuery = "select count (name) from Player where teamName = '"+ teamName+"'";
                    rs = stmt.executeQuery(countQuery);
                	
                    try {
                       while(rs.next())
                            {	
                                int sResult = rs.getInt(1);
                                NumPlayers = sResult;
                            }
                    } finally {
                        try { rs.close(); } catch (Exception ignore) {}
                    }
                    
                   
            } finally {
                try { stmt.close(); } catch (Exception ignore) {}
            }
        } finally {
            try { conn.close(); } catch (Exception ignore) {}
        }
        
        FootballPlayer[] team = new FootballPlayer[NumPlayers];
        
        fetchInformation("teamName", Player, 1);
        fetchInformation("position", Player, 2);
        fetchInformation("marketValue", Player, 3);
        fetchInformation("form", Player, 4);
        fetchInformation("minutes", Player, 5);
        fetchInformation("assists", Player, 6);
        fetchInformation("goals", Player, 7);
        fetchInformation("goalsConceded", Player, 8);
        fetchInformation("ownGoals", Player, 9);
        fetchInformation("saves", Player, 10);
        fetchInformation("cleanSheets", Player, 11);
        fetchInformation("yellowCards", Player, 12);
        fetchInformation("redCards", Player, 13);
        fetchInformation("teamFolder", Player, 14);
        fetchInformation("picture", Player, 15);
        
        for(int i = 0; i < NumPlayers; i++ ){
        	FootballPlayer player = new FootballPlayer(Player[i][0] ,Player[i][1]);
        	
        	int assists = Integer.parseInt(Player[i][6]);
        	player.setAssists(assists);
        	
        	int goals = Integer.parseInt(Player[i][7]);
        	player.setGoals(goals);
        	
        	int goalsC = Integer.parseInt(Player[i][8]);
        	player.setGoalsConceded(goalsC);
        	
        	int value = Integer.parseInt(Player[i][3]);
        	player.setMarketValue(value);
        	
        	int Ogoals = Integer.parseInt(Player[i][9]);
        	player.setOwnGoals(Ogoals);
        	
        	int RC = Integer.parseInt(Player[i][13]);
        	player.setRedCards(RC);
        	
        	int minutes = Integer.parseInt(Player[i][5]);
        	player.setMinutes(minutes);
        	
        	int saves = Integer.parseInt(Player[i][10]);
        	player.setSaves(saves);
        	
        	player.setTeamFolder(Player[i][14]);
        	
        	int YC = Integer.parseInt(Player[i][12]);
        	player.setYellowCards(YC);
        	
        	player.setPosition(Player[i][2]);
        	
        	team[i] = player;
        }
        
        FootballTeam lidid = new FootballTeam(team);
        return lidid;
		
	}
	
	private static void fetchInformation(String quiry, String[][] player, int index)throws Exception{
		
		String sDriverName = "org.sqlite.JDBC";
        Class.forName(sDriverName);
 
        // now we set up a set of fairly basic string variables to use in the body of the code proper
        String sTempDb = "Data.db";
        String sJdbc = "jdbc:sqlite";
        String sDbUrl = sJdbc + ":" + sTempDb;
        // which will produce a legitimate Url for SqlLite JDBC :
        // jdbc:sqlite:hello.db
        int iTimeout = 1;
        
 
        // create a database connection
        Connection conn = DriverManager.getConnection(sDbUrl);
        try {
            Statement stmt = conn.createStatement();
            try {
       
                stmt.setQueryTimeout(iTimeout);

                ResultSet rs;
                String select;
                for(int i = 0; i < 348; i++){
                        	select = "Select "+ quiry+" FROM Player where name = '"+ player[i][0]+"'";
                        	rs = stmt.executeQuery(select);
                            try {
                               while(rs.next())
                                    {	
                                        String sResult = rs.getString(1);
                                        player[i][index] = sResult;
                                    }
                            } finally {
                                try { rs.close(); } catch (Exception ignore) {}
                            }
                        }
            } finally {
                try { stmt.close(); } catch (Exception ignore) {}
            }
        } finally {
            try { conn.close(); } catch (Exception ignore) {}
        }
	}
	
    public static void main (String[] args) throws Exception
    {	
    	FootballTeam[] nyttlid = createTeams();
    	
    }
    
    public static FootballTeam[] createTeams()throws Exception{
    	
    	FootballTeam[] teams = new FootballTeam[10];
    	FootballTeam Arsenal = createFootballTeams("Arsenal");
    	teams[0] = Arsenal;
    	FootballTeam Chelsea = createFootballTeams("Chealsea");
    	teams[1] = Chelsea;
    	FootballTeam Liverpool = createFootballTeams("Liverpool");
    	teams[2] = Liverpool;
    	FootballTeam ManCity = createFootballTeams("Man City");
    	teams[3] = ManCity;
    	FootballTeam ManUtd = createFootballTeams("Man Utd");
    	teams[4] = ManUtd ;
    	FootballTeam Southampton = createFootballTeams("Southampton");
    	teams[5] = Southampton;
    	FootballTeam Spurs = createFootballTeams("Spurs");
    	teams[6] = Spurs;
    	FootballTeam Stoke = createFootballTeams("Stoke");
    	teams[7] = Stoke;
    	FootballTeam Swansea = createFootballTeams("Swansea");
    	teams[8] = Swansea;
    	FootballTeam WestHam = createFootballTeams("West Ham");
    	teams[9] = WestHam;
    	
    	
    	return teams;
    }
    
}