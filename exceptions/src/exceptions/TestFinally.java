/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package exceptions;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Less
 */
public class TestFinally {
	
	public static void demo(){
		TestFinally x = new TestFinally();
		x.run();
	}
	
	private void run(){
		try {
			s.p("Процесс пошел...");
			process();
		} catch (Exception ex) {
			s.p("Exception" + ex);
		}
	}
	
	private String process() throws Exception{
		try{
			throw new Exception("something happened");
		} catch (FinException e){
			s.p("FinException: " + e.getMessage());
		} finally {
			s.p("А мы все равно сделаем пакость. Му-Ха-Ха!!!");
		}
		return "Hello!";
	} 
}

class FinException extends Exception {
	
}