
package services;

/**
 *
 * @author Less
 */
public class Messages extends SuperService {
	
	String[] messages = new String[]{
		"Hello world!",
		"Vasya was here!",
		"Windows must die!"
	};
	
	public void info(){
		print("the news service. use id of message. from [0] to [" + (messages.length - 1) + "]");
	}
	
	public void get(String idStr){
		int id = Integer.parseInt(idStr.trim());
		print(id);
		if(id >= messages.length){
			print("Error! No same message!");
			return;
		}
		print(messages[id]);
	}
}
