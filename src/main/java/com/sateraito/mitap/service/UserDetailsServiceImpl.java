package com.sateraito.mitap.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.sateraito.mitap.entity.MitapUser;
import com.sateraito.mitap.entity.Role;
import com.sateraito.mitap.entity.UserDirection;
import com.sateraito.mitap.entity.UserRole;
import com.sateraito.mitap.model.request.UpdatePasswordByTokenRequest;
import com.sateraito.mitap.model.request.UpdatePasswordRequest;
import com.sateraito.mitap.model.request.UserRegisterRequest;
import com.sateraito.mitap.model.response.ReponseMdl;
import com.sateraito.mitap.model.response.UserDirectionResponse;
import com.sateraito.mitap.model.response.UserInfoResponse;
import com.sateraito.mitap.repo.UserRepo;
import com.sateraito.mitap.utils.EMitapRole;
import com.sateraito.mitap.utils.ExistsUniqueId;
import com.sateraito.mitap.utils.Utils;
import com.sateraito.mitap.validator.EmailValidator;
import com.sateraito.mitap.validator.PasswordValidator;
import com.sateraito.mitap.validator.PhoneValidator;


@Service
@Transactional
public class UserDetailsServiceImpl extends MitapService implements UserDetailsService {
	private PasswordValidator passwordValidator = new PasswordValidator();
	private EmailValidator emailValidator = new EmailValidator();
	private PhoneValidator phoneValidator = new PhoneValidator();
	
