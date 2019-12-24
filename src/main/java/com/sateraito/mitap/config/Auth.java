package com.sateraito.mitap.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
//khai báo annotation này có hiệu lực với method
@Target(ElementType.METHOD)
public @interface Auth {
	// ở đây mình set cứng gồm 1 số quyền sau, chúng ta có thể thay thế sang
	// String hoặc int để sử dụng kèm db
	public enum Role {
		ROLE_ADMIN, ROLE_USER, ROLE_USER_DIRECTOR, ROLE_USER_TOURIST
	};

	// khai báo properties cho anotation với giá trị mặc định là ROLE_ADMIN
	// ví dụ: @Auth(role = Role.ROLE_ADMIN)
	public Role[] role(); // default = @Auth()

}
