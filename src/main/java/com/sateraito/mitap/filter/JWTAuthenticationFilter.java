package com.sateraito.mitap.filter;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Jwts;
 
public class JWTAuthenticationFilter extends GenericFilterBean {
	public static final String HEADER_STRING = "Authorization";
    
	/*
	 * thực hiện Authentication đối với Request
	 */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Authentication authentication = getAuthentication((HttpServletRequest) servletRequest);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(servletRequest, servletResponse);
    }
    
    /*
     * Kiểm tra token được truyền lên bởi request có hiệu lực nữa hay không
     */
    private Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            String user = Jwts.parser().setSigningKey(JWTLoginFilter.SECRET).parseClaimsJws(token.replace(JWTLoginFilter.TOKEN_PREFIX, "")).getBody().getSubject();
            if(user != null) {
            	return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
            }
        }
        return null;
    }
    
    
     
}