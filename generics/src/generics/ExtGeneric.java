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
public class ExtGeneric<T extends Number> {
	
	private T val;
	
	ExtGeneric(T val){
		this.val = val;
	}
	
	public double doubleVal(){
		return val.doubleValue();
	}
}


