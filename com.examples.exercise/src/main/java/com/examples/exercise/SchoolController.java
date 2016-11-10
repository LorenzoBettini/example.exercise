package com.examples.exercise;

import java.util.ArrayList;
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

}
