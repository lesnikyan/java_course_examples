/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package multithreadsimple;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Less
 */
public class EmptyComputer {
	
	int data;
	double step = 1;
	double current = 0;
	
	EmptyComputer(int data){
		this.data = data;
	}
	
	public double compute(){
		current += step;
		return current / data ; 
	}
}
