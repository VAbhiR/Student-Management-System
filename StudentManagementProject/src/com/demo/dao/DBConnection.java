package com.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	  public static Connection getConnection() throws SQLException {
	        String url = "jdbc:mysql://localhost:3306/studentdb1";
	        String username = "root";
	        String password = "Chikki@123";
	        return DriverManager.getConnection(url, username, password);
}

}
