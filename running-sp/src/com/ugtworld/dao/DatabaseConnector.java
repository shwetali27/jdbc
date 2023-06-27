package com.ugtworld.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class DatabaseConnector {
	
	private Connection con; 
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		// Checking if object is null or connection is already closed.
		if(Objects.isNull(con) || con.isClosed()) {
			System.out.println("<=== Creating new connection ===>");
			/* DriverManager.getConnection("jdbc:mysql://<host>:<db-port>/<schema-name>","<user-name>", "<password>"); */
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_schema1", "ugt1", "Welcome@123");
		}
		return con;
	}
}

