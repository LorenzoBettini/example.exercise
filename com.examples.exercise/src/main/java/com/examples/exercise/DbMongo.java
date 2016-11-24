package com.examples.exercise;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class DbMongo implements Database {
	
	public DbMongo(MongoClient mongoClient) {
		
	}

	public List<Student> getAllStudentsList() {
		List<Student> students = new ArrayList<Student>();
		return students;
	}

	public void updateDB(String id, String name) {
	}

	public boolean exists(String id) {
		return false;
	}

}
