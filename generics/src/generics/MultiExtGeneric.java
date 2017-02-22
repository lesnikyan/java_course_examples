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

interface A_type{
	public int getInt();
}

interface B_type{
	public boolean isComputed();
}

interface C_type{
	public String getString();
}

public class MultiExtGeneric <T extends A_type & B_type & C_type>{
		
	private T val;

	MultiExtGeneric(T val){
		this.val = val;
	}
		
	public String getComplexInfo(){
		
		return "Type: "+ val.getClass().toString() + "; data: " + val.getInt() + "; " + val.getString()
				+ "; status: " + (val.isComputed() ? "done" : "no status") + ".";
	}
}
