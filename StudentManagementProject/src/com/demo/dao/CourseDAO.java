package com.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.demo.exception.DublicateData;
import com.demo.model.Course;

public class CourseDAO {
    public static void addCourse(Course course) throws DublicateData {
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "INSERT INTO course (courseId, courseName, instructor) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, course.getCourseID());
                preparedStatement.setString(2, course.getCourseName());
                preparedStatement.setString(3, course.getInstructor());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception or throw a custom exception
			throw new DublicateData("Error while inserting ");

        }
    }
    public static int getEnrolledStudentCount(int courseID) {
        int count = 0;
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "SELECT COUNT(*) FROM course_enrollment WHERE courseId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, courseID);

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

    public static void enrollStudentInCourse(int enrollmentId,int studentID, int courseID) {
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "INSERT INTO course_enrollment (enrollment_id,studentId, courseId) VALUES (?,?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                int enrollment_id = 0;
				preparedStatement.setInt(1,enrollment_id);
                preparedStatement.setInt(2, studentID);
                preparedStatement.setInt(3, courseID);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
//            e.printStackTrace();
            // Handle the exception or throw a custom exception
            System.out.println("error");
        }
    }
}


