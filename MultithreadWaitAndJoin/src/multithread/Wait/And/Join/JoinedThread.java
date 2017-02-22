/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package multithread.Wait.And.Join;


import java.util.logging.Level;
import java.util.logging.Logger;
import static multithread.Wait.And.Join.StaticPrinter.*;
import static multithread.Wait.And.Join.StaticPrinter.pf;

/**
 *
 * @author Less
 */
public class JoinedThread {
	
	public static void demo(boolean joinFlag){
		SomeThread t1 = new SomeThread();
		//Logger.getLogger(JoinedThread.class.getName()).log(Level.INFO, "test log");
		p("Main.beforeStart " + (joinFlag ? "joined" : ""));
		t1.start();
		if(joinFlag){
			try {
				t1.join();
			} catch (InterruptedException ex) {
				//Logger.getLogger(JoinedThread.class.getName()).log(Level.INFO, null, ex);
			}
		}
		p("Main.afterStart " + (joinFlag ? "joined" : ""));
	}
	
}

class SomeThread extends Thread {
	@Override
	public void run(){
		int i=5;
		while(--i > 0){
			pf("SomeThread.run, i = %d \n", i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException ex) {
				//Logger.getLogger(SomeThread.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
}