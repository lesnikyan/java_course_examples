/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package generics;

/**
 *
 * @author Less
 */
public class SimpleGeneric<T> {
	
	private T obj;
	
	public SimpleGeneric(T obj){
		this.obj = obj;
	}
	
	public T get(){
		return obj;
	}
	
	public void showType(){
		System.out.println("Type: " + obj.getClass().toString());
	}
	
}

// наследование обобщенных типов
class SubSimpleGeneric<T> extends SimpleGeneric<T>{
	SubSimpleGeneric(T obj){
		super(obj);
	}
}

class TargetSimpleGenneric extends SimpleGeneric<String>{
	
	public TargetSimpleGenneric(){
		this("");
	}

	public TargetSimpleGenneric(String obj) {
		super(obj);
	}
	
}
