package com.ugtworld.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

import com.ugtworld.dao.DatabaseConnector;

public class TransactionManagement {
	public static void main(String[] args) throws SQLException {
		TransactionManagement transactionManagement = new TransactionManagement();
		String name = "Jay", gender = "M";
		int inClass = 5, rollNum = 6;
		int marks = 320; 
		transactionManagement.saveUpdateStudentsDetails(rollNum, name, marks, inClass, gender);
	}

	public void saveUpdateStudentsDetails(int rollNum, String name, int marks, int inClass, String gender) throws SQLException {
		DatabaseConnector connector = new DatabaseConnector();
		Connection connection = null;
		try {
			connection = connector.getConnection();
			// setting autocommit as false
			connection.setAutoCommit(false);
			String query = "Insert into students(roll_num, name, class, gender) values (?,?,?,?)";
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, rollNum);
			stmt.setString(2, name);
			stmt.setInt(3, inClass);
			stmt.setString(4, gender);
			int insert = stmt.executeUpdate();

			if(insert > 0) {
				System.out.println("entry added for student successfully");
			}

			if(marks < 0) {
				throw new Exception("Invalid marks : " + marks);
			}else {
				String updateQuery = "update students set marks = ? where roll_num = ?";
				PreparedStatement updateStmt = connection.prepareStatement(updateQuery);
				updateStmt.setInt(1, marks);
				updateStmt.setInt(2, rollNum);
				int update= updateStmt.executeUpdate();
				if(update>0) {
					System.out.println("Marks updated for student");
				}
			}
			// commiting the changes
			connection.commit();
		} catch (Exception e) {
			// rollback the changes
			connection.rollback();
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
