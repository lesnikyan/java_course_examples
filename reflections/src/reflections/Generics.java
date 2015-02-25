/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reflections;

import java.util.Arrays;
import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.Scanner;

/**
 *
 * @author Less
 */
public class Generics {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		testAnnotationReflection();
		
		testAction();
	}
	
	public static void getClassMethods(){
		// вариант 1: getClass()
		AnnotationDemo demo = new AnnotationDemo();
		Class<AnnotationDemo> info1 = (Class<AnnotationDemo>) demo.getClass();

		// вариант2: object.class
		Class<AnnotationDemo> info2 = AnnotationDemo.class;
		
		// вариант 3: Class.forName("package.path.ClassName");
		try {
			Class<AnnotationDemo> info3 = (Class<AnnotationDemo>) Class.forName("reflections.AnnotationDemo");
		} catch (ClassNotFoundException ex) {
			//
		}
	}
	
	public static void testAnnotationReflection(){
	
		// standart class
		Class<Object> obclass = Object.class;
		Method[] methodsByObject = obclass.getDeclaredMethods();
		for(Method method: methodsByObject){
			
		}
		
		// User's class 
		try {
			Class<AnnotationDemo> democlass = AnnotationDemo.class;
			AnnotationDemo demo = democlass.newInstance();
			Method[] methods = democlass.getMethods();
			for(Method method: methods){
				Annotation[] anns = method.getAnnotations();
				p("m: " + method.getName() + "; a: " 
					//	+ Arrays.toString(method.getAnnotations())
				);
				if(anns.length > 0){
					for(Annotation ann: anns){
						p("annotation: " + ann.toString());
					}
				}
			}
		
			p("test of run");
			Method run = democlass.getMethod("run", String.class);
			run.invoke(demo, "Test of run");
			
		} catch (NoSuchMethodException | SecurityException ex) {
			// of getMethod
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
			// of invoke
		} catch (InstantiationException ex) {
			// of newInstance
		}
	}
	
	public static void testAction(){
		try {
			String className = "reflections.ActionContainer";
			Class cont = Class.forName(className);
			
			p("Введите имя действия");
			Scanner input = new Scanner(System.in, "windows-1251");
			String line = input.nextLine().trim();
			String methodName = "print" + (line.charAt(0) + "").toUpperCase() + line.substring(1);
			p(methodName);
			Method method = cont.getMethod(methodName);
			if(method.isAnnotationPresent(Action.class)){
				method.invoke(cont.newInstance(), null);
			}
		} catch (ClassNotFoundException ex) {
			p("ClassNotFoundException");
		} catch (NoSuchMethodException ex) {
			p("NoSuchMethodException");
		} catch (SecurityException ex) {
			p("SecurityException");
		} catch (InstantiationException ex) {
			p("InstantiationException");
		} catch (IllegalAccessException ex) {
			p("IllegalAccessException");
		} catch (IllegalArgumentException ex) {
			p("IllegalArgumentException");
		} catch (InvocationTargetException ex) {
			p("InvocationTargetException");
		}
		
	} 
	public static void p(Object x){
		System.out.println(x);
	}
}

class s{
	public static void p(Object x){
		System.out.println(x);
	}
	
	public static String arrs(Object arr){
		System.out.print("("+arr.getClass().getName()+")");
		return Arrays.deepToString((Object[]) arr);
	}

}

