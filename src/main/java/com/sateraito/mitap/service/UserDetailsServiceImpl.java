package com.sateraito.mitap.service;

import java.security.SecureRandom;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
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

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.sateraito.mitap.constant.Constants;
import com.sateraito.mitap.entity.MitapUser;
import com.sateraito.mitap.entity.Role;
import com.sateraito.mitap.entity.SmsAccuracyPhone;
import com.sateraito.mitap.entity.UserDirection;
import com.sateraito.mitap.entity.UserRole;
import com.sateraito.mitap.model.request.UpdatePasswordByTokenRequest;
import com.sateraito.mitap.model.request.UpdatePasswordRequest;
import com.sateraito.mitap.model.request.UpdateUserInfoRequest;
import com.sateraito.mitap.model.request.UserAuthPhoneRequest;
import com.sateraito.mitap.model.request.UserRegisterRequest;
import com.sateraito.mitap.model.response.ReponseMdl;
import com.sateraito.mitap.model.response.SmsAccuracyPhoneResponse;
import com.sateraito.mitap.model.response.UserDirectionResponse;
import com.sateraito.mitap.model.response.UserInfoResponse;
import com.sateraito.mitap.repo.SmsAccuracyPhoneRepo;
import com.sateraito.mitap.repo.UserRepo;
import com.sateraito.mitap.twilio.TwilioSms;
import com.sateraito.mitap.utils.EMitapRole;
import com.sateraito.mitap.utils.ESMSTypeAccuracyPhone;
import com.sateraito.mitap.utils.ExistsRow;
import com.sateraito.mitap.utils.RandomString;
import com.sateraito.mitap.utils.Utils;
import com.sateraito.mitap.validator.EmailValidator;
import com.sateraito.mitap.validator.PasswordValidator;
import com.sateraito.mitap.validator.PhoneValidator;
import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


@Service
@Transactional
public class UserDetailsServiceImpl extends MitapService implements UserDetailsService {
	private PasswordValidator passwordValidator = new PasswordValidator();
	private EmailValidator emailValidator = new EmailValidator();
	private PhoneValidator phoneValidator = new PhoneValidator();
	
	public ResponseEntity<ReponseMdl> index(String uri) {
		return responseSuccessDefault(uri + " Hoàng Kiên");
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MitapUser appUser;
		if(emailValidator.validateEmail(username)) {//email
			appUser = userRepo.findByEmail(username);
		} else if(phoneValidator.validatePhone(username)) {//phone
			String phone = username;
			appUser = userRepo.findByPhoneNumber(phone);
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
    		if(userRegisterRequest.getUsername().length() < 8) {
    			return responseError(USERNAME_INVALID);
    		}
    		user.setUsername(userRegisterRequest.getUsername());
    	} else {
    		user.setUsername(createUniqueUsername(userRepo));
    	}
    	
    	if(!StringUtils.isEmpty(userRegisterRequest.getEmail())) {
    		user.setEmail(userRegisterRequest.getEmail());
    		user.setAccuracy_email(userRegisterRequest.isAccuracy_email());
    	}
    	//không được else ở đây
    	if(!StringUtils.isEmpty(userRegisterRequest.getPhone_number())) {
    		user.setPhone_number(userRegisterRequest.getPhone_number());
    		user.setAccuracy_phone_number(userRegisterRequest.isAccuracy_phone_number());
    	}
    	user.setPassword(passwordDB);
    	user.setLock_flag(false);
    	user.setDel_flag(false);
    	//xác thực 1 trong 2 (email hoặc phone) thì được active tài khoản
    	if(userRegisterRequest.isAccuracy_phone_number() || userRegisterRequest.isAccuracy_email()) {
    		user.setActive_flag(true);
    	} else {
    		user.setActive_flag(false);
    	}
    	user.setPublic_flag(true);
    	user.setUnique_id(createUniqueIdUser(userRepo));
    	user.setCreate_date(new Date());
    	user.setUpdate_date(new Date());
    	user.setTotal_tour_success(0);
    	user.setTotal_tour_fail(0);
    	user.setWallet_flag(false);
    	userRepo.save(user);
    	
    	Role role = roleRepo.findByRole(EMitapRole.ROLE_USER.name());
    	UserRole userRole = new UserRole();
    	userRole.setUser_id(user.getId());
    	userRole.setRole_id(role.getId());
    	userRole.setCreate_date(new Date());
    	userRole.setUpdate_date(new Date());
    	userRoleRepo.save(userRole);
		return responseSuccessDefault(user);
	}
	
