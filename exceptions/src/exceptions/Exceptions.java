/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package exceptions;

/**
 *
 * @author Less
 */
public class Exceptions {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		
		/*
		Object
		|
		Throwable
		|
		-------------
		|           |
		Error...	Exception ...
		|			|
					RuntimeException ...
		
		*/
		
		// try - catch - finally
		// more precise rethrow
		
		String part = "chained";
		
		switch(part){
		// div by zero
			case "base":{
				TestStandardException.demo();
				break;
			}
		// user exception
		// inherited exceptions
			case "inherit":{
				TestSimpleUserException.demo();
				break;
			}
		// throws
			case "throws" :{
				TestThrows.demo();
				break;
			}
		// finally
			case "finally":{
				TestFinally.demo();
				break;
			}
		// chained exception # getCause
			case "chained": {
				TestChainedException.demo();
				break;
			}
			
		// JDK7:
		// multi-catch
			case "multi":{
				TestMultiCatch.demo();
				break;
			}
		//final rethrow
		
		}
                regex();
	}
        
        static void regex(){
            new RegTest().demo();
        }
	
}

class s{
	public static void p(Object x){
		System.out.println(x);
	}
}
