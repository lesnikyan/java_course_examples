/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadlock;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Less
 */
public class DeadLock {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		DeadLock main = new DeadLock();
		main.startTest();
	}
	
	public void startTest(){
		A a = new A();
		B b = new B();
		
		Thread t1 = new Thread(new T1(a, b));
		
		Thread t2 = new Thread(new T2(a, b));
		
		t1.start();
		t2.start();
	}
	
	class T1 implements Runnable {
		
		private A a;
		
		private B b;
		
		private int v = 1;
		
		public T1(A a, B b){
			this.a = a;
			this.b = b;
		}
		
		@Override
		public void run() {
			try {
//				a.testA(v);
//				b.testB(v);
				
				synchronized(a){
						a.testA2(v);
					synchronized(b){
						b.testB2(v);
					}
				}
			} catch (InterruptedException ex) {
				Logger.getLogger(DeadLock.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
	
	class T2 implements Runnable {
		
		private A a;
		
		private B b;
		
		private int v = 2;
		
		public T2(A a, B b){
			this.a = a;
			this.b = b;
		}
		
		@Override
		public void run() {
			try {
//				a.testA(v);
//				b.testB(v);
				synchronized(b){
						b.testB2(v);
					synchronized(a){
						a.testA2(v);
					}
				}
			} catch (InterruptedException ex) {
				Logger.getLogger(DeadLock.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
	
}


class A {
	synchronized public void testA(int v) throws InterruptedException{
		System.out.println("test A start " + v);
		Thread.sleep(500);
		System.out.println("test A end " + v);
	}
	
	public void testA2(int v) throws InterruptedException{
		System.out.println("test2 A start " + v);
		Thread.sleep(500);
		System.out.println("test2 A end " + v);
	}
}

class B {
	synchronized public void testB(int v) throws InterruptedException{
		System.out.println("test B start " + v);
		Thread.sleep(500);
		System.out.println("test B end" + v);
	}
	
	public void testB2(int v) throws InterruptedException{
		System.out.println("test2 B start " + v);
		Thread.sleep(500);
		System.out.println("test2 B end " + v);
	}
}