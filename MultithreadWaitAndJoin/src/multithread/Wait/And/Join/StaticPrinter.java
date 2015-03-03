/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package multithread.Wait.And.Join;

/**
 *
 * @author Less
 */
public class StaticPrinter {
	public static void p(Object x){
		System.out.println(x);
	}
	
	public static void pf(String str, Object... options){
		System.out.printf(str, options);
	}
}
