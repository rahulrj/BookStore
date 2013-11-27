package com.gridants.utils;

public class ItemDetails {

	public ItemDetails(String name, String author, String price, String price2,

			String imageNumber,String description) {

		this.name = name;
		this.author = author;
		this.price = price;
		this.price2 = price2;
		this.imageNumber = imageNumber;

		this.description=description;
		

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPrice2() {
		return price2;
	}

	public void setPrice2(String price2) {
		this.price2 = price2;
	}

	public String getImageNumber() {
		return imageNumber;
	}

	public void setImageNumber(String imageNumber) {
		this.imageNumber = imageNumber;
	}

	
	public void setdescription(String description) {
		this.description = description;
	}
	
	public String getdescription(String description) {
		return description;
	}


	private String name;
	private String author;
	private String price;
	private String price2;
	private String imageNumber;

	private String description;


}