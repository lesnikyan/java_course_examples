package services;

/**
 *
 * @author Less
 */
public class SuperService {
	
	public void info(){
		print("");
	}
	
	public void noService(){
		print("No requested service!");
	}
	
	public static void print(Object x){
		System.out.println(" > " + x.toString());
	}
}
