package org.shoeshop.jpa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }
     
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
     
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
    
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/**/*.js", "/**/*.css","/**/*.png","/**/*.jpg","/***/**/*.png","/***/**/*.jpg").permitAll() //permissão a ficheiros noutras diretorias para todos os utilizadores
				.antMatchers("/","/home","/login","/new-client.html","/new-client","/list-clients","/new-product.html","/new-product").permitAll()  //permite a qualquer entrar nas views /, home e login
				.antMatchers("/adminpage").access("hasRole('ROLE_ADMIN')")
				.anyRequest().permitAll()
				.and()
			.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/home")
				.failureUrl("/login?error=true")
				.permitAll()
				.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	            	.logoutSuccessUrl("/login")
				.permitAll();
	}
}
