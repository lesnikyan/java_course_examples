/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package multithreadcallable;

import java.util.concurrent.Callable;

/**
 *
 * @author Less
 */
public class ControlledComputing implements Callable{

	Object result = null;
	boolean successStatus = false;
	Computing computer;
	Exception exc = null;
	
	public ControlledComputing(Computing c) {
		computer = c;
	}
	
	@Override
	public Object call() throws Exception {
		try{
			computer.compute();
			result = computer.getResult();
		} catch (Exception e){
			exc = e;
		}
		successStatus = true;
		return result;
	}
	
	public Exception getException(){
		return exc;
	}
	
}
