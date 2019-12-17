package com.as.samples;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class databaseconn {
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:Xe","system","chandra");
	
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		}
		return con;

	}
	
}
