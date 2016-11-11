package com.examples.exercise;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class testStudentController {
	private StudentController studControl;
	private StudentsDB myStudentsDB;

	@Before
	public void setup() {
		myStudentsDB = mock(StudentsDB.class);
		studControl = new StudentController(myStudentsDB);
	}

	@Test
	public void testUpdateIterationWithDB() {
		studControl.update("00000", "matteo");
		verify(myStudentsDB, times(1)).exists(anyString());

	}

	@Test
	public void testUpdateWhithNoExistingStudents() {
		assertUpdate(false, 0, "0000", "matteo");
	}

	@Test
	public void testUpdateWithExistingStudents() {
		assertUpdate(true, 1, "0000", "matteo");
	}

	private void assertUpdate(boolean dbAnswer, int expectedVerify, String id, String name) {
		when(myStudentsDB.exists(id)).thenReturn(dbAnswer);
		boolean result = studControl.update(id, name);
		verify(myStudentsDB, times(expectedVerify)).updateDB(anyString(), anyString());
		assertEquals(dbAnswer, result);
	}

}
