package net.projectsync.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * TODO: I dont need thymeleaf page ater login. I need rest response. Use /welcome endpoint
 */
@Controller
public class LoginController {

    // Renders login.html
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    /*
    // REST-style welcome message (used in redirect for ROLE_USER)
    @GetMapping("/welcome")
    @ResponseBody
    public String welcomeMessage() {
        return "User logged in successfully. This is the default page after login.";
    }
    */

    @GetMapping("/welcome")
    public String welcomePage() {
        return "welcome"; // resolves to welcome.html
    }

    // Renders dashboard.html
    @GetMapping("/dashboard")
    public String dashboardPage() {
        return "dashboard";
    }

    // Renders employees.html
    @GetMapping("/employees")
    public String employeesPage() {
        return "employees";
    }
}