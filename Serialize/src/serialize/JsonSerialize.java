/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



package serialize;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Less
 */
public class JsonSerialize {
	
	Gson gson = new Gson();
        
        String serializedData = "";
	
	public void test(){
		serialize();
		unserialize();
                serializeComplex();
                unserializeComplex();
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
	
	public void serializeComplex(){
		Book book = new Book("Girls, girls, girls", "Kasanova Piterskiy", 2000);
		String data = gson.toJson(book);
		p(data);
                List<LittleObject> objects = new ArrayList<>();
                Type type = (new TypeToken<List<LittleObject>>(){}).getType();
                for(int i=0; i< 5; ++i){
                    objects.add(new LittleObject("little_" + i, i, i%2 == 0));
                }
                String dataList = gson.toJson(objects, type);
                p(dataList);
                serializedData = dataList;
	}
	
	public void unserializeComplex(){
		String data = "{\"name\":\"Guns and Roses in desert\", \"author\":\"Sting\", \"year\":2010}";
		Book book = gson.fromJson(data, Book.class);
		p(book.getInfo());
                Type type = (new TypeToken<List<LittleObject>>(){}).getType();
                List<LittleObject> list = gson.fromJson(serializedData, type);
                p(list.get(0).name);
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

class LittleObject {
    String name;
    int num;
    boolean status;
    
    public LittleObject(){
        
    }
    
    public LittleObject(String name, int num, boolean status){
        this.name = name;
        this.num = num;
        this.status = status;
    }
}