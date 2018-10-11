
package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/")
public class Main {
    
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String indexAction(ModelMap map){
        map.addAttribute("urlPrefix", "/SpringMvcHibernate");
        return "main";
    }
    
}
