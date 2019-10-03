package com.sateraito.mitap.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sateraito.mitap.constant.Constants;
import com.sateraito.mitap.model.request.UserLoginRequest;
import com.sateraito.mitap.model.response.LoginResponse;
import com.sateraito.mitap.model.response.ReponseMdl;
import com.sateraito.mitap.model.response.UserInfoResponse;
import com.sateraito.mitap.service.MitapService;
import com.sateraito.mitap.service.UserDetailsServiceImpl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
 
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {
	public static final long EXPIRATIONTIME = 864_000_000; // 10 days
	public static final String TOKEN_PREFIX = "Bearer";
	public static final String SECRET = "ThisIsASecret";
	private UserDetailsServiceImpl userDetailsService;
 
    public JWTLoginFilter(String url, AuthenticationManager authManager, UserDetailsServiceImpl userDetailsService) {
        super(new AntPathRequestMatcher(url));
        this.userDetailsService = userDetailsService;
        setAuthenticationManager(authManager);
    }
 
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
    	String username = "";
    	String password = "";
    	String strUsername = "username";
    	String strPassword = "password";
    	if(request.getMethod().equals(RequestMethod.POST.name()) && request.getParameter(strUsername) == null) {
    		//trong trường hợp username hoạc password có những ký tự đặc biệt thì khi gửi qua url các ký tự đặc biệt đó sẽ bị thay đổi
    		// làm cho việc login trở nên không chính xác. Vì vậy phải gửi username và password bằng method POST với Object
    		try {
    			StringBuffer jb = new StringBuffer();
    	      	String line = null;
	      	    BufferedReader reader = request.getReader();
	      	    while ((line = reader.readLine()) != null)
	      	    	jb.append(line);
    	      	String jsonLogin = jb.toString();
    	      	UserLoginRequest loginRequest = Constants.GSON.fromJson(jsonLogin, UserLoginRequest.class);
    	      	username = loginRequest.getUsername();
    	      	password = loginRequest.getPassword();
			} catch (Exception e) {}
    	} else {
    		username = request.getParameter(strUsername); 
            password = request.getParameter(strPassword);
    	}
        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList()));
    }
 
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String JWT = Jwts.builder().setSubject(authResult.getName())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();
        String token = TOKEN_PREFIX + JWT;
        ReponseMdl reponseMdl = ReponseMdl.getInsSuccess();
        UserInfoResponse userInfo = null;
        try {
        	userInfo = (UserInfoResponse) userDetailsService.userInfo(authResult.getName()).getBody().getData();
		} catch (Exception e) {}
        LoginResponse loginRes = new LoginResponse();
        loginRes.setToken(token);
        loginRes.setUserInfo(userInfo);
        reponseMdl.setData(loginRes);
        response.getWriter().write(Constants.GSON.toJson(reponseMdl));
    }
    
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
    	ReponseMdl reponseMdl = ReponseMdl.getInsErrorDefault();
    	if(failed instanceof LockedException) {
    		reponseMdl = ReponseMdl.getInsErrorMessage(MitapService.ACCOUNT_LOCK, MitapService.mapErrorReponse.get(MitapService.ACCOUNT_LOCK));
    	} else if(failed instanceof DisabledException) {
    		reponseMdl = ReponseMdl.getInsErrorMessage(MitapService.ACCOUNT_DISABLE, MitapService.mapErrorReponse.get(MitapService.ACCOUNT_DISABLE));
    	}
    	reponseMdl.setData("");
        response.getWriter().write(Constants.GSON.toJson(reponseMdl));
    }
    
}