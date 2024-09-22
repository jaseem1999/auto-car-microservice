package com.microservice.admin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import com.microservice.admin.jwt.JwtRequestFilter;

@Configuration
@EnableWebSecurity
public class Config  extends WebSecurityConfigurerAdapter  {
	
		
	
	 	@Autowired
	    private JwtRequestFilter jwtRequestFilter;

	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.inMemoryAuthentication()
	            .withUser("adil")
	            .password(passwordEncoder().encode("123321"))
	            .roles("ADMIN");
	    }

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.csrf().disable()
	            .authorizeRequests()
	                .antMatchers("/admin/login", "/control/driver/add").permitAll() // Allow unrestricted access to the login endpoint
	                .antMatchers("/admin/add").authenticated() // Require authentication for the add endpoint
	                .antMatchers("/v2/api-docs", "/swagger-resources/**", "/swagger-ui/**", "/webjars/**").permitAll() // Allow access to Swagger UI
	                .anyRequest().authenticated() // Require authentication for all other endpoints
	            .and()
	            .sessionManagement()
	                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Ensure stateless session management
	            .and()
	            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class) // Add JWT filter before UsernamePasswordAuthenticationFilter
	            .formLogin()
	                .loginPage("/admin/login") // Custom login page
	                .permitAll() // Allow unrestricted access to the login page
	            .and()
	            .httpBasic(); // Optionally enable HTTP Basic authentication
	    }



	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	 @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	        return authenticationConfiguration.getAuthenticationManager();
	    }
	 
	 @Bean
	    public HttpFirewall allowUrlEncodedDoubleSlashHttpFirewall() {
	        StrictHttpFirewall firewall = new StrictHttpFirewall();
	        firewall.setAllowUrlEncodedDoubleSlash(true);
	        return firewall;
	    }

	
}
