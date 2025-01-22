package com.soyoung.springboot.myfirstwebapp.security;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
public class SpringSecurityConfiguration {

	// LDAP or Database
	// In Memory

	@Bean
	public InMemoryUserDetailsManager createUserDetailsManager() {
		UserDetails userDetails1 = createNewUser("hehe", "qwer");
		UserDetails userDetails2 = createNewUser("Soyoung", "asdf");
		return new InMemoryUserDetailsManager(userDetails1, userDetails2);
	}

	private UserDetails createNewUser(String username, String password) {
		Function<String, String> passwordEncoder = input -> passwordEncoder().encode(input);
		UserDetails userDetails = User.builder().passwordEncoder(passwordEncoder).username(username).password(password)
				.roles("USER", "ADMIN").build();
		return userDetails;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/** Whenever web request comes in, it will be processed by this chain first. 
	 * Default Security Chain Behavior is like this:
	 * - All URLs are protected
	 * - A login form is shown for unauthorized requests
	 * To Enable H2 UI,
	 * - CSRF: Cross Site Request Forgery needs to be disabled
	 * - H2 websites uses "Frames" in HTML. Allow Frame usage
	 * @throws Exception 
	 * */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		RequestMatcher optionsMatcher = new AntPathRequestMatcher("/**", HttpMethod.OPTIONS.toString());
	    http.authorizeHttpRequests(auth -> auth
	            .requestMatchers(optionsMatcher).permitAll()
	            .requestMatchers(new AntPathRequestMatcher("/**")).authenticated());

	    http.csrf(csrf -> csrf.disable());

	    http.httpBasic(withDefaults());
	    http.headers(headers -> headers.frameOptions(frameOptionsConfig-> frameOptionsConfig.disable()));
	    return http.build();
	}

}
