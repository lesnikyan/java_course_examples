/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package springwelcome;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import lesson.game.Person;
import lesson.game.QuestStarter;
import lesson.simple.CommonService;
import lesson.simple.ObjWithList;
import lesson.simple.SimpleBean;
import org.eclipse.core.internal.runtime.CommonMessages;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 *
 * @author Less
 */
public class SpringWelcome {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		// http://www.tutorialspoint.com/spring/index.htm
		
		simpleExample();
		//refDiExample();
	}
	
	public static void simpleExample(){
		Path conf = Paths.get("resources/simple.xml");
		AbstractApplicationContext aac;
	//	ApplicationContext context = new ClassPathXmlApplicationContext("file:" + conf.toAbsolutePath().toString());
		ApplicationContext context = new FileSystemXmlApplicationContext(conf.toAbsolutePath().toString());
		
		// Simple Bean
		SimpleBean messenger = (SimpleBean) context.getBean("simpleBean");
		p(messenger.getMessage());
		
		// factory and alias
		CommonService service = (CommonService) context.getBean("mainService");
		service.doSomething();
		
		// Collection
		ObjWithList objwl = (ObjWithList) context.getBean("collectionInProperties");
		for(String str: objwl.getUnits()){
			p("Unit: " + str);
		}
	}
	
	public static void refDiExample(){
		Path conf = Paths.get("resources/game.xml");
		ApplicationContext context = new FileSystemXmlApplicationContext(conf.toAbsolutePath().toString());
		Person person = (Person) context.getBean("gamePerson");
		person.doQuest();
		QuestStarter qs = (QuestStarter) context.getBean("qStarter");
		qs.start();
	}
	
	public static void springAOP(){
		// http://javaxblog.ru/article/java-spring-aop-1/
	}
	
	public static void p(Object x){
		System.out.println(x);
	}
	
}
