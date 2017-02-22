/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package junittest;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Less
 */
public class ProjClass2 {
	
	private String name;
	private Object _d;
	
	ProjClass2(String name){
		this.name = name;
		_d = new Object(){
			public String getName(){
				return "Vasya Pupkin";
			}
		};
	}
	
	public boolean demoBool(boolean param){
		return ! param;
	}
	
	public double demoDouble(double a, double b){
		return a / b * 100;
	}
	
	public String getName(){
		return name;
	}
	
	public ArrayList<Object> demoList(Object a, Object b, Object c){
		Object d = getD();
		return new ArrayList<>(Arrays.asList(a, b, c, d));
	}
	
	public Object getD(){
		return new D();
	}
	
	public class D {
		public String getName(){
			return "Vasya Pupkin";
		}
	}
	
}
