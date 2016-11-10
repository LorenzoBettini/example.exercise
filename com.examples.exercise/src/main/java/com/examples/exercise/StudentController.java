package com.examples.exercise;

public class StudentController {
	private StudentsDB studentsDB;
	
	public StudentController(StudentsDB studentsDB){
		this.studentsDB=studentsDB;
	}

	public boolean update(String id, String name) {
		// TODO Auto-generated method stub
		return studentsDB.exists(id);
	}

}
