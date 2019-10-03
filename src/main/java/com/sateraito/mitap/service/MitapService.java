package com.sateraito.mitap.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sateraito.mitap.model.response.ReponseMdl;
import com.sateraito.mitap.repo.DealHistoryRepo;
import com.sateraito.mitap.repo.DirectTravelRepo;
import com.sateraito.mitap.repo.PlaceRepo;
import com.sateraito.mitap.repo.PlaceTravelRepo;
import com.sateraito.mitap.repo.PriceServiceRepo;
import com.sateraito.mitap.repo.RoleRepo;
import com.sateraito.mitap.repo.TouristRatingDirectionRepo;
import com.sateraito.mitap.repo.TravelRepo;
import com.sateraito.mitap.repo.UserDirectionRepo;
import com.sateraito.mitap.repo.UserRepo;
import com.sateraito.mitap.repo.UserRoleRepo;


public class MitapService {
	@Autowired
	protected UserRepo userRepo;
	@Autowired
	protected UserRoleRepo userRoleRepo;
	@Autowired
	protected RoleRepo roleRepo;
	@Autowired
	protected DealHistoryRepo dealHistoryRepo;
	@Autowired
	protected DirectTravelRepo directTravelRepo;
	@Autowired
	protected PlaceRepo placeRepo;
	@Autowired
	protected PlaceTravelRepo placeTravelRepo;
	@Autowired
	protected PriceServiceRepo priceServiceRepo;
	@Autowired
	protected TouristRatingDirectionRepo touristRatingDirectionRepo;
	@Autowired
	protected TravelRepo travelRepo;
	@Autowired
	protected UserDirectionRepo userDirectionRepo;
	
	public static final int ERROR_DEFAULT = 1;
	public static final int USERNAME_ALREADY_EXIST = 3;
	public static final int EMAIL_ALREADY_EXIST = 4;
	public static final int PHONE_NUMBER_ALREADY_EXIST = 5;
	public static final int PASSWORD_INVALID = 6;
	public static final int ACCOUNT_LOCK = 7;
	public static final int ACCOUNT_DISABLE = 8;
	public static final int USER_NOT_EXIST = 9;
	public static final int TWO_PASSWORD_SAME = 10;
	public static final int OLD_PASSWORD_INCORRECT = 11;
	
	public static Map<Integer, String> mapErrorReponse = new HashMap<>();
	
	public static void inserErrorCode() {
		mapErrorReponse.put(ERROR_DEFAULT, "An error has occurred");
		mapErrorReponse.put(USERNAME_ALREADY_EXIST, "This username already exists");
		mapErrorReponse.put(EMAIL_ALREADY_EXIST, "This email already exists");
		mapErrorReponse.put(PHONE_NUMBER_ALREADY_EXIST, "This phone already exists");
		mapErrorReponse.put(PASSWORD_INVALID, "Invalid password");
		mapErrorReponse.put(ACCOUNT_LOCK, "User account is locked");
		mapErrorReponse.put(ACCOUNT_DISABLE, "User account is disable");
		mapErrorReponse.put(USER_NOT_EXIST, "User not exist");
		mapErrorReponse.put(TWO_PASSWORD_SAME, "These 2 passwords are the same");
		mapErrorReponse.put(OLD_PASSWORD_INCORRECT, "Old password is incorrect");
	}
	
	public static ResponseEntity<ReponseMdl> responseErrorDefault(Exception e) {
		ReponseMdl reponseError = ReponseMdl.getInsErrorDefault();
		reponseError.setMessage(e == null ? "" : e.toString());
		return new ResponseEntity<>(reponseError, HttpStatus.OK);
	}

	public static ResponseEntity<ReponseMdl> responseError(int errorCode) {
		ReponseMdl reponseError = new ReponseMdl();
		reponseError.setErrorCode(errorCode);
		reponseError.setMessage(mapErrorReponse.get(errorCode));
		return new ResponseEntity<>(reponseError, HttpStatus.OK);
	}

	public static ResponseEntity<ReponseMdl> responseSuccessDefault(Object data) {
		ReponseMdl reponseSuccess = ReponseMdl.getInsSuccess();
		reponseSuccess.setData(data);
		return new ResponseEntity<>(reponseSuccess, HttpStatus.OK);
	}
}
