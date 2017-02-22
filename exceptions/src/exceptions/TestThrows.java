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
public class TestThrows {
	public static void demo(){
		try {
			TestThrows.canThrow();
		} catch (TTException ex) {
			s.p(ex);
		}
	}
	
	private static void canThrow() throws TTException{
		s.p("Doing: canThrow() -> ");
		throw new TTException();
	}
	
}

class TTException extends Exception{
	
}