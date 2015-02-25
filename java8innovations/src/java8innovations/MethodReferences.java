
package java8innovations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import static java8innovations.Java8innovations.p;

/**
 *
 * @author Less
 */
public class MethodReferences {
	public void test(){
		p("Test reference to method:");
		testEvalMethod();
		
		p("Test reference to constructor:");
		testEvalConstructor();
	}
	
	// Simple reference to method
	
	public void testEvalMethod(){
		evalMethod(this::targetMethod, "Ololo!");
	}
	
	public String targetMethod(String x){
		String[] str = new String[5];
		Arrays.fill(str, x);
		return Arrays.toString(str);
	}
	
	// use inctsnce method of object or static method of class
	public <T> T evalMethod(Function<T, T> fun, T val){
		p(fun.apply(val));
		return null;
	}

	// Reference to constructor
	
	public void testEvalConstructor(){
		evalConstr(SimpleClass::new, "Hello, class!");
	}
	
	// define inner class
	class SimpleClass {
		private String data;

		public SimpleClass(String str){
			data = str;
		}

		@Override
		public String toString(){
			return "Data: [" + data + "]";
		}
	}
	
	public <T, K> void evalConstr(Function<T, K> fun, T val){
		K obj = fun.apply(val);
		p(obj);
	}
	
}

