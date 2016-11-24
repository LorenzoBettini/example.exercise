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

	public boolean addToDB(Student student) {

		database.add(student);
		return true;
	}

}
