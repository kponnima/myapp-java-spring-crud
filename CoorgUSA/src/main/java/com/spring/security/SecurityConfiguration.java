package com.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("customUserDetailsService")
	UserDetailsService userDetailsService;

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/registration", "/forgotpswd", "/thankyou").permitAll()
				.antMatchers("/home", "/aboutcoorg", "/aboutcoorgusa", "/news", "/events", "/blogs", "/editpost",
						"/albums", "/photos", "/songs", "/videos", "/contactus", "/search", "/aboutus",
						"/privacypolicy", "/termsofservice")
				.access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
				.antMatchers("/userslist", "/edituser", "/editevent", "/editpost")
				.access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DBA')").and().formLogin().loginPage("/login")
				.failureUrl("/login?error").loginProcessingUrl("/login").defaultSuccessUrl("/home")
				.usernameParameter("username").passwordParameter("password").and().logout()
				.logoutSuccessUrl("/login?logout").deleteCookies("JSESSIONID").invalidateHttpSession(false).and().csrf()
				.and().exceptionHandling().accessDeniedPage("/accessDenied");

		/*
		 * http.sessionManagement().invalidSessionUrl("/default").
		 * maximumSessions(2).expiredUrl("/thankyou")
		 * .maxSessionsPreventsLogin(true);
		 */
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean
	public AuthenticationTrustResolver getAuthenticationTrustResolver() {
		return new AuthenticationTrustResolverImpl();
	}

}