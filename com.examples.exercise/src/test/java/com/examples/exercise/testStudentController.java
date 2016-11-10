package com.examples.exercise;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class testStudentController {
	private StudentController studControl;
	private StudentsDB myStudentsDB;
	
	@Before
	public void setup(){
		myStudentsDB = mock(StudentsDB.class);
		studControl=new StudentController(myStudentsDB);
	}

	@Test
	public void testUpdateIterationWithDB() {
		studControl.update("00000", "matteo");
		verify(myStudentsDB, times(1)).exists(anyString());
		
	}
	
	@Test
	public void testUpdateWhithNoExistingStudents(){
		assertUpdate(false, 0);
	}

	@Test
	public void testUpdateWithExistingStudents(){
		assertUpdate(true, 1);
	}
	
	private void assertUpdate(boolean dbAnswer, int expectedVerify) {
		when(myStudentsDB.exists("0000")).thenReturn(dbAnswer);
		boolean result=studControl.update("0000","matteo");
		verify(myStudentsDB, times(expectedVerify)).updateDB(anyString(), anyString());
		assertEquals(dbAnswer, result);
	}

}
