package com.gridants.crossword;

public class Country {

	String category;
String author;
	String isbn;
	String title;
	//String category;
	
	String publisher;
	int id;
	String Price2,Price;
	String category2;
	String description;
	String imageUrl;
	
	
	public Country() {
		
		this.category="";
		this.id=0;
		this.isbn="";
		this.author="";
		this.title="";
		
		this.publisher="";
		this.Price="";
		this.Price2="";
		this.category2="";
		this.description="";
		this.imageUrl="";
	}

	public String getcategory() {
		return this.category;
	}

	public String getisbn() {
		return this.isbn;
	}
	public String getauthor() {
		return this.author;
	}
	public String gettitle() {
		return this.title;
	}
	
	public String getpublisher() {
		return this.publisher;
	}
	public int getid() {
		return this.id;
	}
	
	public String getPrice() {
		return this.Price;
	}
	public String getPrice2(){
		return this.Price2;
	}
	public String getcategory2(){
		return this.category2;
	}
	
	public String getdescription(){
		return this.description;
	}
	public String getimageUrl(){
		return this.imageUrl;
	}
}
