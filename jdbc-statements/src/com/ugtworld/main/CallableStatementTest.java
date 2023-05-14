package com.ugtworld.main;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import com.ugtworld.dao.DatabaseConnector;

public class CallableStatementTest {

	public static void main(String[] args) {
		DatabaseConnector connector = new DatabaseConnector();
		Connection connection = null;
		try {
			connection = connector.getConnection();
			String query = "call perform_division(?, ?)";
			CallableStatement callableStatement = connection.prepareCall(query);
			callableStatement.setInt(1, 200); // setting first '?' value
			callableStatement.setInt(2, 5);// setting second '?' value
			
			ResultSet rs = callableStatement.executeQuery();
			if (rs.next()) {
				System.out.println(rs.getInt(1));
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
