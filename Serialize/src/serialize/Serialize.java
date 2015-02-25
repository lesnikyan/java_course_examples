/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package serialize;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Less
 */
public class Serialize {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		// classic binary serialization
		bin();
		xml();
		json();
		urlDemo();
	}
	
	public static void bin(){
		BinSerialize binSerializer = new BinSerialize("myobj.bin");
		try {
			binSerializer.test();
		} catch (IOException | ClassNotFoundException ex) {
			Logger.getLogger(Serialize.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public static void xml(){
		XmlSerialize xmlMarshaller = new XmlSerialize("calendar.xml");
		xmlMarshaller.test();
	}
	
	public static void json(){
		JsonSerialize jsonSerializer = new JsonSerialize();
		jsonSerializer.test();
	}
	
	private static void urlDemo(){
		try {
			JavaUrl.demo();
		} catch (MalformedURLException ex) {
			Logger.getLogger(Serialize.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(Serialize.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
}
