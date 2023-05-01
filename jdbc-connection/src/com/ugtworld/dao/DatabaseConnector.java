package com.ugtworld.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		// DriverManager.getConnection("jdbc:mysql://<host>:<db-port>/<schema-name>", "<user-name>", "<password>");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_schema1", "ugt1", "Welcome@123");
		return con;
	}
}