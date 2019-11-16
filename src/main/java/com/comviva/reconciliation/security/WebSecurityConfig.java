package com.comviva.reconciliation.security;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.slf4j.Logger;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService userDetailsService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WebSecurityConfig.class);
	
	@Bean
	public AuthenticationProvider authProvider() {
		LOGGER.info("Constructing Authentication Provider.....");
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		super.configure(auth);
	}

	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		 * http .csrf().disable() .authorizeRequests().antMatchers("/index").permitAll()
		 * .antMatchers("/css/**").permitAll() .anyRequest().authenticated() .and()
		 * .formLogin() .loginPage("/index").permitAll() .and()
		 * .logout().invalidateHttpSession(true) .clearAuthentication(true)
		 * .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		 * .logoutSuccessUrl("/logout-success").permitAll();
		 */
		http .csrf().disable() .authorizeRequests().antMatchers("/index").permitAll()
		 .antMatchers("/getAllTransactions").permitAll()
		 .antMatchers("/getAllFailedTransactions").permitAll()
		 .antMatchers("/css/**").permitAll()
		 .antMatchers("/images/**").permitAll()
		 .anyRequest().authenticated() .and()
		 .formLogin() .loginPage("/index").permitAll()
		 .and()
		 .logout().invalidateHttpSession(true) .clearAuthentication(true)
		 .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		 .logoutSuccessUrl("/logout-success").permitAll();
			
	}
	

}
