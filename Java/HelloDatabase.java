import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.DatabaseMetaData;
import java.lang.Object;
 
public class HelloDatabase
{
    public static void main (String[] args) throws Exception
    {
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
        String sMakeSelect = "SELECT COUNT (*) from R WHERE key = 1000";
 
        // create a database connection
        Connection conn = DriverManager.getConnection(sDbUrl);
        try {
            Statement stmt = conn.createStatement();
            try {
                stmt.setQueryTimeout(iTimeout);
                stmt.executeUpdate( sDrop );
                long first = System.nanoTime();
                stmt.executeUpdate( sMakeTable );
                for(int i = 0; i < 10000; i++){
                    stmt.executeUpdate( "INSERT INTO R VALUES("+i+",'value "+i+"')");
                }


                long fin = System.nanoTime();
                double eleps = (fin-first)/(1000000000.0);
                System.out.println(eleps);



                first = System.nanoTime();
                ResultSet rs = stmt.executeQuery(sMakeSelect);
                for(int i = 0; i < 100; i++){

                    rs = stmt.executeQuery("SELECT COUNT (*) from R WHERE key ="+(10000000-i));
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
                fin = System.nanoTime();
                eleps = (fin-first)/(10000.0);
                System.out.println(eleps);

                stmt.executeUpdate( sDrop );
               stmt.executeUpdate( sMakeTable  );
               stmt.executeUpdate( "CREATE INDEX minninndex ON R(key)" );

               first = System.nanoTime();
                for(int i = 0; i < 10000000; i++){
                    stmt.executeUpdate( "INSERT INTO R VALUES("+i+",'value "+i+"')");
                }


                fin = System.nanoTime();
                eleps = (fin-first)/(1000000000.0);
                System.out.println(eleps);


                first = System.nanoTime();
                ResultSet rst = stmt.executeQuery(sMakeSelect);
                for(int i = 0; i < 100; i++){

                    rst = stmt.executeQuery("SELECT COUNT (*) from R WHERE key ="+(10000000-i));
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
                fin = System.nanoTime();
                eleps = (fin-first)/(1000000000.0);
                System.out.println(eleps);
            } finally {
                try { stmt.close(); } catch (Exception ignore) {}
            }
        } finally {
            try { conn.close(); } catch (Exception ignore) {}
        }
    }
 
}