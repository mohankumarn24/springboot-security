package net.projectsync.springsecurity.service.security;

import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import net.projectsync.springsecurity.model.security.AppUser;
import net.projectsync.springsecurity.repository.security.UserRepository;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository repo;

	public CustomUserDetailsService(UserRepository repo) {
		this.repo = repo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser user = repo.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}

		return new User(user.getUsername(), user.getPassword(),
				Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name())));
	}
}