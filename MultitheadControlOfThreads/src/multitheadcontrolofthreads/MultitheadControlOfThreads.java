/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multitheadcontrolofthreads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Less
 * // short manual: http://www.ibm.com/developerworks/ru/library/j-5things5/ 
 * // sync collections: http://www.ibm.com/developerworks/ru/library/j-5things4/index.html
 * 
 */
public class MultitheadControlOfThreads {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		semaphoreTest();
		
		sleep();
		
		cdLatchTest();
	}
	
	private static void semaphoreTest(){
		int maxThreadsCount = 3;
		int realThreadsCount = 8;
		Semaphore semaphore = new Semaphore(maxThreadsCount);
		List<LocalThread> threads = new ArrayList<>();
		
		// create threads
		for (int i = 0; i < realThreadsCount; i++) {
			threads.add(new LocalThread(semaphore));
		}
		
		// run threads
		for(Thread t: threads){
			t.start();
		}
	}
	
	private static void cdLatchTest(){
		int realThreadsCount = 5;
		CountDownLatch latch = new CountDownLatch(realThreadsCount);
		List<LocalThread> threads = new ArrayList<>();
		
		// create threads
		for (int i = 0; i < realThreadsCount; i++) {
			threads.add(new LocalThread(latch));
		}
		
		// run threads
		for(Thread t: threads){
			t.start();
		}
		System.out.println("Threads started.");
		
		long fullTime = 0;
		try {
			// waiting for all threads finish 
			latch.await();
			for(LocalThread t: threads){
				fullTime += t.getExecTime();
			}
			System.out.println("Full exec time = " + fullTime + " msec");
		} catch (InterruptedException ex) {
			Logger.getLogger(MultitheadControlOfThreads.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public static void sleep(){
		try {
			Thread.sleep(500);
		} catch (InterruptedException ex) {
			Logger.getLogger(MultitheadControlOfThreads.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
}
