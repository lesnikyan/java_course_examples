/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package loggingdefault;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.*;
import java.nio.file.Paths;
import java.nio.file.Path;

/**
 *
 * @author Less
 */
public class LoggingDefault {
	
	private static final Logger log = Logger.getLogger(LoggingDefault.class.getName());
	
	LoggingDefault() throws FileNotFoundException, IOException {
		
		log.log(Level.FINEST, "Making instance");
	}

	/**
	 * @param args the command line arguments
	 * @throws java.io.IOException
	 */
	public static void main(String[] args) throws IOException{
		
		// Load logging configuration
		Path logConfPath = Paths.get("resources/logging.properties").toAbsolutePath();
		FileInputStream logConfInputStream = new FileInputStream(logConfPath.toString());
		LogManager.getLogManager().readConfiguration(logConfInputStream);
		log.log(Level.CONFIG, "Logging config loaded");
		
		// create instance
		LoggingDefault inst = new LoggingDefault();
		inst.warning();
		inst.info();
		inst.exception();
		
		AnotherClassLogging anclog = new AnotherClassLogging();
		anclog.test();
	}
	
	private void warning(){
		log.warning("Warning message!!!");
	}
	
	private void info(){
		log.info("Some info..");
	}
	
	private void exception(){
		try{
			int x = 1;
			x -= 1;
			x = 10 / x;
			p(x);
		} catch(java.lang.ArithmeticException ex){
			log.log(Level.SEVERE, "exception method, illegal", ex);
		}
	}
	
	private void p(Object x){
		System.out.println(x);
	}
	
}
