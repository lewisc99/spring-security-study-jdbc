package com.luv2code.springsecurity.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;



@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

	
	// add a reference to our security data souce
	
	@Autowired
	private DataSource securityDataSource;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
 
	/*//add our users for in memory authentication
		
		UserBuilder users = User.withDefaultPasswordEncoder();
		
		auth.inMemoryAuthentication()
		.withUser(users.username("john").password("test123").roles("EMPLOYEE"))
		.withUser(users.username("mary").password("test123").roles("MANAGER","EMPLOYEE"))
		.withUser(users.username("susan").password("test123").roles("ADMIN","EMPLOYEE"));
		
		*/
		
		//use jdbc authentication ... oh year!!!
		
		//telll spring Security to use JDBC authentication with our data source.
		
		auth.jdbcAuthentication().dataSource(securityDataSource);
		
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		http.authorizeRequests()
		//	.anyRequest().authenticated() //any request to the app must be authenticated (ie logged in).
			.antMatchers("/").hasRole("EMPLOYEE")
			.antMatchers("/leaders/**").hasRole("MANAGER")
			.antMatchers("/systems/**").hasRole("ADMIN")
			.and()
			.formLogin()
			.loginPage("/showMyLoginPage")
			.loginProcessingUrl("/authenticateTheUser") //Login form should POST data to this URL for processing.
			.permitAll()
			.and()
			.logout().permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/access-denied");
		
	 
	}

	
	
}
