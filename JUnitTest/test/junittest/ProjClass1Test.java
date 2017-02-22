/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package junittest;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Less
 */
public class ProjClass1Test {
	
	public ProjClass1Test() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}

	/**
	 * Test of demoVoid method, of class ProjClass1.
	 */
	@Test
	public void testDemoVoid() {
		System.out.println("demoVoid");
		ProjClass1 instance = null;
		instance = new ProjClass1();
		if(instance == null)
			fail("Instance is null!");
		instance.demoVoid();
	}

	/**
	 * Test of demoInt method, of class ProjClass1.
	 */
	@Test
	public void testDemoInt() {
		System.out.println("demoInt");
		ProjClass1 instance = new ProjClass1();
		int expResult = 100; // expected result
		int result = instance.demoInt();
		assertEquals(expResult, result);

	}

	/**
	 * Test of demoString method, of class ProjClass1.
	 */
	@Test
	public void testDemoString() {
		System.out.println("demoString");
		ProjClass1 instance = new ProjClass1();
		String expResult = "Hello, Kitty!"; // expected result
		String result = instance.demoString();
		assertEquals(expResult, result);
	}
	
}
