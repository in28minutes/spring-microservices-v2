package com.in28minutes.microservices.currencyconversionservice.student;

public class Student {

	private final int studentId;
	private final String studentName;
	
	public Student(int studentId, String studentName) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
	}


	public int getStudentId() {
		return studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	
	
}
