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
	public void testGetStudentByIdIterationWithDB(){
		schoolController.getStudentById("0000");
		verify(database, times(1)).takeStudentsById("0000");
	}
	
	@Test
	public void testGetStudentByIdWithBadIndex(){
		when(database.takeStudentsById("0000")).thenReturn(null);
	}

}
