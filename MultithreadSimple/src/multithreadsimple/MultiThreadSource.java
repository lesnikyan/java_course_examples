/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package multithreadsimple;

import java.util.ArrayList;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/**
 *
 * @author Less
 */
public class MultiThreadSource implements SourceThread {
	
	private double globalStatus = 0;
	private int count = 0;
	private volatile double status = 0;
	private ArrayList<Thread> threads = new ArrayList<>();
	private double[] statPull = {};
	
	public void addComputer(ActiveThread computer){
		computer.setStatTarget(this, count);
		threads.add(new Thread(computer));
		count = threads.size();
	}
	
	public void begin(){
		statPull = new double[count];
		for(Thread thread : threads){
			thread.start();
		}
	}
	
	
	public synchronized void updateStatus(double oneCompStatus, int index){
		statPull[index] = oneCompStatus;
		globalStatus = DoubleStream.of(statPull).sum();
		this.status = globalStatus / count;
	}
	
	public void updateStatusAsync(double oneCompStatus, int index){
		statPull[index] = oneCompStatus;
		globalStatus = DoubleStream.of(statPull).sum();
		this.status = globalStatus / count;
	}

	@Override
	public double getStatus() {
		return status;
	}
	
}
