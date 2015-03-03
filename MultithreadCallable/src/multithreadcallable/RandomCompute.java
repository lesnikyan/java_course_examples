/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package multithreadcallable;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Less
 */
class RandomCompute implements Computing {
	
	AtomicInteger result;
	int step = 0;

	public RandomCompute(int val) {
		step = val - 1;
		result = new AtomicInteger(0);
	}
	
	@Override
	public void compute(){
		int sleepPeriod = 10 + new Random().nextInt(100);
		
		Random r = new Random();
		for(int i=0; i <  10; ++i){
			try{
				Thread.sleep(sleepPeriod);
				result.addAndGet(r.nextInt(step) + 1);
			}catch(InterruptedException exc){
				
			}
		}
	}
	
	@Override
	public Integer getResult(){
		return result.get();
	}
}