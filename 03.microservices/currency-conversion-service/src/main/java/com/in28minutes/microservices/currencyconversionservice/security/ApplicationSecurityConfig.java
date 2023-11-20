package com.in28minutes.microservices.currencyconversionservice.security;

import com.in28minutes.microservices.currencyconversionservice.auth.ApplicationUserService;
import com.in28minutes.microservices.currencyconversionservice.exceptions.CustomAccessDeniedHandler;
import com.in28minutes.microservices.currencyconversionservice.exceptions.CustomAuthenticationFailureHandler;
import com.in28minutes.microservices.currencyconversionservice.jwt.JwtManualConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.crypto.SecretKey;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final PasswordEncoder passwordEncoder;
	
	private final ApplicationUserService appService;


	private final SecretKey secretKey;

	private final JwtManualConfig jwtManualConfig;



	
	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, ApplicationUserService appService, SecretKey secretKey, JwtManualConfig jwtManualConfig) {
		this.passwordEncoder=passwordEncoder;
		this.appService=appService;
		this.secretKey = secretKey;
		this.jwtManualConfig = jwtManualConfig;
	}

	/**
	 * /*
	 * 				antMatcher to validate permissions
	 * 				 * .antMatchers("/*").hasRole(ApplicationUserRole.ADMIN.name())
	 * 				 * .antMatchers("/*").hasAuthority(
	 * 				 * ApplicationUserPermission.COURSE_WRITE.getPermission())
	 * 				 * .antMatchers("/*").hasAuthority(
	 * 				 * ApplicationUserPermission.COURSE_READ.getPermission())
	 *                                  */
	/*
	 * @param http the {@link HttpSecurity} to modify
	 * @throws Exception
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {


		http
				.csrf().disable()
				//.authorizeRequests().antMatchers("/app/**").permitAll().and()

			/*	.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()*/
				.authorizeRequests().antMatchers("/app/**").authenticated().and()
				.authorizeRequests().antMatchers("/login").authenticated().and()
				//.addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtManualConfig, secretKey))
				//.authorizeRequests().antMatchers("/app/**").permitAll().and()

				//.addFilterAfter(new JwtTokenVerifier(secretKey, jwtManualConfig), JwtUsernameAndPasswordAuthenticationFilter.class)

				.authorizeRequests()

				.anyRequest()
				.authenticated()
				.and()
				.formLogin()
				.loginPage("/login").permitAll()
				.defaultSuccessUrl("/sucess")
				.passwordParameter("password")
				.usernameParameter("username")
				.and()
				.rememberMe()
				.tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
				.key("sha")
				.and()
				.logout()
				.logoutUrl("/logout")
				.clearAuthentication(true)
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID", "remember-me")
				.logoutSuccessUrl("/login")
				.and()
				.formLogin()
				.failureHandler(authenticationFailureHandler())
				.and()
				.exceptionHandling()
				.accessDeniedHandler(accessDeniedHandler());
	}

	/*
	 * @Override
	 * 
	 * @Bean protected UserDetailsService userDetailsService() { UserDetails
	 * imranUser=User.builder().username("imran")
	 * .password(passwordEncoder.encode("password123"))
	 * //.roles(ApplicationUserRole.STUDENT.name())
	 * .authorities(ApplicationUserRole.STUDENT.getAuthority()) .build();
	 * UserDetails ayeshaUser=User.builder().username("ayesha")
	 * .password(passwordEncoder.encode("password123"))
	 * //.roles(ApplicationUserRole.ADMIN.name())
	 * .authorities(ApplicationUserRole.ADMIN.getAuthority()) .build();
	 * 
	 * return new InMemoryUserDetailsManager(imranUser,ayeshaUser); }
	 */
	
	@Bean
	public DaoAuthenticationProvider daoAuthProvider() {
		DaoAuthenticationProvider daoAuthProvider=new DaoAuthenticationProvider();
		daoAuthProvider.setPasswordEncoder(passwordEncoder);
		daoAuthProvider.setUserDetailsService(appService);
		return daoAuthProvider;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthProvider());
	}

	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}


	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		return new CustomAuthenticationFailureHandler();
	}


}
