/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multitheadcontrolofthreads;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Less
 */
public class LocalThread extends Thread{
	
	private int id;
	private static int nextId = 1;
	private Semaphore semaphore = null;
	
	private CountDownLatch latch = null;
	long execTime = 0;
	
	public LocalThread(){
		id = nextId++;
	}
	
	public LocalThread(Semaphore semaphore){
		this();
		this.semaphore = semaphore;
	}
	
	public LocalThread(CountDownLatch latch){
		this();
		this.latch = latch;
	}
	
	@Override
	public void run(){
		if(semaphore != null){
			runWithSemaphor();
		} else if (latch != null){
			runWithCountDownLatch();
		}
	}
	
	private void runWithSemaphor(){
		try{
			semaphore.acquire(); // get semaphore
			
			// doing some hard work
			for (int i = 0; i < 3; i++) {
				try {
					System.out.println(String.format("LocalThread(Sem)(%d) : %d", id, i));
					Thread.sleep(new Random().nextInt(20) + 10);
				} catch (InterruptedException ex) {
					log(ex);
				}
			}
			
		} catch (InterruptedException ex) {
			log(ex);
		}finally{
			semaphore.release(); // release semaphore
		}
	}
	
	private void runWithCountDownLatch(){
		long time1 = (new Date()).getTime();
		for (int i = 0; i < 3; i++) {
				try {
					System.out.println(String.format("LocalThread(CDL)(%d) : %d", id, i));
					Thread.sleep(new Random().nextInt(20) + 10);
				} catch (InterruptedException ex) {
					log(ex);
				}
			}
		long time2 = (new Date()).getTime();
		execTime = time2 - time1;
		latch.countDown();
	}
	
	public long getExecTime(){
		return execTime;
	}
	
	private void log(Exception ex){
		Logger.getLogger(MultitheadControlOfThreads.class.getName()).log(Level.SEVERE, null, ex);
	}
}
