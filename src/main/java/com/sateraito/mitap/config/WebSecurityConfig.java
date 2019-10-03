package com.sateraito.mitap.config;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.sateraito.mitap.constant.Constants;
import com.sateraito.mitap.filter.JWTAuthenticationFilter;
import com.sateraito.mitap.filter.JWTLoginFilter;
import com.sateraito.mitap.service.UserDetailsServiceImpl;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private UserDetailsServiceImpl userDetailsService;
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
         auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
 
	/*
	 * Các link không cần login vẫn có thể truy cập được bao gồm /, /login, /token, /register
	 * Các link còn lại để có thể truy cập được thì đầu tiên phải thực hiện login sau đó nhận được 1 token trả về sau khi login 
	 * khi có được token thì thực hiện truyền token đó vào header của request muốn thực hiện với key 'Authorization'
	 * Lưu ý token này chỉ tồn tại 10 days sau đó token sẽ mất yêu cầu người dùng phải login lại
	 */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.headers().frameOptions().disable().and().authorizeRequests()
	        // No need authentication.
	        .antMatchers("/").permitAll()
	        .antMatchers(HttpMethod.POST, Constants.LOGIN).permitAll()
	        .antMatchers(HttpMethod.GET, Constants.LOGIN).permitAll()
	        .antMatchers(Constants.REGISTER_ACCURACY_PHONE).permitAll()
	        .antMatchers(Constants.REGISTER_ACCURACY_EMAIL).permitAll()
	        .antMatchers(Constants.REGISTER_COMPACT).permitAll()
	        .antMatchers(Constants.REGISTER_SOCIAL).permitAll()
	        .antMatchers(HttpMethod.GET, Constants.CONFIRM_REGISTER).permitAll()
	        .antMatchers(Constants.UPDATE_PASSWORD).permitAll()//xem lai
	        .antMatchers(Constants.FORGET_PASSWORD).permitAll()
	        .antMatchers(Constants.NEW_PASSWORD).permitAll()
	        .antMatchers(Constants.API_PUBLIC + "/**").permitAll()
	        .antMatchers(Constants.API_REDIRECT + "/**").permitAll()
	        .antMatchers("/css/**", "/fonts/**", "/image/**", "/js/**").permitAll()
	        // tất cả những trang web còn lại Need authentication.
	        .anyRequest().authenticated().and()
	        .addFilterBefore(new JWTLoginFilter(Constants.LOGIN, authenticationManager(), userDetailsService), UsernamePasswordAuthenticationFilter.class)
	        .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    	http.cors().configurationSource(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedHeaders(Collections.singletonList("*"));
                config.setAllowedMethods(Collections.singletonList("*"));
                config.addAllowedOrigin("*");
                config.setAllowCredentials(true);
                return config;
            }
          });
    	http.csrf().disable();
    }
 
}