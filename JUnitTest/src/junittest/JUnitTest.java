/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package junittest;

import com.google.common.math.*;
import org.apache.logging.log4j.Level;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * https://github.com/junit-team/junit
 * 
 * @author Less
 */
public class JUnitTest {
	
	private static final Logger log = LogManager.getLogger(JUnitTest.class.getName());

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		double x = DoubleMath.factorial(10);
		System.out.println(x);
		ProjClass3.setK(3);
		System.out.println(new ProjClass3().kRepeate("yo!"));
		
		// OFF< TRACE< DEBUG< INFO< WARN< ERROR< FATAL< ALL
		log.log(Level.INFO, "x value = " + x);
	}
	
}
