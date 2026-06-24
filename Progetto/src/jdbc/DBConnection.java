package jdbc;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException; 

public class DBConnection {
	
	private static DBConnection dbconn=null;
	private Connection conn=null;
	
	private DBConnection() {}
	
	public static DBConnection getDBConnection() {
		if(dbconn==null) {
			dbconn=new DBConnection();
		}
		return dbconn;
	}
	
	public Connection getConnection() {
		String pwd=null;
		BufferedReader b=null;
		try {
			if(conn==null||conn.isClosed()) {
				b=new BufferedReader(new FileReader(new File("src/pwd")));
				pwd=b.readLine();	
				Class.forName("oracle.jdbc.driver.OracleDriver");
				DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "OOBD16", "DE1000111");
				
			}
		}catch(SQLException|IOException|ClassNotFoundException throwables) {
			 throwables.printStackTrace();
		 }
		return conn;
		
	}

}
