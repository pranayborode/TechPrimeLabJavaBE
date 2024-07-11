package com.techprimelab.Controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/session")
public class SessionController {

	@GetMapping("/storeData")
	public String storeData(HttpServletRequest request, String userName) {
		HttpSession session = request.getSession();
		session.setAttribute("username", userName);
		return "data_stored";
	}
	
	@GetMapping("/getData")
	public String getData(HttpSession session) {
	    String username = (String) session.getAttribute("username");
	    return "Hello, " + username;
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
	    session.invalidate();
	    return "logged_out";
	}
}
