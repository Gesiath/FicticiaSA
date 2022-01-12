package com.FicticiaSA.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.FicticiaSA.services.ServiceClient;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)

public class mainConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private ServiceClient serviceClient;
	
	@Autowired
	public void configueGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(serviceClient).passwordEncoder(new BCryptPasswordEncoder());	
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/css/*", "/js/*", "/img/*", "/**").permitAll()
			.and().formLogin()
				.loginPage("/login")
				.usernameParameter("mail")
				.passwordParameter("password")
				.defaultSuccessUrl("/")
				.loginProcessingUrl("/logincheck")
				.failureUrl("/login?error=error")
				.permitAll()
			.and().logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login?logout")
			.and().csrf().disable();
			
	}
	
}
