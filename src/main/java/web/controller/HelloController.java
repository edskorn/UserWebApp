package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {

	@GetMapping(value = "/hello")
	public String printWelcome(ModelMap model) {
		List<String> messages = new ArrayList<>();
		messages.add("Hello!");
		messages.add("I'm Spring MVC application");
		messages.add("5.2.0 version by sep'19 ");
		model.addAttribute("messages", messages);
		return "index";
	}

	@GetMapping(value = "/")
	public String askUser(ModelMap model) {
		model.addAttribute("user", new User());
		return "ask-user";
	}

	@GetMapping(value = "/showDetails")
	public String showDetails(@ModelAttribute("user") User user) {
		//model.addAttribute("showName", usrName);
		user.setName("Mr. " + user.getName());
		return "show-details";
	}
//	@GetMapping(value = "/showDetails")
//	public String showDetails(@RequestParam(name = "userName") String usrName, ModelMap model) {
//		model.addAttribute("showName", usrName);
//		return "show-details";
//	}
	
}