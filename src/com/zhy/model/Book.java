package com.zhy.model;

import java.io.Serializable;

public class Book implements Serializable {
	private Integer id;				// id	
	private String name;			// 书名
	private String author;			// 作者
	private Double price;			// 价格
	private String image;			// 封面图片
	public Book() {
		super();
	}
	public Book(String name, String author, Double price, String image) {
		super();
		this.name = name;
		this.author = author;
		this.price = price;
		this.image = image;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
