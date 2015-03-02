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
	
	public static void createFootballTeams( )throws Exception{
		
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
                //stmt.executeUpdate( sDrop );
                //stmt.executeUpdate( sMakeTable );
                //for(int i = 0; i < 10; i++){
                //    stmt.executeUpdate( "INSERT INTO R VALUES("+i+",'value "+i+"')");
                //}

                ResultSet rs = stmt.executeQuery(sMakeSelect);
                //for(int i = 0; i < 10 ; i++){
                	
                	int index = 0;
                	String [] Player = new String [348];
                    try {
                       while(rs.next())
                            {	
                                String sResult = rs.getString(1);
                                Player[index++] = sResult;
                                //System.out.println(sResult);
                            }
                    } finally {
                        try { rs.close(); } catch (Exception ignore) {}
                    }
                //}
                    for(int i = 0; i < 348; i++){
                    	System.out.println(Player[i]);
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
    	
    	createFootballTeams();/*
        // register the driver 
        String sDriverName = "org.sqlite.JDBC";
        Class.forName(sDriverName);
 
        // now we set up a set of fairly basic string variables to use in the body of the code proper
        String sTempDb = "hello.db";
        String sJdbc = "jdbc:sqlite";
        String sDbUrl = sJdbc + ":" + sTempDb;
        // which will produce a legitimate Url for SqlLite JDBC :
        // jdbc:sqlite:hello.db
        int iTimeout = 1;
        String sDrop = "DROP TABLE R";
        String sMakeTable = "CREATE TABLE R (key numeric, value text)";
        String sMakeInsert = "INSERT INTO dummy VALUES(1,'Hello from the database')";
        String sMakeSelect = "SELECT key from R";
 
        // create a database connection
        Connection conn = DriverManager.getConnection(sDbUrl);
        try {
            Statement stmt = conn.createStatement();
            try {
                stmt.setQueryTimeout(iTimeout);
                stmt.executeUpdate( sDrop );
                stmt.executeUpdate( sMakeTable );
                for(int i = 0; i < 10; i++){
                    stmt.executeUpdate( "INSERT INTO R VALUES("+i+",'value "+i+"')");
                }

                ResultSet rs = stmt.executeQuery(sMakeSelect);
                for(int i = 0; i < 10 ; i++){

                    try {
                       while(rs.next())
                            {
                                int sResult = rs.getInt(1);
                                System.out.println(sResult);
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
        }*/
    }
 
}