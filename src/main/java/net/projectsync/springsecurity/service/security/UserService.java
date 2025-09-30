package net.projectsync.springsecurity.service.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import net.projectsync.springsecurity.dto.RegistrationRequest;
import net.projectsync.springsecurity.model.security.AppUser;
import net.projectsync.springsecurity.repository.security.UserRepository;

@Service
public class UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean userExists(String username) {
        return userRepository.findByUsername(username) != null;
    }

    public AppUser registerUser(RegistrationRequest request) {
    	
    	logger.info("Registering user: {}", request.getUsername());
    	
        AppUser user = new AppUser();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        return userRepository.save(user);
    }
}
