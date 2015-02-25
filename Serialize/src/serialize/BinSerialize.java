/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package serialize;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Less
 */
public class BinSerialize {
	
	File file;
	
	public BinSerialize(String fileName){
		file = new File(fileName);
	}
 
	public void test() throws IOException, FileNotFoundException, ClassNotFoundException{
		serialize();
		unserialize();
	}
	
	public void serialize() throws FileNotFoundException, IOException{
		MyObject mob = new MyObject();
		mob.name = "ABCDEF";
		// serialize
		FileOutputStream foStr = new FileOutputStream(file);
		try (ObjectOutputStream ooStrm = new ObjectOutputStream(foStr)) {
			ooStrm.writeObject(mob);
			ooStrm.flush();
		}
	}
	
	public void unserialize() throws FileNotFoundException, IOException, ClassNotFoundException{
		// unserialize
		FileInputStream fiStr = new FileInputStream(file);
		try (ObjectInputStream oiStr = new ObjectInputStream(fiStr)) {
			MyObject unMob = (MyObject) oiStr.readObject();
			p("MyObject.name = " + unMob.name);
		}
		p("\n\n");
	}
	
	public static void p(Object x){
		System.out.println(x);
	}
	
}


class BaseObject implements java.io.Serializable {
	public String name = "QWERTY";
}


class MyObject extends BaseObject{
	
	int myValue = 123;
	
	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
		out.writeInt(myValue);
	}
	
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
		myValue = in.readInt();
	}
}