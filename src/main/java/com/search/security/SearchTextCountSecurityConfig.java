package com.search.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import static com.search.constants.SearchTextCountConstants.USERNAME;
import static com.search.constants.SearchTextCountConstants.PASSWORD;
import static com.search.constants.SearchTextCountConstants.ROLES;

@EnableWebSecurity
public class SearchTextCountSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
		/*
		 * In real application, we need to fetch the user information from
		 * database or LDAP. Here, providing dummy user for demo purpose.
		 */		
		auth.inMemoryAuthentication().withUser(USERNAME).password(PASSWORD).roles(ROLES);
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		((HttpSecurity) ((HttpSecurity) ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl) http.authorizeRequests()
				.anyRequest()).authenticated().and()).formLogin().and()).httpBasic();
		http.csrf().disable();
	}
}