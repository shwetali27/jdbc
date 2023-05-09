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
			for (int i = 1; i <= 5; i++) {
				System.out.println("\nGetting connection for i = " +i);
				con = connector.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select * from students where roll_num = " + i);

				if (rs.next()) {
					System.out.println(rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getString(3));
				}else {
					System.out.println("closing the connection for i = " + i);
					con.close();
				}
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
