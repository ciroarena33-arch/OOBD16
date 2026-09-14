package jdbc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

public class DBConnection {

    private static DBConnection dbconn = null;
    private Connection conn = null;

    private DBConnection() {}

    public static DBConnection getDBConnection() {
        if (dbconn == null) {
            dbconn = new DBConnection();
        }
        return dbconn;
    }

	public Connection getConnection(){
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
