package lesson.spring.mvc;

import dao.UserDAO;
import data.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	UserDAO udao;
	
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public ModelAndView createForm(ModelMap model){
		return new ModelAndView("user/form", "user-form", new User());
	}
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public String createHandler(@ModelAttribute("user-form") User user, ModelMap model){
		model.addAttribute("name", user.getName());
		model.addAttribute("pass", user.getPass());
		model.addAttribute("email", user.getEmail());
		model.addAttribute("alias", user.getAlias());
		return "user/info";
		// return "redirect:/user/";
	}
	
	
	@RequestMapping(value={"", "/info"}, method = RequestMethod.GET)
	public String info(ModelMap model){
		model.addAttribute("daotest", udao.test());
		return "user/empty";
	}
}
