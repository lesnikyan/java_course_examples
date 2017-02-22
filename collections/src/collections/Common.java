/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package collections;

/**
 *
 * @author Less
 */
public class Common {
	private String value;
	
	Common(){
		this("none");
	}
	
	Common(String val){
		value = val;
	}
	
	@Override
	public String toString(){
		return value;
	}
}
