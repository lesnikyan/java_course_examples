/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



package serialize;

import com.google.gson.Gson;

/**
 *
 * @author Less
 */
public class JsonSerialize {
	
	Gson gson = new Gson();
	
	public void test(){
		serialize();
		unserialize();
	}
	
	public void serialize(){
		Book book = new Book("Girls, girls, girls", "Kasanova Piterskiy", 2000);
		String data = gson.toJson(book);
		p(data);
	}
	
	public void unserialize(){
		String data = "{\"name\":\"Guns and Roses in desert\", \"author\":\"Sting\", \"year\":2010}";
		Book book = gson.fromJson(data, Book.class);
		p(book.getInfo());
	}
	
	public static void p(Object x){
		System.out.println(x);
	}
}

class Book {
	private String name;
	private String author;
	private int year;
	
	Book(String name, String author, int year){
		this.name = name;
		this.author = author;
		this.year = year;
	}
	
	public String getInfo(){
		return author + ". " + name +  ". " + year + ".";
	}
}
