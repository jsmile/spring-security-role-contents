package com.jsmile.springsecurity.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter
{

	// configure users( memory, DB, LDAP )
	@Override
	protected void configure( AuthenticationManagerBuilder _auth ) throws Exception
	{
		// Add users for in memory authentication
		UserBuilder users = User.withDefaultPasswordEncoder();
		
		_auth.inMemoryAuthentication()
					.withUser( users.username( "john" ).password( "test123" ).roles( "EMPLOYEE" ) )
					.withUser( users.username( "marry" ).password( "test123" ).roles( "MANAGER" ) )
					.withUser( users.username( "susan" ).password( "test123" ).roles( "ADMIN" ) );
	}

	
	// configure security of web paths in application : login, logout, ...
	@Override
	protected void configure( HttpSecurity _http ) throws Exception
	{
		// Restrict access based on the HttpServletRequest
		_http.authorizeRequests()
						.anyRequest().authenticated()
					.and()
					.formLogin()
						.loginPage( "/showLoginPage" )
						.loginProcessingUrl( "/authenticateTheUser" ).permitAll()
					.and()
					.logout().permitAll();
	}

}
