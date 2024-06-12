package com.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Course {
	    private int courseID;
	    private String courseName;
	    private String instructor;
	    private List<Student> enrolledStudents;

		public Course(int courseID, String courseName, String instructor) {
			super();
			this.courseID = courseID;
			this.courseName = courseName;
			this.instructor = instructor;
			this.enrolledStudents = new ArrayList<>();
		}
		public int getCourseID() {
			return courseID;
		}
		public void setCourseID(int courseID) {
			this.courseID = courseID;
		}
		public String getCourseName() {
			return courseName;
		}
		public void setCourseName(String courseName) {
			this.courseName = courseName;
		}
		public String getInstructor() {
			return instructor;
		}
		public void setInstructor(String instructor) {
			this.instructor = instructor;
		}
		
		  public void enrollStudent(Student student) {
		        enrolledStudents.add(student);
		        System.out.println(student.getFirstName() + " " + student.getLastName() + " enrolled in " + courseName);
		    }

		    public void printEnrolledStudents() {
		        System.out.println("Students enrolled in " + courseName + ":");
		        for (Student student : enrolledStudents) {
		            System.out.println(student.getFirstName() + " " + student.getLastName());
		        }
		    }
		
		
		
		@Override
		public String toString() {
			return "Course [courseID=" + courseID + ", courseName=" + courseName + ", instructor=" + instructor + "]";
		}
	    
}
