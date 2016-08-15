/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package suite;

import junittest.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Less
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(value={ProjClass1Test.class, ProjClass2Test.class})
public class ProjTestSuite {

	@BeforeClass
	public static void setUpClass() throws Exception {
		System.out.println("Test suite BeforeClass");
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		System.out.println("Test suite AfterClass");
	}
	
}