	public ResponseEntity<ReponseMdl> registerNotAuthPhone(UserRegisterRequest userRegisterRequest) {
		MitapUser user;
		try {
			ReponseMdl registerReponseMdl = register(userRegisterRequest).getBody();
			if(registerReponseMdl.getErrorCode() == ReponseMdl.SUCCESS) {
				user = (MitapUser) registerReponseMdl.getData();
			} else {
				return new ResponseEntity<>(registerReponseMdl, HttpStatus.OK);
			}
		} catch (Exception e) {
			return responseError(ERROR_DEFAULT);
		}
		if(StringUtils.isEmpty(user.getPhone_number())) {
			return responseError(PHONE_NUMBER_NOT_EMPTY);
		}
		
		//thực hiện xác thực qua số điện thoại
		Twilio.init(TwilioSms.ACCOUNT_SID, TwilioSms.AUTH_TOKEN);
        PhoneNumber receivePhoneNumber = new PhoneNumber(user.getPhone_number()); //+84969951417
        RandomString tickets = new RandomString(6, new SecureRandom(), RandomString.digits);
		String code = tickets.nextString();
		String bodyMess = String.format(Constants.SYNTAX_SMS_VERIFY_CODE, code);
        Message.creator(receivePhoneNumber, new PhoneNumber(TwilioSms.TWILIO_NUMBER), bodyMess).create();
        //lưu lại database
        String uniqueId = createUniqueIdSmsAccuracy(smsAccuracyPhoneRepo);
        SmsAccuracyPhone smsAcc = new SmsAccuracyPhone();
        smsAcc.setUnique_id(uniqueId);
        smsAcc.setStatus("");
        smsAcc.setError("");
        smsAcc.setBody_message(bodyMess);
        smsAcc.setCode(String.format(Constants.CODE_VERIFY, code));
        smsAcc.setPrice("");
        smsAcc.setTime_auth(null);
        smsAcc.setPrice_unit("");
        smsAcc.setUser_id(user.getId());
        smsAcc.setCreate_date(new Date());
        smsAcc.setUpdate_date(new Date());
        smsAcc.setType(ESMSTypeAccuracyPhone.REGISTER_VERIFY_PHONENUMBER);
        smsAccuracyPhoneRepo.save(smsAcc);
        //callback
		ListenableFuture<ResourceSet<Message>> future = Message.reader().readAsync();
		FutureCallback<ResourceSet<Message>> callback = new FutureCallback<ResourceSet<Message>>() {
            public void onSuccess(ResourceSet<Message> messages) {
                for (Message message : messages) {
                    System.out.println(message.getSid() + " : " + message.getStatus());
                    SmsAccuracyPhone updateSmsAcc = smsAccuracyPhoneRepo.findOneByUniqueId(uniqueId);
                    if(updateSmsAcc == null) return;
                    updateSmsAcc.setStatus(message.getStatus().name());
                    updateSmsAcc.setPrice(message.getPrice());
                    updateSmsAcc.setPrice_unit(message.getPriceUnit().getCurrencyCode());
                    updateSmsAcc.setUpdate_date(new Date());
                    smsAccuracyPhoneRepo.save(updateSmsAcc);
                }
            }

            public void onFailure(Throwable t) {
                System.out.println("Failed to get message status: " + t.getMessage());
                SmsAccuracyPhone updateSmsAcc = smsAccuracyPhoneRepo.findOneByUniqueId(uniqueId);
                if(updateSmsAcc == null) return;
                updateSmsAcc.setError(t.getMessage());
                updateSmsAcc.setUpdate_date(new Date());
                smsAccuracyPhoneRepo.save(updateSmsAcc);
            }
        };
		Futures.addCallback(future, callback, MoreExecutors.directExecutor());
		
		SmsAccuracyPhoneResponse smsAccuracyPhoneResponse = new SmsAccuracyPhoneResponse(smsAcc);
        smsAccuracyPhoneResponse.setBody_message("");
        smsAccuracyPhoneResponse.setCode("");
		return responseSuccessDefault(smsAccuracyPhoneResponse);
	}
	
	public ResponseEntity<ReponseMdl> userAuthPhone(UserAuthPhoneRequest userAuthPhone) {
		MitapUser user = userRepo.findByPhoneNumber(userAuthPhone.getPhone());
		if(user == null) return responseError(USER_NOT_EXIST);
		SmsAccuracyPhone smsAccuracyPhone = smsAccuracyPhoneRepo.findOneByUser(user.getId());
		if(smsAccuracyPhone == null) return responseError(VERIFY_CODE_NOTFOUND);
		if(!smsAccuracyPhone.getCode().equals(userAuthPhone.getCode())) return responseError(VERIFY_CODE_FAIL);
		//check time
		Date curentDate = new Date();
		long diff = curentDate.getTime() - smsAccuracyPhone.getCreate_date().getTime();
		long minute = diff / (60 * 1000);
		if(minute > Constants.TIME_EXPIRED_VERIFY_CODE) return responseError(AUTH_TIME_EXPIRED);
		
		user.setAccuracy_phone_number(true);
		user.setActive_flag(true);
		userRepo.save(user);
		
		smsAccuracyPhone.setTime_auth(curentDate);
		smsAccuracyPhone.setUpdate_date(curentDate);
		smsAccuracyPhoneRepo.save(smsAccuracyPhone);
		
		return responseSuccessDefault(null);
	}
	
