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
		verify(myStudentsDB, times(1)).updateDB(anyString(), anyString());
		
	}
	
	

}
