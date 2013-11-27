package com.gridants.utils;

public class StringIdentifier {

	String parent;
	String home;
	String category;

	// Constructor
	public StringIdentifier(String input) {
		parent = input;
	}

	public int getValue() {
		int activityId = 0;

		if (parent.equalsIgnoreCase("Home Activity")) {
			activityId = 1;
		} else if (parent.equalsIgnoreCase("Category Activity")) {
			activityId = 2;
		}
		return activityId;
	}

}