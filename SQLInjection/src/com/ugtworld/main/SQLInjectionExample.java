package com.ugtworld.main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.ugtworld.dao.DatabaseConnector;

public class SQLInjectionExample {
	public static void main(String[] args) {
		
		SQLInjectionExample sqlInjectionExample = new SQLInjectionExample();
		String inputByUser = "Linda' or '1' = '1";
		List<String> output = sqlInjectionExample.getStudentDetails(inputByUser);
		System.out.println("---- output ----");
		output.forEach(System.out::println);
		
	}

	public List<String> getStudentDetails(String name) {
		List<String> list = new ArrayList<String>();

		DatabaseConnector connector = new DatabaseConnector();
		Connection connection = null;
		try {
			connection = connector.getConnection();
			Statement stmt = connection.createStatement();
			String query = "select name, marks from students where name = ?1";
			System.out.println("query: " + query);
			ResultSet resultSet = stmt.executeQuery(query);

			while (resultSet.next()) {
				list.add(resultSet.getString(1) 
						+ " | " + resultSet.getString(2));
			}

			resultSet.close();
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

		return list;
	}
}