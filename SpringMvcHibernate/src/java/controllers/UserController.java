
package controllers;

import classes.dao.UserDao;
import classes.orm.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	UserDao model;
	
	@ResponseBody
	@RequestMapping(value="/test")
	public String test(){
		// 1262304000000 - milliseconds of 2010-01-01 . 0 : 0: 0
		long timeIndex = System.currentTimeMillis() - 1262304000000L;
		User user = new User("Uasya"+timeIndex, "q"+(timeIndex % 256), "uasya"+timeIndex+"@mail.mail");
		model.add(user);
		return "test";
	}
	
}
