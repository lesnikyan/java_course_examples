package consolecalc;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Less
 */
public class ConsoleCalc {

	Scanner input= new Scanner(System.in);
	
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		ConsoleCalc calc = new ConsoleCalc();
		calc.start();
	}
	
	private void start(){
		boolean proceed = true;
		while(proceed){
			String command = read().trim();
			System.out.println("command: " + command);
			if(command.equalsIgnoreCase("exit")){
				proceed = false;
				break;
			}
			// operator operand operand
			try{
				operation(command);
			} catch (IncorrectOperatorException e){
				System.out.println("Exception: " + e.getMessage());
			} catch (IncorrectCommandFormatException e) {
				System.out.println("Exception: " + e.getMessage());
			}
		}
	}
	
	private void operation(String command) throws IncorrectOperatorException, IncorrectCommandFormatException{
		List<String> words = Arrays.asList(command.split("\\s+"));
		if(words.size() != 3){
			throw new IncorrectCommandFormatException("Number of words is incorrect = " + words.size());
		}
	//	System.out.println(Arrays.toString(words.toArray()));
		String operator = words.get(0).trim();
		int x = Integer.parseInt(words.get(1).trim());
		int y = Integer.parseInt(words.get(2).trim());
		int res = 0;
		switch(operator){
			case "+" :{
				res = x + y;
				break;
			}
			case "-" :{
				res = x - y;
				break;
			}
			case "*" :{
				res = x * y;
				break;
			}
			case "/" :{
				res = x / y;
				break;
			}
			default :{
				throw new IncorrectOperatorException("Default case!");
			}
		}
		System.out.println(String.format("%1$d %2$s %3$d = %4$d", x, operator, y, res));
	}
	
	private String read(){
        String str=input.nextLine();
		return str;
	}
	
}

class CalcException extends Exception {
	CalcException(String msg){
		super(msg);
	}
}

class IncorrectOperatorException extends CalcException {
	IncorrectOperatorException(String msg){
		super(msg);
	}
}

class IncorrectCommandFormatException extends CalcException {
	IncorrectCommandFormatException(String msg){
		super(msg);
	}
}