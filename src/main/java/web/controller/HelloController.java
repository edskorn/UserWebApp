package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class HelloController {

	@Autowired
	private UserService userService;

	@GetMapping(value = "/")
	public String printWelcome(ModelMap model) {
		List<User> users = userService.getAllUsers();
		model.addAttribute("allUsers", users);
		return "index";
	}

	@RequestMapping(value = "/addUser")
	public String addUser(ModelMap model){
		model.addAttribute("user", new User());
		return "add-user";
	}

	@RequestMapping(value = "/saveUser")
	public String saveUser(@ModelAttribute("user") User user){
		userService.saveUser(user.getName(), user.getLastName(), user.getAge());
		return "redirect:/";
	}



//	@GetMapping(value = "/")
//	public String askUser(ModelMap model) {
//		model.addAttribute("user", new User());
//		return "ask-user";
//	}
//
//	@GetMapping(value = "/showDetails")
//	public String showDetails(@ModelAttribute("user") User user) {
//		//model.addAttribute("showName", usrName);
//		user.setName("Mr. " + user.getName());
//		return "show-details";
//	}
//	@GetMapping(value = "/showDetails")
//	public String showDetails(@RequestParam(name = "userName") String usrName, ModelMap model) {
//		model.addAttribute("showName", usrName);
//		return "show-details";
//	}
	
}