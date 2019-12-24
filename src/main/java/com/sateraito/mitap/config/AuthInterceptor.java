package com.sateraito.mitap.config;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sateraito.mitap.constant.Constants;
import com.sateraito.mitap.model.response.ReponseMdl;
import com.sateraito.mitap.service.MitapService;

public class AuthInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//TODO trích xuất method tương ứng với request mapping trong controller
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
 
        //TODO tìm trong method có khai báo anotation Auth?
        Auth roleAnnotation = AnnotationUtils.findAnnotation(method, Auth.class);
        //TODO nếu có lấy ra thuộc tính role, không trả về null
        Auth.Role[] roles = roleAnnotation != null ? roleAnnotation.role() : null;
        if(roles == null) return true;
 
        //TODO lấy các thông tin đăng nhập từ session
        List<Auth.Role> loginRoles = new ArrayList<Auth.Role>();
        try {
        	Cookie[] cookie = request.getCookies();
            for (Cookie itemCookie : cookie) {
            	if(itemCookie.getName().equals("role")) {
//            		loginRole = Auth.Role.valueOf(itemCookie.getValue());
            		String[] arrStrRole = itemCookie.getValue().split("&");
            		for (String strRole : arrStrRole) {
            			Auth.Role role = Auth.Role.valueOf(strRole);
            			loginRoles.add(role);
    				}
            		
            	}
    		}
     
            //TODO kiểm tra sự tồn tại của loginRole trong role
            if (!checkRoleExists(roles, loginRoles)) {
            	ResponseEntity<ReponseMdl> responseEntity = MitapService.responseError(MitapService.NOT_PERMISSTION);
            	response.getWriter().write(Constants.GSON.toJson(responseEntity.getBody()));
                return false;
            }
            return true;
		} catch (Exception e) {
			return false;
		}
        
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	}

	private boolean checkRoleExists(Auth.Role[] roles, List<Auth.Role> loginRoles){
		for (Auth.Role role : roles) {
			for (Auth.Role loginRole : loginRoles) {
				if(loginRole == role) return true;	
			}
		}
		return false;
	}
}
