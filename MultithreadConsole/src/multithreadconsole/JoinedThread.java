/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package multithreadconsole;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Less
 */
public class JoinedThread {
	
	public static void demo(boolean joinFlag){
		SomeThread t1 = new SomeThread();
		//Logger.getLogger(JoinedThread.class.getName()).log(Level.INFO, "test log");
		System.out.println("Main.beforeStart " + (joinFlag ? "joined" : ""));
		t1.start();
		if(joinFlag){
			try {
				t1.join();
			} catch (InterruptedException ex) {
				//Logger.getLogger(JoinedThread.class.getName()).log(Level.INFO, null, ex);
			}
		}
		System.out.println("Main.afterStart " + (joinFlag ? "joined" : ""));
	}
	
}

class SomeThread extends Thread {
	@Override
	public void run(){
		int i=5;
		while(--i > 0){
			System.out.printf("SomeThread.run, i = %d \n", i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException ex) {
				//Logger.getLogger(SomeThread.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
}