package net.projectsync.springsecurity.configuration.security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import net.projectsync.springsecurity.enums.Role;
import net.projectsync.springsecurity.model.security.AppUser;
import net.projectsync.springsecurity.repository.security.UserRepository;

/**
 * create users on startup
 */
@Configuration
public class CreateUsers {
	
	@Bean
	public CommandLineRunner init(UserRepository repo, PasswordEncoder encoder) {
	    return args -> {
	        if (repo.findByUsername("admin") == null) {
	            AppUser admin = new AppUser();
	            admin.setUsername("admin");
	            admin.setPassword(encoder.encode("password"));
	            admin.setRole(Role.ROLE_ADMIN); // ✅ enum value
	            repo.save(admin);
	        }

	        if (repo.findByUsername("mohan") == null) {
	            AppUser user = new AppUser();
	            user.setUsername("mohan");
	            user.setPassword(encoder.encode("password"));
	            user.setRole(Role.ROLE_USER); // ✅ enum value
	            repo.save(user);
	        }
	    };
	}
}
