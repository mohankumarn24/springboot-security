package net.projectsync.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import net.projectsync.springsecurity.dto.RegistrationRequest;
import net.projectsync.springsecurity.service.security.UserService;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class RegistrationController {

	@Autowired
	private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegistrationRequest request) {
        if (userService.userExists(request.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }

        userService.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }
}
