package com.capg.oauthserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity // registers as security config class
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// method to expose Authentication manager (spring-security)
	@Bean(name = "authenticationManager")
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}
	
	
	// config for credential repository
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// in memory credential
		auth.inMemoryAuthentication()
				.withUser("manager")
				.password(this.passwordEncoder.encode("abc"))
				.roles("MANAGER");
		auth.inMemoryAuthentication()
				.withUser("admin")
				.password(this.passwordEncoder.encode("abc"))
				.roles("ADMIN");
	}
	
	
}
