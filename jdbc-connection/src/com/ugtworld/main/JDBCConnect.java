package com.ugtworld.main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ugtworld.dao.DatabaseConnector;

public class JDBCConnect {

	public static void main(String[] args) {
		DatabaseConnector connector = new DatabaseConnector();
		Connection con;
		try {
			con = connector.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from students");

			while (rs.next()) {
				System.out.println(rs.getInt("roll_num") + "  " + rs.getString("name") + "  " + rs.getString("marks"));
			}
			
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}
}