	public ResponseEntity<ReponseMdl> forgotPassword(String phoneNumber) {
		if(StringUtils.isEmpty(phoneNumber)) {
			return responseError(PHONE_NUMBER_NOT_EMPTY);
		}
		if(phoneNumber.indexOf("=") == (phoneNumber.length() - 1)) {
			phoneNumber = phoneNumber.substring(0, phoneNumber.length() - 1);
		}
		MitapUser user = userRepo.findByPhoneNumber(phoneNumber);
		if(user == null) {
			return responseError(USER_NOT_EXIST);
		}
		
		Twilio.init(TwilioSms.ACCOUNT_SID, TwilioSms.AUTH_TOKEN);
        PhoneNumber receivePhoneNumber = new PhoneNumber(phoneNumber); //+84969951417
        RandomString tickets = new RandomString(6, new SecureRandom(), RandomString.digits);
		String code = tickets.nextString();
		String bodyMess = String.format(Constants.SYNTAX_SMS_VERIFY_CODE, code);
        Message.creator(receivePhoneNumber, new PhoneNumber(TwilioSms.TWILIO_NUMBER), bodyMess).create();
		
		String uniqueId = createUniqueIdSmsAccuracy(smsAccuracyPhoneRepo);
        SmsAccuracyPhone smsAcc = new SmsAccuracyPhone();
        smsAcc.setUnique_id(uniqueId);
        smsAcc.setStatus("");
        smsAcc.setError("");
        smsAcc.setBody_message(bodyMess);
        smsAcc.setCode(String.format(Constants.CODE_VERIFY, code));
        smsAcc.setPrice("");
        smsAcc.setTime_auth(null);
        smsAcc.setPrice_unit("");
        smsAcc.setUser_id(user.getId());
        smsAcc.setCreate_date(new Date());
        smsAcc.setUpdate_date(new Date());
        smsAcc.setType(ESMSTypeAccuracyPhone.FORGOT_PASSWORD);
        smsAccuracyPhoneRepo.save(smsAcc);
        
        //callback
		ListenableFuture<ResourceSet<Message>> future = Message.reader().readAsync();
		FutureCallback<ResourceSet<Message>> callback = new FutureCallback<ResourceSet<Message>>() {
			public void onSuccess(ResourceSet<Message> messages) {
				for (Message message : messages) {
	                  System.out.println(message.getSid() + " : " + message.getStatus());
	                  SmsAccuracyPhone updateSmsAcc = smsAccuracyPhoneRepo.findOneByUniqueId(uniqueId);
	                  if(updateSmsAcc == null) return;
	                  updateSmsAcc.setStatus(message.getStatus().name());
	                  updateSmsAcc.setPrice(message.getPrice());
	                  updateSmsAcc.setPrice_unit(message.getPriceUnit().getCurrencyCode());
	                  updateSmsAcc.setUpdate_date(new Date());
	                  smsAccuracyPhoneRepo.save(updateSmsAcc);
	              }
	        }
	
	        public void onFailure(Throwable t) {
	        	System.out.println("Failed to get message status: " + t.getMessage());
	        	SmsAccuracyPhone updateSmsAcc = smsAccuracyPhoneRepo.findOneByUniqueId(uniqueId);
	            if(updateSmsAcc == null) return;
	            updateSmsAcc.setError(t.getMessage());
	            updateSmsAcc.setUpdate_date(new Date());
	            smsAccuracyPhoneRepo.save(updateSmsAcc);
	        }
	    };
		Futures.addCallback(future, callback, MoreExecutors.directExecutor());
        SmsAccuracyPhoneResponse smsAccuracyPhoneResponse = new SmsAccuracyPhoneResponse(smsAcc);
        smsAccuracyPhoneResponse.setBody_message("");
        smsAccuracyPhoneResponse.setCode("");
		return responseSuccessDefault(smsAccuracyPhoneResponse);
	}
	
