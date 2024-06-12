package com.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import com.demo.exception.StudentNotFounndException;
import com.demo.model.Student;

public class StudentDAO {
	// Insert a new student
	public static void insertStudent(Student student) {
		try (Connection connection = DBConnection.getConnection()) {
			String query = "INSERT INTO student (studentid,firstName,lastName,marks,branch) VALUES (?,?,?,?,?)";
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				preparedStatement.setInt(1, student.getStudentId());
				preparedStatement.setString(2, student.getFirstName());
				preparedStatement.setString(3, student.getLastName());
				preparedStatement.setInt(4, student.getMarks());
				preparedStatement.setString(5, student.getBranch());
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			System.out.println("error");
			
		}
	}

	public static Optional<Student> getStudentById(int studentId) throws StudentNotFounndException {
		String query = "SELECT * FROM student WHERE studentId = ?";

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			// Set the student ID for the prepared statement
			preparedStatement.setInt(1, studentId);

			// Execute the query to retrieve the student
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				// Check if the result set has a row
				if (resultSet.next()) {
					// Create a new Student object and populate its properties from the result set
					Student student = new Student();
					student.setStudentId(resultSet.getInt("studentid"));
					student.setFirstName(resultSet.getString("firstName"));
					student.setLastName(resultSet.getString("lastName"));
					student.setMarks(resultSet.getInt("marks"));
					student.setBranch(resultSet.getString("branch"));
					return Optional.of(student);
				}else {
					System.out.println("dublicate value");				}
			}

			// If no student is found, return an empty Optional
			return Optional.empty();
		} catch (SQLException e) {
			// Log or handle the SQLException as needed
			e.printStackTrace();
			// Rethrow a custom exception if desired
			throw new StudentNotFounndException("Error while retrieving student with ID " + studentId);
		}
	}

	public static  int getStudentsWithMoreThan450Marks() {
        int count = 0;
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "SELECT COUNT(*) FROM student WHERE marks > 450";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        count = resultSet.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception or throw a custom exception
        }
        return count;
    }
	public static void updateStudent(Student student) throws StudentNotFounndException {
		String query = "UPDATE student SET firstName = ?, lastName = ? WHERE studentid = ?";

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			// Set values for the prepared statement using the student object
			preparedStatement.setString(1, student.getFirstName());
			preparedStatement.setString(2, student.getLastName());
			preparedStatement.setInt(3, student.getStudentId());

			// Execute the update to modify the student's information
			int rowsAffected = preparedStatement.executeUpdate();

			// Check if the update was successful
			if (rowsAffected == 0) {
				// If no rows were affected, the student with the specified ID was not found
				throw new StudentNotFounndException("Student with ID " + student.getStudentId() + " not found.");
			}
		} catch (SQLException e) {
			// Handle SQL exceptions as needed
			e.printStackTrace();
		}
	}

	// Delete a student by ID
	public static void deleteStudent(int studentId) {
		// Implementation similar to insertStudent, but with DELETE statement
		// Handle exceptions as needed
		String query = "DELETE FROM student WHERE studentid = ?";
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			// Set the student ID for the prepared statement
			preparedStatement.setInt(1, studentId);
			// Execute the update to delete the student
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// Handle exceptions as needed
			e.printStackTrace();
		}
	}

	public static int countStudents(Connection connection) throws SQLException {
//			  try (Connection connection = DBConnection.getConnection()){
		String query = "SELECT COUNT(*) FROM student";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query);

				// Execute the query to retrieve the student
				ResultSet resultSet = preparedStatement.executeQuery()) {
			// Check if the result set has a row
			if (resultSet.next()) {
				// Return the count of students
				return resultSet.getInt(1);
			}
			return 0;
			// TODO Auto-generated method stub

		}

	}
}
