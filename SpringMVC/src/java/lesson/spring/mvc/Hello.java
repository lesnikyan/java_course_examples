/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lesson.spring.mvc;
//import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



/**
 * 
 * http://www.tutorialspoint.com/spring/spring_web_mvc_framework.htm
 * http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html
 * http://docs.spring.io/spring/docs/3.0.x/spring-framework-reference/html/mvc.html#mvc-ann-requestmapping-arguments
 *
 * @author Less
 */
@Controller
public class Hello {
	
	//@Inject
	public Hello(){
		
	}
	
	@RequestMapping(value={"/", "/hello"}, method=RequestMethod.GET)
	public String showPage(ModelMap model){
		model.addAttribute("msg1", "Hello from Spring MVC!");
		return "hello";
	}
	
	@RequestMapping(value="/getname", method=RequestMethod.GET)
	public String showName(@RequestParam(value = "name", required = false)String nameVal, ModelMap model){
		if(nameVal == null){
			nameVal = "noname";
		}
		model.addAttribute("name", nameVal);
		return "viewname";
	}
	
}
