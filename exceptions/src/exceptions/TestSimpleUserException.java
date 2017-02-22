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
public class TestSimpleUserException {
	
	public static void demo(){
		TestSimpleUserException test = new TestSimpleUserException();
		for (int i = 0; i < 10; ++i){
			test.process(i);
		}
	}
	
	void process(int x){
		try{
			if(x == 5){
				throw new Exception("Illegal value 5");
			}
			if (x != 0 && x % 3 == 0){
				throw new SuperException("Illegal multiplier 3");
			}
			if(x * x > 50){
				throw new Sub1Exception("Too large square of value");
			}
			if(x%2 != 0 && x < 9 && x > 5 ){
				throw new Sub2Exception("Abnormal result of complicated test");
			}
			
			s.p(" == Normal process. Result: " + x);
			
		} catch (Sub2Exception e) {
			s.p(e);
		} catch (Sub1Exception e) {
			s.p(e);
		} catch (SuperException e) {
			s.p(e);
		} catch (Exception e) {
			s.p(e);
		}
	}
	
}


class SuperException extends Exception {
	SuperException(String msg){
		super(msg);
	}
}

class Sub1Exception extends SuperException {
	Sub1Exception(String msg){
		super(msg);
	}
}

class Sub2Exception extends Sub1Exception {
	Sub2Exception(String msg){
		super(msg);
	}
}