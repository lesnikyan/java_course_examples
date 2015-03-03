/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package multithreadcallable;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Less
 */
public class MultithreadCallable {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		testSchedulledExecutor();
		testExecutor();
	}
	
	private static void testExecutor(){
		ExecutorService pool = Executors.newFixedThreadPool(3);
		
		ArrayList<Future> futResList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			RandomCompute rcom = new RandomCompute(i * (i%3) * 10 + 10);
			futResList.add(pool.submit(new ControlledComputing(rcom)));
			
		}
		
		pool.shutdown();

		try {
			Thread.sleep(1500);
		} catch (InterruptedException ex) {
			Logger.getLogger(MultithreadCallable.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		for(Future f: futResList){
			if(f.isDone()){
				try {
					System.out.println("next result = " + f.get());
				} catch (InterruptedException | ExecutionException ex) {
					Logger.getLogger(MultithreadCallable.class.getName()).log(Level.SEVERE, null, ex);
				}
			} else {
				System.out.println("No result for this Future");
			}
		}
	}
	
	private static void testSchedulledExecutor(){
		ExecutorService pool = Executors.newFixedThreadPool(3);
		ScheduledExecutorService schpool = Executors.newScheduledThreadPool(3);
		
		ArrayList<ScheduledFuture> futResList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			RandomCompute rcom = new RandomCompute(i * (i%3) * 10 + 10);
			futResList.add(schpool.schedule(new ControlledComputing(rcom), 1, TimeUnit.SECONDS));
		}
		
		
		//pool.shutdown();
		schpool.shutdown();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ex) {
			Logger.getLogger(MultithreadCallable.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		for(ScheduledFuture f: futResList){
			if(f.isDone()){
				try {
					System.out.println("next scheduled result = " + f.get());
					f.cancel(true);
				} catch (InterruptedException | ExecutionException ex) {
					Logger.getLogger(MultithreadCallable.class.getName()).log(Level.SEVERE, null, ex);
				}
			} else {
				System.out.println("No result for this ScheduledFuture");
			}
		}
	}
	
}


