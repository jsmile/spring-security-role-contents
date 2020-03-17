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
public class SecurityConfig extends WebSecurityConfigurerAdapter
{

	// configure users( memory, DB, LDAP )
	@Override
	protected void configure( AuthenticationManagerBuilder _auth ) throws Exception
	{
		// Add users for in memory authentication
		UserBuilder users = User.withDefaultPasswordEncoder();
		
		_auth.inMemoryAuthentication()
					.withUser( users.username( "john" ).password( "test123" ).roles( "EMPLOYEE" ) )
					.withUser( users.username( "marry" ).password( "test123" ).roles( "EMPLOYEE", "MANAGER" ) )
					.withUser( users.username( "susan" ).password( "test123" ).roles( "EMPLOYEE", "ADMIN" ) );
	}

	
	// configure security of web paths in application : login, logout, ...
	@Override
	protected void configure( HttpSecurity _http ) throws Exception
	{
/*		
		// Restrict all access based on the HttpServletRequest
		_http.authorizeRequests()
						.anyRequest().authenticated()	// check all accesses
					.and()
					.formLogin()
						.loginPage( "/showLoginPage" )
						.loginProcessingUrl( "/authenticateTheUser" ).permitAll()
					.and()
					.logout().permitAll();
*/		

		// Restrict accesses based on the HttpServletRequest without a home page.
		_http.authorizeRequests()
					.antMatchers( "/" ).permitAll()		// no check of landing page
						.antMatchers( "/employees" ).hasRole( "EMPLOYEE" )
						.antMatchers( "/leaders/**" ).hasRole( "MANAGER" )
						.antMatchers( "/systems/**" ).hasRole( "ADMIN" )
					.and()
					.formLogin()
						.loginPage( "/showLoginPage" )
						.loginProcessingUrl( "/authenticateTheUser" ).permitAll()
					.and()
					.logout()
						.logoutSuccessUrl( "/" ).permitAll()		// after successful logout
					.and()
					.exceptionHandling()
						.accessDeniedPage( "/access-denied" );	// access denied
						
	}

}
