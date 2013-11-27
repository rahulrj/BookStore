package com.gridants.utils;

public class Reviewobject {

	private String name;
	private String date;
	private String body;
	private String followers;

	public Reviewobject(String name, String date, String body, String followers) {
		this.name = name;
		this.date = date;
		this.body = body;
		this.followers = followers;
	}

	public void setname(String name) {
		this.name = name;
	}

	public String getname() {
		return name;
	}

	public void setdate(String date) {
		this.date = date;
	}

	public String getdate() {
		return date;
	}

	public void setbody(String body) {
		this.body = body;
	}

	public String getbody() {
		return body;
	}

	public void setfollowers(String followers) {
		this.followers = followers;
	}

	public String getfollowers() {
		return followers;
	}
}
