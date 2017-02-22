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
public class TestChainedException {
	public static void demo(){
		new TestChainedException().process();
	}
	
	void process(){
		try{
			BadWifiException ex = new BadWifiException();
			ex.initCause(new SpaceRaysException());
			throw ex;
		}catch(BadWifiException e){
			s.p(e);
			s.p("Because... ");
			Throwable cause = e.getCause();
			s.p(cause);
		}
	}
}

class SpaceRaysException extends Exception {
	SpaceRaysException(){
		super("Space rays too intense");
	}
}

class BadWifiException extends Exception {
	BadWifiException(){
		super("Bad wi-fi connection today");
	}
}