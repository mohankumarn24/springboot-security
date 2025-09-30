package net.projectsync.springsecurity.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login() {
		return "login"; // resolves to templates/login.html
	}

	@GetMapping("/welcome")
	public ResponseEntity<String> welcome() {
		return new ResponseEntity<String>("User logged in successfully. This is the default page after login", HttpStatus.OK);
	}
	
    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard"; // renders dashboard.html
    }

    @GetMapping("/employees")
    public String employees() {
        return "employees"; // looks for employees.html in templates/
    }
}