	public ResponseEntity<ReponseMdl> forgotPasswordVerifyCode(UserAuthPhoneRequest userAuthPhone) {
		SmsAccuracyPhone smsAccuracyPhone = smsAccuracyPhoneRepo.findOneByPhoneAndType(ESMSTypeAccuracyPhone.FORGOT_PASSWORD.getType(), userAuthPhone.getPhone());
		if(smsAccuracyPhone == null) {
			return responseError(VERIFY_CODE_NOTFOUND);
		}
		if(!smsAccuracyPhone.getCode().equals(userAuthPhone.getCode())) return responseError(VERIFY_CODE_FAIL);
		//check time
		Date curentDate = new Date();
		long diff = curentDate.getTime() - smsAccuracyPhone.getCreate_date().getTime();
		long minute = diff / (60 * 1000);
		if(minute > Constants.TIME_EXPIRED_VERIFY_CODE) return responseError(AUTH_TIME_EXPIRED);
		
		smsAccuracyPhone.setTime_auth(curentDate);
		smsAccuracyPhone.setUpdate_date(curentDate);
		smsAccuracyPhoneRepo.save(smsAccuracyPhone);
		
		return responseSuccessDefault(null);
	}
	
	private String createUniqueUsername(UserRepo userRepo) {
		String username = Utils.generateUniqueId();
		while (userRepo.checkUniqueUsernameExists(username) == ExistsRow.EXISTS) {
			username = Utils.generateUniqueId();
		}
		return username;
	}
	
	private static String createUniqueIdUser(UserRepo userRepo) {
		String uniqueId = Utils.generateUniqueId();
		while (userRepo.checkUniqueIdExists(uniqueId) == ExistsRow.EXISTS) {
			uniqueId = Utils.generateUniqueId();
		}
		return uniqueId;
	}
	
	private static String createUniqueIdSmsAccuracy(SmsAccuracyPhoneRepo smsAccuracyRepo) {
		String uniqueId = Utils.generateUniqueId();
		while (smsAccuracyRepo.checkUniqueIdExists(uniqueId) == ExistsRow.EXISTS) {
			uniqueId = Utils.generateUniqueId();
		}
		return uniqueId;
	}


	public ResponseEntity<ReponseMdl> userInfo(String usernameAuthen) {
		MitapUser user = userRepo.findByUsername(usernameAuthen);
		if(user == null) {
			return responseError(USER_NOT_EXIST);
		}
		
		return getUserInfo(user);
	}
	
	public ResponseEntity<ReponseMdl> userInfoById(Long idUser) {
		MitapUser user = userRepo.findOne(idUser);
		if(user == null) {
			return responseError(USER_NOT_EXIST);
		}
		
		return getUserInfo(user);
	}
	
	private ResponseEntity<ReponseMdl> getUserInfo(MitapUser user) {
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

	/**
	 * @param updatePasswordRequest : phoneNumber, newPassword
	 * @return
	 */
	public ResponseEntity<ReponseMdl> forgotPasswordMemberUpdatePass(UpdatePasswordRequest updatePasswordRequest) {
		MitapUser user = userRepo.findByPhoneNumber(updatePasswordRequest.getPhoneNumber());
		if(user == null) {
			return responseError(USER_NOT_EXIST);
		}
		if(updatePasswordRequest.getNewPassword() == null  || !passwordValidator.validatePassword(updatePasswordRequest.getNewPassword())) { 
			return responseError(PASSWORD_INVALID);
		}
		
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String passwordDB = bCryptPasswordEncoder.encode(updatePasswordRequest.getNewPassword());
    	user.setPassword(passwordDB);
    	userRepo.save(user);
    	
    	return responseSuccessDefault(null);
	}

	public ResponseEntity<ReponseMdl> updateUserInfo(UpdateUserInfoRequest updateUserInfoRequest, String usernameAuthen) {
		MitapUser user = userRepo.findByUsername(usernameAuthen);
		if(user == null) {
			return responseError(USER_NOT_EXIST);
		}
		if(!updateUserInfoRequest.getEmail().equals(user.getEmail()) && !StringUtils.isEmpty(updateUserInfoRequest.getEmail()) && userRepo.findByEmail(updateUserInfoRequest.getEmail()) != null) {
			return responseError(EMAIL_ALREADY_EXIST);
		}
		if(!emailValidator.validateEmail(updateUserInfoRequest.getEmail())) {
			return responseError(EMAIL_INVALIDATE);
		}
		user.setFirst_name(updateUserInfoRequest.getFirst_name());
		user.setLast_name(updateUserInfoRequest.getLast_name());
		user.setEmail(updateUserInfoRequest.getEmail());
		user.setAddress(updateUserInfoRequest.getAddress());
		user.setNational(updateUserInfoRequest.getNational());
		try {
			Date birday = Constants.fomat.parse(updateUserInfoRequest.getBirday());
			user.setBirday(birday);
			userRepo.save(user);
			return responseSuccessDefault(null);
		} catch (ParseException e) {
			return responseError(BIRDAY_INCORRECT);
		}
	}

}






























