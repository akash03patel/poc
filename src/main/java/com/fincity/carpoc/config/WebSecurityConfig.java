package com.fincity.carpoc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * The type Web security config.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  /**
   * RequestFilter.
   */
  @Autowired
  private RequestFilter requestFilter;

  /**
   * The Authentication entry point.
   */
  @Autowired
  private AuthenticationEntryPoint authenticationEntryPoint;

  /**
   * UserDetailsService.
   */
  @Autowired
  private UserDetailsService userDetailsService;

  /**
   * Config authentication.
   *
   * @param auth the auth
   * @throws Exception the exception
   */
  @Autowired
  public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }

  /**
   * Password encoder b crypt password encoder.
   *
   * @return the b crypt password encoder
   */
  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    //http.csrf().disable();
    //http.authorizeRequests().antMatchers("/").permitAll();
    http
        .cors()
        .and()
        .csrf().disable()

        // .authorizeRequests().antMatchers("/**").permitAll()
        // dont authenticate this particular request
        .authorizeRequests().antMatchers("/authentication/login").permitAll()
        .antMatchers("/authentication/register").permitAll()
        .antMatchers("/health").permitAll()
        .anyRequest().authenticated().and()
        // make sure we use stateless session; session won't be used to
        // store user's state.
        .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    http.addFilterBefore(requestFilter, UsernamePasswordAuthenticationFilter.class);
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/v2/api-docs",
        "/configuration/ui",
        "/swagger-resources/**",
        "/configuration/security",
        "/swagger-ui.html",
        "/webjars/**");
  }
}
