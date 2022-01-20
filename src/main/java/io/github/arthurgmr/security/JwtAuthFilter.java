package io.github.arthurgmr.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import io.github.arthurgmr.service.implementation.UserServiceImpl;

public class JwtAuthFilter extends OncePerRequestFilter {

    private JWTService JWTService;
    private UserServiceImpl userService;


    public JwtAuthFilter(io.github.arthurgmr.security.JWTService jWTService, UserServiceImpl userService) {
        JWTService = jWTService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, 
            HttpServletResponse response, 
            FilterChain filterChain) throws ServletException, IOException {
        
        //get authorization header;
        String auth = request.getHeader("Authorization");
        //check header            
        if (auth != null && auth.startsWith("Bearer")) {
            String token = auth.split(" ")[1];
            Boolean isValid = JWTService.checkToken(token);
            
            if(isValid) {
                String loginUser = JWTService.getLogin(token);
                UserDetails user =  userService.loadUserByUsername(loginUser);
                //set web credentials;
                UsernamePasswordAuthenticationToken userAuth = new 
                    UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                userAuth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    //set authentication in spring security context;
                    SecurityContextHolder.getContext().setAuthentication(userAuth);
            }
        }

        // The filter is responsible for intercept the request,
        // processing the information(headers, set context auth, etc)
        // and continuing the flow;
        // It's will be apply in the SecurityConfig
        
        filterChain.doFilter(request, response);
    }
    
}

