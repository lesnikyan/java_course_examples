/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package exceptions;

/**
 *
 * @author Less
 */
public class TestMultiCatch {
	public static void demo(){
		new TestMultiCatch().run();
	}
	
	private void run(){
		for(int i=0; i< 5; ++i){
			try{
				if(i == 1){
					throw new OneException();
				} else if(i == 2){
					throw new TwoException();
				} else if(i == 3){
					throw new ThreeException();
				}
			}catch(OneException | TwoException | ThreeException e){
				s.p("So.. we have an error. End we need type of it!");
				s.p("And this type...");
				s.p(e.getMessage());
			}
		}
	}
}

class OneException extends Exception{
	public OneException() {
		super("One!");
	}
}

class TwoException extends Exception{
	TwoException(){
		super("Two!");
	}
}

class ThreeException extends Exception{
	ThreeException(){
		super("Three!");
	}
}