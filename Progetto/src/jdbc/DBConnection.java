package jdbc;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException; 

public class DBConnection {
	
	private static DBConnection dbconn=null;
	private Connection conn=null;
	
	private DBConnection() {}
	
	public static synchronized DBConnection getDBConnection() {
		if(dbconn==null) {
			dbconn=new DBConnection();
		}
		return dbconn;
	}
	
	public Connection getConnection() {
		try {
			if(conn==null||conn.isClosed()) {
				
				conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "OOBD16", "DE1000111");
				
			}
		}catch(SQLException throwables) {
			 throwables.printStackTrace();
		 }
		return conn;
		
	}

}
