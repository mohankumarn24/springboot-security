package net.projectsync.springsecurity.configuration.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Set;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, 
    									HttpServletResponse response, 
    									Authentication authentication) throws IOException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if (roles.contains("ROLE_ADMIN")) {
            response.sendRedirect("/employees");	 // return employees.html
        } else if (roles.contains("ROLE_USER")) {
            // response.sendRedirect("/dashboard");  // return dashboard.html
        	response.sendRedirect("/welcome");		 // hit /welcome endpoint
        } else {
            response.sendRedirect("/login?error");
        }
    }
}