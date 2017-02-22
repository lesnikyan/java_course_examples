/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package junittest;

import java.util.Arrays;

/**
 *
 * @author Less
 */
public class ProjClass3 {
	
	private static int k = 0;
	
	public static void setK(int val){
		k = val;
	}
	
	public int summ100(int x, int y){
		return (x + y) + 100;
	}
	
	public int multK(int x, int y){
		return x * y * k;
	}
	
	public String kRepeate(String str){
		String[] arr = new String[k];
		Arrays.fill(arr, str);
		return String.join(" ", arr);
	}
	
}
