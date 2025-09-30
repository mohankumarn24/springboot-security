package net.projectsync.springsecurity.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import net.projectsync.springsecurity.model.security.AppUser;

// used to check if user exists in db
public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
