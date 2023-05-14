package com.ugtworld.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import com.ugtworld.dao.DatabaseConnector;

public class PreparedStatementTest {

	public static void main(String[] args) {
		DatabaseConnector connector = new DatabaseConnector();
		Connection connection = null;
		try {
			connection = connector.getConnection();
			String query = "select * from students where roll_num = ? and name = ?";
			PreparedStatement pr = connection.prepareStatement(query);
			pr.setInt(1, 5); // setting first '?' value
			pr.setString(2, "Chen");// setting second '?' value
			
			ResultSet rs = pr.executeQuery();
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
