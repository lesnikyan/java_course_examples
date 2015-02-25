
package java8innovations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import static java8innovations.Java8innovations.p;

/**
 *
 * @author Less
 */
public class Lambda {
	public void test(){
		p("Test list:");
		list();
		
		p("Simple test:");
		p(simple(x -> x * x, 10 ));
		
		p("Binary args test:");
		p(binary( (x, y) -> { return x.doubleValue() / y.doubleValue(); }, 1203, 30));
		
		p("Closure test:");
		testClosure();
		
		p("Test user's func interface:");
		testMyFunc();
	}
	
	public void list(){
		List<String> users = Arrays.asList("User1", "User2", "User3", "User4", "User5");
		users.forEach(name -> p("\t" +name));
	}
	
	public int simple(Function<Integer, Integer> fun, int x){
		return fun.apply(x);
	}
	
	public Double binary(BiFunction<Integer, Integer, Double> fun, int x, int y){
		return fun.apply(x, y);
	}
	
	public void testClosure(){
		int size = 3;
		closure(x -> p(x * size), 10);
	}
	
	public void closure(Consumer<Integer> fun, int x){
		fun.accept(x);
	}
	
	public void testMyFunc(){
		p(myFunc(
				(Integer x, Integer y) -> 
					{ 
						int[] src = new int[x]; 
						Arrays.fill(src, y); 
						return Arrays.toString(src); 
					},
				5, 123));
	}
	
	public String myFunc(MyFunctionalInterface<Integer, Integer, String> fun, int x, int y){
		return fun.calc(x, y);
	}
}

@FunctionalInterface
interface MyFunctionalInterface<N,M,R> {
	public R calc(N x, M y);
}



/*
() -> {}                // No parameters; result is void
() -> 42                // No parameters, expression body
() -> null              // No parameters, expression body
() -> { return 42; }    // No parameters, block body with return
() -> { System.gc(); }  // No parameters, void block body

() -> {                 // Complex block body with returns
  if (true) return 12;
  else {
    int result = 15;
    for (int i = 1; i < 10; i++)
      result *= i;
    return result;
  }
}                          

(int x) -> x+1              // Single declared-type parameter
(int x) -> { return x+1; }  // Single declared-type parameter
(x) -> x+1                  // Single inferred-type parameter
x -> x+1                    // Parentheses optional for
                            // single inferred-type parameter

(String s) -> s.length()      // Single declared-type parameter
(Thread t) -> { t.start(); }  // Single declared-type parameter
s -> s.length()               // Single inferred-type parameter
t -> { t.start(); }           // Single inferred-type parameter

(int x, int y) -> x+y  // Multiple declared-type parameters
(x, y) -> x+y          // Multiple inferred-type parameters
(x, int y) -> x+y    // Illegal: can't mix inferred and declared types
(x, final y) -> x+y  // Illegal: no modifiers with inferred types

*/