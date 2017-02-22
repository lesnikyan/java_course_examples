
package java8innovations;

/**
 *
 * @author Less
 */
public class Java8innovations {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		
		new Lambda().test();
		
		new MethodReferences().test();
		
		new InterfaceDefaultMethod().test();
	}
	
	public static void p(Object x){
		System.out.println(x);
	}
	
}
