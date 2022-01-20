package io.github.arthurgmr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import io.github.arthurgmr.security.JWTService;
import io.github.arthurgmr.security.JwtAuthFilter;
import io.github.arthurgmr.service.implementation.UserServiceImpl;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private JWTService jwtService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		//crypt password;
		return new BCryptPasswordEncoder();
	}

	// call and inject the filter;
	@Bean
	public OncePerRequestFilter JWTFilter() {
		return new JwtAuthFilter(jwtService, userService);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userService)
			.passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/api/client/**")
					.hasAnyRole("USER", "ADMIN")
				.antMatchers("/api/order/**")
					.hasAnyRole("USER", "ADMIN")
				.antMatchers("/api/product/**")
					.hasRole("ADMIN")
				.antMatchers(HttpMethod.POST, "/api/user/**")
					.permitAll()
				.anyRequest().authenticated()
			.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.addFilterBefore(JWTFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
}
