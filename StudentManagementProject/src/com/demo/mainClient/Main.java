package com.demo.mainClient;
import com.demo.dao.CourseDAO;
import com.demo.dao.DBConnection;
import com.demo.dao.StudentDAO;
import com.demo.exception.DatabaseNotFound;
import com.demo.exception.DublicateData;
import com.demo.exception.StudentNotFounndException;
import com.demo.model.Course;
import com.demo.model.Student;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;
public class Main {
	static Scanner scanner = new Scanner(System.in);
         public static void main(String[] args)
			throws ClassNotFoundException, StudentNotFounndException, SQLException, DatabaseNotFound, DublicateData {
	     int choice = 0;
		while (choice != 8008) {
			System.out.println("welcome to student management system");
			System.out.println("please authenticate system");
			System.out.println("Enter choice :\n 1: Login \n 2:Signup \n 3:EXIT");
			int menu = scanner.nextInt();
			switch (menu) {
			case 1:
				System.out.println("Login");
				while (true) {
					System.out.println("Student Management System");
					System.out.println("1. Insert a new student");
					System.out.println("2. Find a student by ID");
					System.out.println("3. Count the number of students");
					System.out.println("4. Delete a Student by ID");
					System.out.println("5. Update a Student (name)");
					System.out.println("6. Inset into a new course");
					System.out.println("7. Inset into a new enrollement_course");
					System.out.println("8. Student enrollecd in particular course");
					System.out.println("9. students with more than 450 marks ");
					System.out.println("10. Exit");
					System.out.print("Enter your choice: ");
					int choice1 = scanner.nextInt();

					switch (choice1) {
					case 1:
						// Insert a new student
						System.out.print("Enter id: ");
						int studentId = scanner.nextInt();
						System.out.print("Enter first name: ");
						String firstName = scanner.next();
						System.out.print("Enter last name: ");
						String lastName = scanner.next();
						System.out.print("Enter marks: ");
						int marks = scanner.nextInt();
						System.out.print("Enter Branch ");
						String branch = scanner.next();

						Student newStudent = new Student();
						newStudent.setStudentId(studentId);
						newStudent.setFirstName(firstName);
						newStudent.setLastName(lastName);
						newStudent.setMarks(marks);
						newStudent.setBranch(branch);
						StudentDAO.insertStudent(newStudent);
						System.out.println("Student inserted successfully!");
						break;
					case 2:
						System.out.print("Enter student ID: ");
						int stuId = scanner.nextInt();

						try {
							Optional<Student> foundStudentOptional = StudentDAO.getStudentById(stuId);
							if (foundStudentOptional.isPresent()) {
								Student foundStudent = foundStudentOptional.get();
								System.out.println("Found Student: " + foundStudent.getStudentId() + " "
										+ foundStudent.getFirstName() + " " + foundStudent.getLastName() + ""
										+ foundStudent.getMarks() + "" + foundStudent.getBranch());
							} else {
								System.out.println("Student not found with ID: " + stuId);
							}
						} catch (StudentNotFounndException e) {
							System.out.println("Error: " + e.getMessage());
						}

						break;

					case 3:
						// Count the number of students

						Connection connection = DBConnection.getConnection();
						int studentCount = StudentDAO.countStudents(connection);
						System.out.println("Number of students: " + studentCount);
						connection.close();

						break;

					case 4:
						// Delete a student by ID
						System.out.println("Enter student ID to delete:");
						int studentIdToDelete = scanner.nextInt();
						scanner.nextLine(); // Consume the newline character

						StudentDAO.deleteStudent(studentIdToDelete);
						System.out.println("Student deleted successfully!");
						break;

					case 5:
						System.out.print("Enter student ID to update: ");
						int studentId1 = scanner.nextInt();

						try {
							Optional<Student> studentToUpdate = StudentDAO.getStudentById(studentId1);

							if (studentToUpdate.isPresent()) {
								// Student with the given ID exists
								Student student = studentToUpdate.get();
								System.out.println("Enter new first name:");
								String updatedFirstName = scanner.next(); // Use scanner.next() to read the input
								System.out.println("Enter new last name:");
								String updatedLastName = scanner.next();

								// Update the student object
								student.setFirstName(updatedFirstName);
								student.setLastName(updatedLastName);

								// Update the student in the database
								StudentDAO.updateStudent(student);
								System.out.println("Student updated successfully!");
							} else {
								// Student with the given ID was not found
								System.out.println("Student not found!");
							}
						} catch (StudentNotFounndException e) {
							// Handle the custom exception if the student is not found
							System.out.println("Error: " + e.getMessage());
						} finally {
							// Any cleanup code can go here
						}
					break;
					 case 6:
		                    // Example of adding a course
		                    System.out.println("Enter course ID:");
		                    int courseID = scanner.nextInt();
		                    System.out.println("Enter course name:");
		                    String courseName = scanner.next();
		                    System.out.println("Enter instructor:");
		                    String instructor = scanner.next();

		                    Course course = new Course(courseID, courseName, instructor);
		                    CourseDAO.addCourse(course);
		                    System.out.println("Course added successfully!");
		                    break;

		                case 7:
		                    // Example of enrolling a student in a course
		                    System.out.println("Enter enrollement ID:");
		                    int enrollmentId = scanner.nextInt();
		                    System.out.println("Enter student ID:");
		                    int studentID = scanner.nextInt();
		                    System.out.println("Enter course ID:");
		                    int courseIDToEnroll = scanner.nextInt();

		                    CourseDAO.enrollStudentInCourse(enrollmentId,studentID,courseIDToEnroll);
		                    System.out.println("Student enrolled in the course!");
		                    break;
		                case 8:
		                    // Generate a report on the number of students enrolled in a particular course
		                    System.out.println("Enter course ID:");
		                    int reportCourseID = scanner.nextInt();

		                    int enrolledStudentCount = CourseDAO.getEnrolledStudentCount(reportCourseID);
		                    System.out.println("Number of students enrolled in the course: " + enrolledStudentCount);
		                    break;
		                case 9:
		                    // Generate a report on students with more than 450 marks
		                    int studentsWithMoreThan450 = StudentDAO.getStudentsWithMoreThan450Marks();
		                    System.out.println("Number of students with more than 450 marks: " + studentsWithMoreThan450);
		                    break;
		       
					case 10:
						System.out.println("Exiting the program. Goodbye!");
						System.exit(0);
						break;

					default:
						System.out.println("Invalid choice. Please enter a valid option.");
					break;
				}}
             
			case 2:
				System.out.println("Signup sucessful");
				break;

			case 3:
				System.out.println("exit");
			}
}
}
}
