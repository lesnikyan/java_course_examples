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
public class ActiveThread implements Runnable {
	
	MultiThreadSource target;
	int pullIndex;
	double status = 0;
	int data;
	double step = 1;
	
	public ActiveThread(int data){
		this.data = data;
	}
	
	public void setStatTarget(MultiThreadSource target, int index){
		this.target = target;
		this.pullIndex = index;
	}

	@Override
	public void run(){
		if(target == null){
			try {
				throw new NoTargetException();
			} catch (NoTargetException ex) {
				System.out.println(ex);
				return;
			}
		}
		
		syncWork();
		
	}
	
	public void syncWork(){
		while(status < data){
			status += step;
			target.updateStatus(status / data, pullIndex);
			try {
				Thread.sleep(10);
			} catch (InterruptedException ex) {
				//
			}
		}
	}
	
	public void asyncWork(){
		//updateStatusAsync
		while(status < data){
			status += step;
			synchronized(target){
				target.updateStatusAsync(status / data, pullIndex);
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException ex) {
				//
			}
		}
	}
	
}

class NoTargetException extends Exception {
	
}
