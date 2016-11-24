package com.examples.exercise;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class SchoolControllerTest {
	private Database database;
	private List<Student> students;
	private SchoolController schoolController;

	@Before
	public void setUp() throws Exception {
		database = mock(Database.class);
		schoolController = new SchoolController(database);
		students = new ArrayList<Student>();
		when(database.getAllStudentsList()).thenReturn(students);
	}

	@Test
	public void testGetAllStudentsWhenThereAreNoStudents() {
		List<Student> allStudents = schoolController.getAllStudents();
		verify(database, times(1)).getAllStudentsList();
		assertEquals(0, allStudents.size());
	}

	@Test
	public void testGetAllStudentsWhenThereIsOneStudent() {
		students.add(new Student());
		List<Student> allStudents = schoolController.getAllStudents();
		verify(database, times(1)).getAllStudentsList();
		assertEquals(1, allStudents.size());	
	}
	
	@Test
	public void testAddStudentBase(){
		Student student = factoryStudent("1", "Pippo");
		verify(database, times(1)).add(student);
		assertTrue(schoolController.addToDB(student));
	}
	
	@Test
	public void testAddStudentWhenIsDuplicate(){
		Student student = factoryStudent("1", "Pippo");
		when(database.exists(anyString())).thenReturn(false);
		verify(database, times(1)).exists(student.getId());
		assertFalse(schoolController.addToDB(student));
	}
	

	private Student factoryStudent(String id, String name) {
		Student student = new Student();
		student.setId(id);
		student.setName(name);
		return student;
	}
	
}
