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
public class DualGeneric <T,V>{
	
	private T t;
	private V v;
	
	DualGeneric(T t1, V v1){
		t = t1;
		v =  v1;
	}
	
	public void showType(){
		System.out.println("Type 1: " + t.getClass().toString());
		System.out.println("Type 2: " + v.getClass().toString());
	}
}
