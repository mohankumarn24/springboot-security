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
public class CreateUsersAtStartup {

    @Bean
    public CommandLineRunner init(UserRepository repo, PasswordEncoder encoder) {
        return args -> {
            createUserIfNotExists(repo, encoder, "admin", "password", Role.ROLE_ADMIN);
            createUserIfNotExists(repo, encoder, "mohan", "password", Role.ROLE_USER);
        };
    }

    private void createUserIfNotExists(UserRepository repo, PasswordEncoder encoder,
                                       String username, String rawPassword, Role role) {
        if (repo.findByUsername(username) == null) {
            AppUser user = new AppUser();
            user.setUsername(username);
            user.setPassword(encoder.encode(rawPassword));
            user.setRole(role);
            repo.save(user);
            System.out.println("Created user: " + username + " with role: " + role);
        }
    }
}
