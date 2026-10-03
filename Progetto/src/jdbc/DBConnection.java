package jdbc;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection
{
    private static DBConnection dbcon = null;
    private Connection conn = null;

    private DBConnection(){}

    public static DBConnection getDBConnection()
    {   
        if (dbcon == null) {
            dbcon = new DBConnection();
        }
        return dbcon;
    }

    public Connection getConnection()
    {
        String pwd = null;
        BufferedReader b = null;
        try
        {   
            if(conn==null || conn.isClosed())
            {   
                b = new BufferedReader(new FileReader(new File("src/pwdfile")));
                pwd = b.readLine();
                conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "OOBD16", pwd);
            }
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

        return conn;
    }


}
