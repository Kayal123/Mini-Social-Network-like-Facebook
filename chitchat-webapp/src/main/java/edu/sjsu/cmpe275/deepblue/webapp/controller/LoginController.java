package edu.sjsu.cmpe275.deepblue.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	@RequestMapping(value = { "/login.html" }, method = { RequestMethod.GET })
	public String login(Model model) {
		return "login";
	}

	@RequestMapping(value = { "/loginFailure.html" }, method = { RequestMethod.GET })
	public String loginFailure(Model model) {
		model.addAttribute("error", "true");
		return "login";
	}

	@RequestMapping(value = { "/logout.html" }, method = { RequestMethod.GET })
	public String logout(Model model) {
		model.addAttribute("logout", "true");
		return "login";
	}
	
	@RequestMapping(value = { "/register.html" })
	public String launchRegisterForm(Model model) {
		return "profileInputForm";
	}
}
