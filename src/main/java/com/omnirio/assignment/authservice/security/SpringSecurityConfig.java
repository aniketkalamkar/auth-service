package com.omnirio.assignment.authservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.omnirio.assignment.authservice.security.filter.AuthenticationFilter;
import com.omnirio.assignment.authservice.security.filter.AuthorizationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(
		  prePostEnabled = true, // (1)
		  securedEnabled = true, // (2)
		  jsr250Enabled = true) 
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
//		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

		http.authorizeRequests().
		antMatchers("/hello/**").permitAll().
		and().authorizeRequests().
		//antMatchers("/api/**").
//		antMatchers("/login").permitAll().
		antMatchers(HttpMethod.GET,
				"/api/user/*").hasAnyAuthority("ROLE_C","ROLE_M").
		antMatchers("/api/user/**").hasAuthority("ROLE_M").
//		antMatchers("df")
		anyRequest().authenticated().
		and()
        .addFilter(new AuthenticationFilter(authenticationManager()))
        .addFilter(new AuthorizationFilter(authenticationManager()))
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


//		http.authorizeRequests().
//		antMatchers("/hello").permitAll().
//		antMatchers("/api/").hasAuthority("ROLE_C").
//		anyRequest().authenticated().and()
//        .httpBasic()
//        .authenticationEntryPoint(authenticationEntryPoint);
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new MyDatabaseUserDetailsService(); // (1)
	}
//
//	@Bean
//	public DaoAuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//		provider.setPasswordEncoder(passwordEncoder);
//		provider.setUserDetailsService(userDetailsService);
//		return provider;
//	}
}