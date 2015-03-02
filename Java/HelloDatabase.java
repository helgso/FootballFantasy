package trunk.Java;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.DatabaseMetaData;
import java.lang.Object;

 
public class HelloDatabase
{	
	
	public static String[][] createFootballTeams( )throws Exception{
			
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
        String sMakeSelect = "SELECT name FROM Player";
        
 
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
                    
                    
                    
                   
            } finally {
                try { stmt.close(); } catch (Exception ignore) {}
            }
        } finally {
            try { conn.close(); } catch (Exception ignore) {}
        }
        
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
        
        return Player;
		
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
        String sMakeSelect;
        
 
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
    	
    	String [][] players = createFootballTeams();
    	
    	for(int i = 0; i < 10; i++){
    		System.out.println(players[i][0]);
    		System.out.println("  "+players[i][1]);
    		System.out.println("  "+players[i][2]);
    		System.out.println("  "+players[i][3]);
    		System.out.println("  "+players[i][4]);
    		System.out.println("  "+players[i][5]);
    		System.out.println("  "+players[i][6]);
    		System.out.println("  "+players[i][7]);
    		System.out.println("  "+players[i][8]);
    		System.out.println("  "+players[i][9]);
    		System.out.println("  "+players[i][10]);
    		System.out.println("  "+players[i][11]);
    		System.out.println("  "+players[i][12]);
    		System.out.println("  "+players[i][13]);
    		System.out.println("  "+players[i][14]);
    		System.out.println("  "+players[i][15]);
    	}
    }
 
}