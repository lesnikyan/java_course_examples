
package reflectionconsole;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Less
 */
public class ReflectionConsole {
	
	Scanner scan = new Scanner(System.in);
	//String[] services = new String[]{"messages", "user", "news"};
	List<String> services = new ArrayList<>(Arrays.asList(new String[]{"user", "messages", "news"}));
	String baseService = "superService";

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		ReflectionConsole cons = new ReflectionConsole();
		cons.run();
	}
	
	public void run(){
		boolean con = true;
		String command;
		String[] words;
		
		Comparator comparator = new Comparator(){
			@Override
			public int compare(Object a, Object b) {
				return a.toString().compareTo(b.toString());
			}				
		};
		
		while(con){
			command = read();
			if(command.equals("exit")){
				break;
			}
			words = command.trim().split("\\s+");
			if(words.length > 0){
				//print("words len = " + words.length);	
				//print(Arrays.toString(words));
				//print("search = " + Arrays.binarySearch(services, words[0]));
				//print("search = " + services.contains(words[0]));
				//boolean correctService = Arrays.binarySearch(services, words[0]) > -1;
				boolean correctService = services.contains(words[0]);
				String service = correctService ? words[0] : baseService;
				String action;
				if(correctService){
					action = words.length > 1 ? words[1] : "info";
				} else {
					action = "noService";
				}
				//print(service + " > " + action);
				String param = correctService && words.length > 2 ? words[2] : null;
				runService(upperCase(service), action, param);
			} else {
				print("enter any command");
			}
		}
		print("end of service");
	}
	
	String upperCase(String str){
		if(str.length() == 0){
			return "";
		}
		return Character.toUpperCase(str.charAt(0)) + str.substring(1);
	}
	
	private void runService(String serviceName, String action, String param){
		try {
			Class service = Class.forName("services." + serviceName);
			
			Method method;
			if(param != null){
				//print("invoke with param");
				method = service.getMethod(action, String.class);
				//method.setAccessible(true);
				method.invoke(service.newInstance(), param);
			} else {
				//print("no param invoke");
				method = service.getMethod(action);
				method.invoke(service.newInstance());
			}
			
			
		} catch (ClassNotFoundException ex) {
			print("Error on creating service: " + serviceName);
		} catch (NoSuchMethodException | SecurityException ex) {
			print("Error on creating method: " + serviceName + " . " + action + "; couse: " + ex.getClass().getName());
		} catch (InstantiationException ex) {
			print("Error on instantiating of service: " + serviceName);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
			print("Error on running method: " + action + " [" + ex.getClass().getCanonicalName() + "]: " + ex.getMessage());
			print(Arrays.toString(ex.getStackTrace()));
		}
		
	}
	
	private String read(){
		return scan.nextLine().trim();
	}
	
	private void print(Object x){
		System.out.println(x);
	}
	
}