	public ResponseEntity<ReponseMdl> index(String uri) {
		return responseSuccessDefault(uri + "");
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MitapUser appUser;
		if(emailValidator.validateEmail(username)) {//email
			appUser = userRepo.findByEmail(username);
		} else if(phoneValidator.validatePhone(username)) {//phone
			appUser = userRepo.findByPhoneNumber(username);
		} else { //username
			appUser = userRepo.findByUsername(username);
		}
		 
        if (appUser == null) {
            System.out.println("User not found! " + username);
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }
        
        List<String> roleNames = userRoleRepo.findRoleNameByUserId(appUser.getId());
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
            for (String role : roleNames) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }
//      user.isAccountNonExpired(); //tài khoản chưa hết hạn
//    	user.isAccountNonLocked();  //tài khoản không bị khóa
//    	user.isCredentialsNonExpired(); // thông tin chưa hết hạn
        boolean enable = appUser.isActive_flag() && !appUser.isDel_flag();
		return new User(appUser.getUsername(), appUser.getPassword(), enable, true, true, !appUser.isLock_flag(), grantList);
	}

	public ResponseEntity<ReponseMdl> register(UserRegisterRequest userRegisterRequest) {
		String strPass = userRegisterRequest.getPassword();
		if(!StringUtils.isEmpty(userRegisterRequest.getUsername()) && userRepo.findByUsername(userRegisterRequest.getUsername()) != null) {
			return responseError(USERNAME_ALREADY_EXIST);
		} else if(!StringUtils.isEmpty(userRegisterRequest.getEmail()) && userRepo.findByEmail(userRegisterRequest.getEmail()) != null) {
			return responseError(EMAIL_ALREADY_EXIST);
		} else if(!StringUtils.isEmpty(userRegisterRequest.getPhone_number()) && userRepo.findByPhoneNumber(userRegisterRequest.getPhone_number()) != null) {
			return responseError(PHONE_NUMBER_ALREADY_EXIST);
		} else if(strPass == null  || !passwordValidator.validatePassword(strPass)) { 
			return responseError(PASSWORD_INVALID);
		} 
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    	String passwordDB = bCryptPasswordEncoder.encode(strPass);
    	MitapUser user = new MitapUser();
    	if(!StringUtils.isEmpty(userRegisterRequest.getUsername())) {
    		user.setUsername(userRegisterRequest.getUsername());
    	} else {
    		user.setUsername(createUniqueUsername(userRepo));
    	}
    	
    	if(!StringUtils.isEmpty(userRegisterRequest.getEmail())) {
    		user.setEmail(userRegisterRequest.getEmail());
    		user.setAccuracy_email(false);
    	}
    	//không được else ở đây
    	if(!StringUtils.isEmpty(userRegisterRequest.getPhone_number())) {
    		user.setPhone_number(userRegisterRequest.getPhone_number());
    		user.setAccuracy_phone_number(userRegisterRequest.isAccuracy_phone_number());
    	}
    	user.setPassword(passwordDB);
    	user.setLock_flag(false);
    	user.setDel_flag(false);
    	if(userRegisterRequest.isAccuracy_phone_number()) {
    		user.setActive_flag(true);
    		user.setPublic_flag(true);
    	}
    	user.setUnique_id(createUniqueIdUser(userRepo));
    	user.setCreate_date(new Date());
    	user.setUpdate_date(new Date());
    	user.setTotal_tour_success(0);
    	user.setTotal_tour_fail(0);
    	user.setWallet_flag(false);
    	userRepo.save(user);
    	
    	Role role = roleRepo.findByRole(EMitapRole.USER.name());
    	UserRole userRole = new UserRole();
    	userRole.setUser_id(user.getId());
    	userRole.setRole_id(role.getId());
    	userRole.setCreate_date(new Date());
    	userRole.setUpdate_date(new Date());
    	userRoleRepo.save(userRole);
		return responseSuccessDefault(null);
	}
	
	private String createUniqueUsername(UserRepo userRepo) {
		String username = Utils.generateUniqueId();
		while (userRepo.checkUniqueUsernameExists(username) == ExistsUniqueId.EXISTS) {
			username = Utils.generateUniqueId();
		}
		return username;
	}
	
	private String createUniqueIdUser(UserRepo userRepo) {
		String uniqueId = Utils.generateUniqueId();
		while (userRepo.checkUniqueIdExists(uniqueId) == ExistsUniqueId.EXISTS) {
			uniqueId = Utils.generateUniqueId();
		}
		return uniqueId;
	}


	public ResponseEntity<ReponseMdl> userInfo(String usernameAuthen) {
		MitapUser user = userRepo.findByUsername(usernameAuthen);
		UserInfoResponse userInfo = new UserInfoResponse(user);
		
		List<Role> arrRole = roleRepo.findByUserId(user.getId());
		List<String> roles = new ArrayList<>();
		for (Role role : arrRole) {
			roles.add(role.getName());
		}
		userInfo.setRoles(roles);
		
		UserDirection userDirect = userDirectionRepo.findOneByUserId(user.getId());
		if(userDirect != null) {
			UserDirectionResponse userDirection = new UserDirectionResponse(userDirect);
			userInfo.setUserDirection(userDirection);
		}
		
		return responseSuccessDefault(userInfo);
	}

	public ResponseEntity<ReponseMdl> updatePassword(UpdatePasswordRequest updatePasswordRequest) {
		MitapUser user = null;
		if(!StringUtils.isEmpty(updatePasswordRequest.getUsername())) {
			user = userRepo.findByUsername(updatePasswordRequest.getUsername());
		} else if(!StringUtils.isEmpty(updatePasswordRequest.getPhoneNumber())) {
			user = userRepo.findByPhoneNumber(updatePasswordRequest.getPhoneNumber());
		} else if(!StringUtils.isEmpty(updatePasswordRequest.getEmail())) {
			user = userRepo.findByEmail(updatePasswordRequest.getEmail());
		}
		if(user == null) {
			return responseError(USER_NOT_EXIST);
		}
		
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    	boolean matchOldPass = bCryptPasswordEncoder.matches(updatePasswordRequest.getOldPassword(), user.getPassword());
    	if(!matchOldPass) {
    		return responseError(OLD_PASSWORD_INCORRECT);
    	}
		
		if(updatePasswordRequest.getNewPassword() == null  || !passwordValidator.validatePassword(updatePasswordRequest.getNewPassword())) { 
			return responseError(PASSWORD_INVALID);
		}
		
		if(updatePasswordRequest.getNewPassword().equals(updatePasswordRequest.getOldPassword())) {
			return responseError(TWO_PASSWORD_SAME);
		}
		
		String passwordDB = bCryptPasswordEncoder.encode(updatePasswordRequest.getNewPassword());
    	user.setPassword(passwordDB);
    	userRepo.save(user);
    	
    	return responseSuccessDefault(null);
	}

	public ResponseEntity<ReponseMdl> updatePasswordByToken(UpdatePasswordByTokenRequest updatePasswordByTokenRequest, String username) {
		MitapUser user = userRepo.findByUsername(username);
		if(user == null) {
			return responseError(USER_NOT_EXIST);
		}
		
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    	boolean matchOldPass = bCryptPasswordEncoder.matches(updatePasswordByTokenRequest.getOldPassword(), user.getPassword());
    	if(!matchOldPass) {
    		return responseError(OLD_PASSWORD_INCORRECT);
    	}
		
		if(updatePasswordByTokenRequest.getNewPassword() == null  || !passwordValidator.validatePassword(updatePasswordByTokenRequest.getNewPassword())) { 
			return responseError(PASSWORD_INVALID);
		}
		
		if(updatePasswordByTokenRequest.getNewPassword().equals(updatePasswordByTokenRequest.getOldPassword())) {
			return responseError(TWO_PASSWORD_SAME);
		}
		
		String passwordDB = bCryptPasswordEncoder.encode(updatePasswordByTokenRequest.getNewPassword());
    	user.setPassword(passwordDB);
    	userRepo.save(user);
    	
    	return responseSuccessDefault(null);
	}

}






























