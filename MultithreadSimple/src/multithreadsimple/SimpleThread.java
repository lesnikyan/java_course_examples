/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package multithreadsimple;

/**
 *
 * @author Less
 */
public class SimpleThread extends Thread implements SourceThread {
	
	double status = 0;
	
	@Override
	public void run(){
		EmptyComputer comp = new EmptyComputer(1000);
		while(status < 1){
			try {
				status = comp.compute();
				Thread.sleep(10);
			} catch (InterruptedException ex) {
				//Logger.getLogger(EmptyComputer.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
	
	@Override
	public double getStatus(){
		return status;
	}
	
}
