package com.in28minutes.microservices.currencyconversionservice.student;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path ="api/v1/student")
public class StudentManagementController {

	private static List<Student> STUDENTS=Arrays.asList(new Student(1,"imran"),new Student(2,"vishakh"));
	
	@GetMapping
	public List<Student> getAllStudent(){
		return STUDENTS;
	}
	
	@PostMapping
	public void newStudent(@RequestBody Student student)
	{
		System.out.println("added student id:" + student.getStudentId());

	}	
	
	@DeleteMapping(path="{studentId}")
	public void deleteStudent(@PathVariable("studentId") int studentId)
	{
		System.out.println("deleted student id:" + studentId);
		
	}	
	
	@PutMapping(path="{studentId}")
	public void updateStudent(@PathVariable("studentId") int studentId,@RequestBody Student student)
	{
		System.out.println(" update student id:" + student.getStudentId());
	}
}
