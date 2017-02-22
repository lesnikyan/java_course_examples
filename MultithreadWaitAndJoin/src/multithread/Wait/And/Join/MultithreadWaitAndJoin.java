/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package multithread.Wait.And.Join;

/**
 * 
 * Demo code of Thread.join()
 * 
 * Demo code of Object.wait() / Object.notify()
 * 
 * Some little example of static importing: 
 *		import static multithread.Wait.And.Join.StaticPrinter.p;
 *
 * @author Less
 */
public class MultithreadWaitAndJoin {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
	//	joinTest();
		waitTest();
	}
	
	public static void joinTest(){
		JoinedThread.demo(false);
		JoinedThread.demo(true);
	}
	
	public static void waitTest(){
		ThreadWaiting.demo();
	}
	

}
