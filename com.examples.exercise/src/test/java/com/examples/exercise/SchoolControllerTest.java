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
	public void testUpdateIterationWithDB() {
		schoolController.update("0000", "matteo");
		verify(database, times(1)).exists("0000");
	}

	@Test
	public void testUpdateWhithNoExistingStudents() {
		assertUpdate(false, "0000", "matteo");
	}

	@Test
	public void testUpdateWithExistingStudents() {
		assertUpdate(true, "0000", "matteo");
	}

	private void assertUpdate(boolean dbAnswer, String id, String name) {
		when(database.exists(id)).thenReturn(dbAnswer);
		boolean result = schoolController.update(id, name);
		if(dbAnswer)
			verify(database, times(1)).updateDB(id, name);
		else
			verify(database, times(0)).updateDB(id, name);
		assertEquals(dbAnswer, result);
	}

	@Test
	public void testGetStudentByIdIterationWithDB() {
		schoolController.getStudentById("0000");
		verify(database, times(1)).takeStudentsById("0000");
	}

	@Test
	public void testGetStudentByIdWithBadIndex() {
		when(database.takeStudentsById("0000")).thenReturn(null);
		schoolController.getAllStudents();
	}
	
	@Test
	public void testAddStudentBase(){
		Student student = factoryStudent("1", "Pippo");
		when(database.exists(anyString())).thenReturn(false);
		assertTrue(schoolController.addToDB(student));
		verify(database, times(1)).add(student);
		verify(database, times(1)).add(student);

	}
	
	@Test
	public void testAddStudentWhenIsDuplicate(){
		Student student = factoryStudent("1", "Pippo");
		when(database.exists(anyString())).thenReturn(true);
		assertFalse(schoolController.addToDB(student));
		verify(database, times(1)).exists(student.getId());
		verify(database, times(0)).add(student);

	}

	
	@Test
	public void testGetStudentByIdWithCorrectedIndex() {
		Student student = factoryStudent("0000", "matteo");
		when(database.takeStudentsById("0000")).thenReturn(student);

		Student result = schoolController.getStudentById("0000");
		assertSame(student, result);
	}

	private Student factoryStudent(String id, String name) {
		Student student = new Student();
		student.setId(id);
		student.setName(name);
		return student;
	}
}
