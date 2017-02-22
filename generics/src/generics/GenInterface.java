/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package generics;

/**
 *
 * @author Less
 * @param <T>
 * @param <V>
 * @param <Comparable>
 */
public interface GenInterface <T extends Comparable<T>, V> {
	T compare();
	V compile();
	boolean compareOf(GenInterface<?,?> that);
}

abstract class ImlGen<A extends Comparable<A>, B> implements GenInterface<A, B> {
	
}
