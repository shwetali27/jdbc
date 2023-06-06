package com.ugtworld.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.ugtworld.dao.DatabaseConnector;

public class ParameterizedQueryExample {

	public static void main(String[] args) {
		
		ParameterizedQueryExample parameterizedQueryExample = new ParameterizedQueryExample();
		String inputByUser = "Linda' or '1' = '1";
		List<String> output = parameterizedQueryExample.getStudentDetails(inputByUser);
		System.out.println("---- output ---- for \"" + inputByUser +"\"");
		output.forEach(System.out::println);
		
		System.out.println("------------------------------");
		String inputByUser2 = "Linda";
		List<String> output2 = parameterizedQueryExample.getStudentDetails(inputByUser2);
		System.out.println("---- output ---- for \"" + inputByUser2 +"\"");
		output2.forEach(System.out::println);
	}

	public List<String> getStudentDetails(String name) {
		List<String> list = new ArrayList<String>();

		DatabaseConnector connector = new DatabaseConnector();
		Connection connection = null;
		try {
			connection = connector.getConnection();
			
			String query = "select name, marks from students where name = ?";
			PreparedStatement pStmt = connection.prepareStatement(query);
			pStmt.setString(1, name);
			ResultSet resultSet = pStmt.executeQuery();

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