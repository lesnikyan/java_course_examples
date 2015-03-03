/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package multithreadcallable;

/**
 *
 * @author Less
 * @param <T>
 */
public interface Computing<T> {
	public void compute();
	public T getResult();
}
