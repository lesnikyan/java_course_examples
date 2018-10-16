
package controllers;

import classes.dao.UserDao;
import classes.orm.entities.Phone;
import classes.orm.entities.User;
import java.util.Set;
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
		
		Phone phone1 = new Phone("111-" + (timeIndex % 999999));
		phone1.setUser(user);
		
		Phone phone2 = new Phone("222-" + (timeIndex % 999999));
		phone2.setUser(user);
		
		Set<Phone> phones = user.getPhones();
		phones.add(phone1);
		phones.add(phone2);
		model.add(user);
		return String.format("test: user: %d phone1: %d phone2: %d ", user.getId(), phone1.getId(), phone2.getId());
	}
	
}
