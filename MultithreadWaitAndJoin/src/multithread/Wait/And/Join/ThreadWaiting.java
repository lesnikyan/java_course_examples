/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package multithread.Wait.And.Join;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import static multithread.Wait.And.Join.StaticPrinter.*;

/**
 *
 * @author Less
 */
public class ThreadWaiting {
	public static void demo(){
		FirstProcess firstProc = new FirstProcess();
		SecondProcess secondProc = new SecondProcess(firstProc);
		firstProc.setSecond(secondProc);

		Thread first = new Thread(firstProc);
		Thread second = new Thread(secondProc);
		
		first.start();
		second.start();
	}
}

class FirstProcess implements Runnable {
	
	SecondProcess process2;
	
	void setSecond(SecondProcess second){
		process2 = second;
	}
	
	@Override
	public void run(){
		p("First ran");
		try {
			Thread.sleep(110);
		} catch (InterruptedException ex) {}
		
		synchronized(this){
			try {
				wait(10000);
			} catch (
					InterruptedException ex
				//	Exception ex
					) {
				p(ex);
			} finally {
				notify();
			}
		}
		p("==Result from Second = " + process2.getResult());
		p("First finish");
	}
}


class SecondProcess implements Runnable {
	
	
	FirstProcess process1 = null;
	
	SecondProcess(FirstProcess first){
		process1 = first;
	}
	int result = 0;
	
	@Override
	public void run(){
		p("Second run");
		Random r = new Random();
		int num = r.nextInt(10) + 5;
		int i = num;
		
		// using threadsafe synchronize by Lock
		Lock lk = new ReentrantLock();
		while(i > 0){
			try{
				p("Second: iteration " + (num - i));
				if(lk.tryLock()){
					try{
						// some unsafed and hard code =)
						result += r.nextInt(20);
						--i;
					} finally {
						lk.unlock();
					}
				}
				
				Thread.sleep(100);
			}catch(InterruptedException exc){}
		}
		synchronized(process1){
			process1.notify();
		}
		p("--Second: final result = " + result);
	}
	
	public int getResult(){
		return result;
	}
}

