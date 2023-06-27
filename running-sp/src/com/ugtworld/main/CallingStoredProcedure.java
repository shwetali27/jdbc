package com.ugtworld.main;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import com.ugtworld.dao.DatabaseConnector;

public class CallingStoredProcedure {
	public static void main(String[] args) {
		addition();
		division();
	}
	
	private static void addition() {
		Connection connection = null;
		try {
			
			DatabaseConnector connector = new DatabaseConnector();
			connection = connector.getConnection();
			
			CallableStatement stmnt = connection.prepareCall("{call add_numbers(?,?)}");
			stmnt.setInt(1, 20);
			stmnt.setInt(2, 34);
			
			ResultSet rs = stmnt.executeQuery();
			if(rs.next()) {			
				System.out.println("Addition is: " + rs.getInt(1));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				if (Objects.nonNull(connection) && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	private static void division() {

		Connection connection = null;
		try {
			
			DatabaseConnector connector = new DatabaseConnector();
			connection = connector.getConnection();

			
			CallableStatement stmnt = connection.prepareCall("{call perform_division(?,?,?)}");	
			stmnt.setInt(1, 20);
			stmnt.setInt(2, 34);
			stmnt.registerOutParameter(3, java.sql.Types.VARCHAR);
			
			stmnt.execute();
			System.out.println("result is: " + stmnt.getString(3));
			
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				if (Objects.nonNull(connection) && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	
	}
}
