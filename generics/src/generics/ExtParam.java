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
public class ExtParam<T extends Base> {
	
	T val;
	
	ExtParam(T val){
		this.val = val;
	}
	
	static void showStaus(ExtParam<? extends Base> some){
		System.out.println("ExtParam: " + some.val.getName() + "; " + some.val.getVal());
	}
}

abstract class Base {
	public String getName(){
		return "Vasya";
	}
	
	abstract public int getVal();
}

class Aext extends Base{
	@Override
	public int getVal(){
		return 10;
	}
}

class Bext extends Base{
	@Override
	public int getVal(){
		return 20;
	}
}