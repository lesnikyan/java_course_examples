/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package junittest;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Less
 */
public class ProjClass2Test {
	
	public ProjClass2Test() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void prepare(){
		System.out.println("prepare");
	}

	/**
	 * Test of demoBool method, of class ProjClass2.
	 */
	@Test
	public void testDemoBool() {
		System.out.println("demoBool");
		boolean param = false;
		ProjClass2 instance = new ProjClass2("Mbanga");
		boolean result = instance.demoBool(param);
		assertTrue("expected true", result);
	}

	/**
	 * Test of demoDouble method, of class ProjClass2.
	 */
	@Test
	public void testDemoDouble() {
		System.out.println("demoDouble");
		double a = 100.0;
		double b = 5.0;
		ProjClass2 instance =  new ProjClass2("Mbanga");
		double expResult = 2000.0;
		double result = instance.demoDouble(a, b); // -> a / b * 100
		assertEquals(expResult, result, 0.0);
	}

	/**
	 * Test of getName method, of class ProjClass2.
	 */
	@Test
	public void testGetName() {
		System.out.println("getName");
		String expResult = "Mbanga";
		ProjClass2 instance =  new ProjClass2(expResult);
		String result = instance.getName();
		assertEquals(expResult, result);
	}

	/**
	 * Test of demoList method, of class ProjClass2.
	 */
	@Test
	public void testDemoList() {
		System.out.println("demoList");
		ProjClass2 instance = new ProjClass2("Mbanga");
		
		Object a = new Object();
		Object b = new Object();
		Object c = new Object(){
			public String getStr(){
				return "c-string";
			}
		};
		Object d = instance.getD();
		
		ArrayList<Object> expResult = new ArrayList<>(Arrays.asList(a, b, c, d));
		ArrayList<Object> result = instance.demoList(a, b, c);
		
		int len = expResult.size();
		int last = len - 1;
		
		assertEquals(len, result.size());
		
		if(len != result.size())
			fail("incorrect size of result");
		
		for(int index = 0; index < len; ++index){
			if(index < last)
				assertSame("testing equals part of result: " + index, expResult.get(index), result.get(index));
			else
				assertEquals("testing same part of result: " + index, 
						((ProjClass2.D)expResult.get(index)).getName(), 
						((ProjClass2.D)result.get(index)).getName());
		}
	}
	
}
