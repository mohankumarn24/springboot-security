package net.projectsync.springsecurity.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Autowired
    private CustomSuccessHandler successHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/login", "/dashboard", "/welcome", "/css/**", "/api/v1/register").permitAll()
                .antMatchers("/employees/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            .and()
            .formLogin()
                .loginPage("/login")
                .successHandler(successHandler)
                .permitAll()
            .and()
            .logout()
            	.logoutUrl("/perform_logout") // custom logout endpoint
            	// .logoutSuccessUrl("/login?logout") // redirect after logout   
            	.logoutSuccessUrl("/logout-success") // .use "logoutSuccessUrl("/logout-success")" to show custom message or page
            	.invalidateHttpSession(true)
            	.deleteCookies("JSESSIONID")
            	.permitAll();

            /* default logout
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll();
			*/
        
        return http.build();
    }

    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.builder()
                .username("mohan")
                .password(passwordEncoder.encode("password"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin123"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
    */

    /*
    // Without using encoder. Remove passwordEncoder() method to work
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
	    UserDetails user = User.withUsername("mohan")
	            .password("{noop}password")
	            .roles("USER")
	            .build();

	    UserDetails admin = User.withUsername("admin")
	            .password("{noop}admin123")
	            .roles("ADMIN")
	            .build();

	    return new InMemoryUserDetailsManager(user, admin);
	}
	*/
    
    /*
	// after login displays default welcome page or redirects to original endpoint
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/login", "/css/**", "/js/**").permitAll()
                .antMatchers("/employees/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            .and()
            .formLogin()
                .loginPage("/login") // custom login page
                .defaultSuccessUrl("/welcome", true) 
                // redirect after login. If you set true to false, Spring will redirect to the originally requested page (if any), otherwise fallback to /welcome.
                .permitAll()
            .and()
            .logout()
                .logoutSuccessUrl("/login?logout")
                .permitAll();

        return http.build();
    }
    */
}
