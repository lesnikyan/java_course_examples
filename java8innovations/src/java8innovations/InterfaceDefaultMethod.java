
package java8innovations;

import java.util.Arrays;
import static java8innovations.Java8innovations.p;

/**
 *
 * @author Less
 */
public class InterfaceDefaultMethod implements Filterable {
	
	public void test(){
		p("Test default method of interface:");
		String[] words = {"AAA", "bbbbbbbbb", "cccccccc", "q1234567", "hello!world!", "qqqaazzxxsswwpp"};
		String[] filtered = filter(words, "[a-z!]{6,12}");
		p(Arrays.toString(filtered));
	}
	
}

interface Filterable {
	public default String[] filter(String[] src, String match){
		String[] res = new String[src.length];
		int current = 0;
		for(String item: src){
			if(item.matches(match)){
				res[current] = item;
				current++;
			}
		}
		return Arrays.copyOf(res, current);
	}
}
