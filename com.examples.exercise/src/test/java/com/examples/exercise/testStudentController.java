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
		studControl.update("0000", "matteo");
		verify(myStudentsDB, times(1)).exists("0000");
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
		when(myStudentsDB.exists(id)).thenReturn(dbAnswer);
		boolean result = studControl.update(id, name);
		if(dbAnswer)
			verify(myStudentsDB, times(1)).updateDB(id, name);
		else
			verify(myStudentsDB, times(0)).updateDB(id, name);
		assertEquals(dbAnswer, result);
	}

}
