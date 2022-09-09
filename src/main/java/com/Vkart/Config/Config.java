package com.Vkart.Config;

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

@Configuration
@EnableWebSecurity
public class Config extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userdetailservice() {
		return new userDetailsService();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();

	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoauthenticationProvider = new DaoAuthenticationProvider();
		daoauthenticationProvider.setUserDetailsService(this.userdetailservice());
		daoauthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoauthenticationProvider;

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/admin/**").hasAuthority("ADMIN")
	    .antMatchers("/user/**").hasAuthority("USER")
         .antMatchers("/**").permitAll().and().formLogin().loginPage("/login").loginProcessingUrl("/login").defaultSuccessUrl("/admin/home")
        .and().logout() .and().csrf().disable();
		// login work has been finished

	}

}
