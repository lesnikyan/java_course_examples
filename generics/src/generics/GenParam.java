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
public class GenParam <T extends Number>{
	
	T val;
	
	GenParam(T val){
		this.val = val;
	}
	
	public T value(){
		return val;
	}
	
	public boolean equal(GenParam<?> that){
		return this.val.doubleValue() == that.val.doubleValue();
	}
	
}
