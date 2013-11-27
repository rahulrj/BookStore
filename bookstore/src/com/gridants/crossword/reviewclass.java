package com.gridants.crossword;

public class reviewclass {

	String user_id;
String Product_id;
	String body;
	String date;
	//String category;
	
	
	public reviewclass() {
		
		this.user_id="";
		this.Product_id="";
		this.body="";
		this.date="";
		//this.title="";
		
	}

	public String getuser() {
		return this.user_id;
	}

	public String getproduct() {
		return this.Product_id;
	}
	public String getbody() {
		return this.body;
	}
	public String getdate() {
		return this.date;
	}
	
	
}
