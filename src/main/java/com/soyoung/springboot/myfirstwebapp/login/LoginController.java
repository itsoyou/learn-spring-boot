package com.soyoung.springboot.myfirstwebapp.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class LoginController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private AuthenticationService authenticationService;

	public LoginController(AuthenticationService authenticationService) {
		super();
		this.authenticationService = authenticationService;
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String gotoLoginPage() {
		return "login";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String gotoWelcomePage(@RequestParam String name, @RequestParam String password, ModelMap model) {
		logger.debug("Request Param is: {}", name);

		// Authentication
		if (authenticationService.authenticate(name, password)) {

			// Model: Pass variable from Controller to JSP!! Use ModelMap.
			model.put("name", name);

			return "welcome";
		}
		
		model.put("errorMessage", "Invalid Credentials. Please try again.");
		return "login";
	}

}
