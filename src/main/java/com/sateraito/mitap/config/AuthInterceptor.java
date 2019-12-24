package com.sateraito.mitap.config;

import java.lang.reflect.Method;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

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
        Auth.Role loginRole = null;
        Cookie[] cookie = request.getCookies();
        for (Cookie itemCookie : cookie) {
        	if(itemCookie.getName().equals("role")) {
        		loginRole = Auth.Role.valueOf(itemCookie.getValue());
        	}
		}
 
        //TODO kiểm tra sự tồn tại của loginRole trong role
        if (!checkRoleExists(roles, loginRole)) {
            return false;
        }
        return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	}

	private boolean checkRoleExists(Auth.Role[] roles, Auth.Role loginRole){
		for (Auth.Role role : roles) {
			if(loginRole == role) return true;
		}
		return false;
	}
}
