package com.examples.exercise;

import static org.junit.Assert.*;

import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.Test;

import com.github.fakemongo.Fongo;
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
	public void test() {
		fail("Not yet implemented");
	}

}
