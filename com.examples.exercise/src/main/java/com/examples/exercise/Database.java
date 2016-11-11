package com.examples.exercise;

public interface Database {
	public void updateDB(String id, String name);

	public boolean exists(String id);
}
