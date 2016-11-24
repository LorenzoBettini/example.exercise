package com.examples.exercise;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class DbMongo implements Database {

	private DBCollection students;

	public DbMongo(MongoClient mongoClient) {
		DB db = mongoClient.getDB("schoolExcercise");
		students = db.getCollection("student");

	}

	public List<Student> getAllStudentsList() {
		DBCursor cursor = students.find();
		List<Student> resultList = new ArrayList<Student>();
		if (cursor.hasNext()) {
			DBObject toAdd = cursor.next();
			resultList.add(new Student((String) toAdd.get("id"), (String) toAdd.get("name")));
		}
		return resultList;
	}

	public void updateDB(String id, String name) {
	}

	public boolean exists(String id) {
		return false;
	}

}
