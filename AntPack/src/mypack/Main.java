
package mypack;

import java.io.*;
import java.nio.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class Main {
	public static void main(String[] args){
		p("Main class: Hello, world!");
		SecondClass second = new SecondClass();
		second.hello();
		p("...");
		writeFile();
		System.out.flush();
	}
	
	public static void p(Object x){
		System.out.println(x);
	}
	
	static void writeFile(){
		BufferedWriter bw = null;
		try {
			String strPath = "a.txt";
			
			String data[] = {"1. some good data", "2. next str", "3. last string"};
			bw = new BufferedWriter(			// буферезированный writer
				new OutputStreamWriter(			// потоковый writer
					new FileOutputStream(strPath), // файловый поток записи
					"utf-8"
				)
			);
			for(String line: data){
				bw.write(line + "\n");
			}
		} catch (FileNotFoundException | UnsupportedEncodingException ex) {
			err(ex);
		} catch (IOException ex) {
			err(ex);
		} finally {
			try {
				bw.close(); // закрываем поток
			} catch (IOException ex) {
				err(ex);
			}
		}
	}
	
	
	
	public static void err(Exception ex){
		p(ex.getMessage());
	}
}
