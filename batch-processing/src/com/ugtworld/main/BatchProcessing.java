package com.ugtworld.main;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.ugtworld.dao.DatabaseConnector;
import com.ugtworld.dto.Student;

public class BatchProcessing {
	public static void main(String[] args) {
		Connection connection = null;
		try {
			DatabaseConnector connector = new DatabaseConnector();
			connection = connector.getConnection();
			boolean batchProcessing = checkBatchProcessing(connection);
			System.out.println("batch processing: " + batchProcessing);
			if(batchProcessing) {
				processBatchUsingCreateStatement(connection);
				processBatchUsingPreparedStatement(connection);
			}
		} catch (Exception e) {
			try {
				if (Objects.nonNull(connection) && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static boolean checkBatchProcessing(Connection connection) throws SQLException {
		System.out.println("Checking if batch processing is supported");
		DatabaseMetaData metaData = connection.getMetaData();
		return metaData.supportsBatchUpdates();
	}

	public static void processBatchUsingCreateStatement(Connection connection) throws SQLException {
		System.out.println("Batch processing using create statement");
		List<Student> students = new ArrayList<>();
		students.add(new Student(7, "Kush", 3, "M",340));
		students.add(new Student(8, "Jennie", 4, "F",322));
		students.add(new Student(9, "Kai", 3, "M", 387));
		students.add(new Student(10, "Chen", 4, "M", 376));

		Statement stmt = connection.createStatement();

		students.forEach(s -> {
			try {
				String query = "Insert into students(roll_num, name, class, gender, marks) values "
						+ "('"+s.getRollNumber()+"','"+s.getName()+"'"
								+ ",'"+s.getInClass()+"','"+s.getGender()+"','"+s.getMarks()+"')";
				// adding query to batch
				stmt.addBatch(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});
		
		// int[] to hold returned values
		int[] count = stmt.executeBatch();
		System.out.println(Arrays.toString(count));
	}
		
	
	public static void processBatchUsingPreparedStatement(Connection connection) throws SQLException {
		System.out.println("Batch processing using prepared statement");
		List<Student> students = new ArrayList<>();
		students.add(new Student(11, "Linda", 3, "F",290));
		students.add(new Student(12, "Jeet", 4, "M",310));
		students.add(new Student(13, "Kisan", 3, "M", 238));
		students.add(new Student(14, "Luv", 4, "M", 283));
		String query = "Insert into students(roll_num, name, class, gender, marks) values (?,?,?,?,?)";
		PreparedStatement pStmt = connection.prepareStatement(query);

		students.forEach(s -> {
			try {
				pStmt.setInt(1, s.getRollNumber());
				pStmt.setString(2, s.getName());
				pStmt.setInt(3, s.getInClass());
				pStmt.setString(4, s.getGender());
				pStmt.setInt(5, s.getMarks());
				
				// adding statement to batch
				pStmt.addBatch();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});
		
		int[] count = pStmt.executeBatch();
		System.out.println(Arrays.toString(count));
	}
		
}
