package com.examples.exercise;

import java.util.List;

public class SchoolController {
	private Database database;

	public SchoolController(Database database) {
		super();
		this.database = database;
	}

	public List<Student> getAllStudents() {
		return database.getAllStudentsList();
	}

	public Student getStudentById(String id) {
		// TODO Auto-generated method stub
		return database.takeStudentsById(id);
	}
	

}
