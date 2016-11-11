package com.examples.exercise;

public class StudentController {
	private Database dbInstance;

	public StudentController(Database dbInstance) {
		this.dbInstance = dbInstance;
	}

	public boolean update(String id, String name) {
		boolean dbAnswer = dbInstance.exists(id);
		if (dbAnswer)
			dbInstance.updateDB(id, name);
		return dbAnswer;
	}

}
