package com.examples.exercise;

import java.util.List;

import com.mongodb.MongoClient;

public class DbMongo implements Database {

	public DbMongo(MongoClient mongoClient) {
		
	}

	public List<Student> getAllStudentsList() {
		return null;
	}

	public void updateDB(String id, String name) {
	}

	public boolean exists(String id) {
		return false;
	}

}
