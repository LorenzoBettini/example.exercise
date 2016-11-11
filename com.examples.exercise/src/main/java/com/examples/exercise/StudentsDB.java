package com.examples.exercise;

public interface StudentsDB {
	public void updateDB(String id, String name);

	public boolean exists(String id);
}
