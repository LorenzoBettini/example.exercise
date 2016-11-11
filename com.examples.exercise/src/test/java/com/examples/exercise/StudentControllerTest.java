package com.examples.exercise;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class StudentControllerTest {
	private StudentController studControl;
	private Database myDBInstance;

	@Before
	public void setup() {
		myDBInstance = mock(Database.class);
		studControl = new StudentController(myDBInstance);
	}

	@Test
	public void testUpdateIterationWithDB() {
		studControl.update("0000", "matteo");
		verify(myDBInstance, times(1)).exists("0000");
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
		when(myDBInstance.exists(id)).thenReturn(dbAnswer);
		boolean result = studControl.update(id, name);
		if(dbAnswer)
			verify(myDBInstance, times(1)).updateDB(id, name);
		else
			verify(myDBInstance, times(0)).updateDB(id, name);
		assertEquals(dbAnswer, result);
	}

}
