package com.examples.exercise;

import static org.junit.Assert.*;

import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.Test;

import com.github.fakemongo.Fongo;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class DbMongoTest {
	private DbMongo database;
	private DBCollection students;
	
	@Before
	public void setUp() throws UnknownHostException{
		Fongo fongo = new Fongo("exercise server");
		MongoClient mongoClient = fongo.getMongo();
		
		//check and drop some trash
		DB db = mongoClient.getDB("schoolExcercise");
		db.getCollection("student").drop();
		
		database = new DbMongo(mongoClient);
		students = db.getCollection("student");
	}
	
	@Test
	public void testGetAllStudentsEmpty() {
		assertTrue(database.getAllStudentsList().isEmpty());
	}
	
	@Test
	public void testGetAllStudentsNotEmpty(){
		this.addStudent("1", "first");
		assertEquals(1, database.getAllStudentsList().size());
		assertEquals("1", database.getAllStudentsList().get(0).getId());
		assertEquals("first", database.getAllStudentsList().get(0).getName());
	}

	private void addStudent(String id, String name) {
		BasicDBObject document = new BasicDBObject();
		document.put("id", id);
		document.put("name", name);
		students.insert(document);
	}
	
	@Test
	public void testGetStudentByIdNotFound(){
		this.addStudent("1", "first");
		assertNull(database.takeStudentsById("2"));
	}
	
	@Test
	public void testGetStudentByIdFound(){
		this.addStudent("1", "first");
		Student found = database.takeStudentsById("1");
		assertNotNull(found);
		assertEquals("1", found.getId());
		assertEquals("first", found.getName());
	}

	
}

