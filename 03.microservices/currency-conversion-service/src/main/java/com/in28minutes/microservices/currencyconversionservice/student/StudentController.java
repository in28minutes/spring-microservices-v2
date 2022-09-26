package com.in28minutes.microservices.currencyconversionservice.student;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path ="api/v1/student")
public class StudentController {

	private static List<Student> STUDENTS=Arrays.asList(new Student(1,"imran"),new Student(2,"vishakh"));
	
	
	
	@GetMapping(path="{studentId}")
	public Student getStudent(@PathVariable("studentId") int studentId) {
		return STUDENTS.stream().filter(student->student.getStudentId()==studentId)
				.findFirst()
				.orElseThrow(()->new IllegalStateException("student" +studentId));
	}
}
