package com.ugtworld.main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

import com.ugtworld.dao.DatabaseConnector;

public class CreateStatementTest {

	public static void main(String[] args) {
		DatabaseConnector connector = new DatabaseConnector();
		Connection connection = null;
		try {
			connection = connector.getConnection();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from students");

			while (rs.next()) {
				System.out.println(rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getString(3));
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (Objects.nonNull(connection) && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
