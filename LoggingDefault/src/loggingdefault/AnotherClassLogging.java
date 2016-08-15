package loggingdefault;


import java.util.logging.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Less
 */
public class AnotherClassLogging {
	
	private static final Logger log = Logger.getLogger(AnotherClassLogging.class.getName());
	
	AnotherClassLogging(){
		log.log(Level.FINEST, "Making anotherClass instance");
	}
	
	public void test(){
		log.log(Level.SEVERE, "another - test");
	}
}
