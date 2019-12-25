package com.sateraito.mitap.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sateraito.mitap.constant.Constants;
import com.sateraito.mitap.converter.StateTravelConvertor;
import com.sateraito.mitap.entity.DirectTravel;
import com.sateraito.mitap.entity.MitapUser;
import com.sateraito.mitap.entity.Travel;
import com.sateraito.mitap.entity.UserDirection;
import com.sateraito.mitap.model.request.DirectorSelectTourRequest;
import com.sateraito.mitap.model.request.DirectorTravelRequest;
import com.sateraito.mitap.model.request.RegisterTravelRequest;
import com.sateraito.mitap.model.response.ReponseMdl;
import com.sateraito.mitap.model.response.TravelResponse;
import com.sateraito.mitap.repo.DirectTravelRepo;
import com.sateraito.mitap.repo.TravelRepo;
import com.sateraito.mitap.utils.EDeleleFlag;
import com.sateraito.mitap.utils.EStateDirectTravel;
import com.sateraito.mitap.utils.EStateTravel;
import com.sateraito.mitap.utils.ExistsRow;
import com.sateraito.mitap.utils.Utils;

@Service
@Transactional
public class TravelService extends MitapService {

	public ResponseEntity<ReponseMdl> getWaitingTravel() {
		StateTravelConvertor convertor = new StateTravelConvertor();
		List<Travel> listWaitingTravel = travelRepo.findByStateTravel(convertor.convertToDatabaseColumn(EStateTravel.WAITING_REGISTER));
		List<TravelResponse> waitingTravelResponses = new ArrayList<>();
		for (Travel travel : listWaitingTravel) {
			TravelResponse response = new TravelResponse(travel);
			waitingTravelResponses.add(response);
		}
		return responseSuccessDefault(waitingTravelResponses);
	}

	public ResponseEntity<ReponseMdl> registerTravel(String username, RegisterTravelRequest registerTravelRequest) {
		try {
			Travel travel = new Travel();
			travel.setState(EStateTravel.WAITING_REGISTER);
			travel.setPublic_flag(false);
			travel.setDel_flag(false);
			
			MitapUser u = userRepo.findByUsername(username);
			// TODO kiểm tra xem user này có quyền tạo tour du lịch hay không (chỉ có người đi du lịch mới được tạo, người chỉ đường thì không được tạo)
			UserDirection userDirection = userDirectionRepo.findOneByUserId(u.getId());
			if(userDirection != null) {
				return responseError(USER_DIRECTION_NOT_CREATE_TOUR);
			}
			
			travel.setId_user(u.getId());
			travel.setUnique_id(createUniqueIdTravel(travelRepo));
			
			travel.setJapanese_profile_required(registerTravelRequest.getJapanese_profile_required());
			travel.setList_place(registerTravelRequest.getPlaceToVisit());
			Date timeStart;
			try {
				timeStart = Constants.fomat_2.parse(registerTravelRequest.getTime_start());
				travel.setTime_start(timeStart);
			} catch (ParseException e) {
				travel.setTime_start(null);
			}
			Date timeFinish;
			try {
				timeFinish = Constants.fomat_2.parse(registerTravelRequest.getTime_finish());
				travel.setTime_finish(timeFinish);
			} catch (ParseException e) {
				travel.setTime_finish(null);
			}
			travel.setTransport(registerTravelRequest.getTransport());
			travel.setLocation(registerTravelRequest.getDestination());
			travel.setNote(registerTravelRequest.getNote());
			travel.setSchedule(registerTravelRequest.getSchedule());
			travel.setCreate_date(new Date());
			travel.setUpdate_date(new Date());
			travelRepo.save(travel);
			return responseSuccessDefault(null);
		} catch (Exception e) {
			return responseErrorDefault(e);
		}
	}
	
	private String createUniqueIdTravel(TravelRepo travelRepo) {
		String uniqueId = Utils.generateUniqueId();
		while (travelRepo.checkUniqueIdExists(uniqueId) == ExistsRow.EXISTS) {
			uniqueId = Utils.generateUniqueId();
		}
		return uniqueId;
	}

	public ResponseEntity<ReponseMdl> directorsTravel(DirectorTravelRequest directorTravelRequest) {
		return null;
	}

	public ResponseEntity<ReponseMdl> listTravelWaiting(String username) {
		MitapUser u = userRepo.findByUsername(username);
		if(u == null) {
			return responseError(USER_NOT_EXIST);
		}
		
		StateTravelConvertor convertor = new StateTravelConvertor();
		List<Travel> listTravel = travelRepo.findByStateTravel(convertor.convertToDatabaseColumn(EStateTravel.WAITING_REGISTER).intValue());
		List<TravelResponse> travelResponses = new ArrayList<>();
		for (int i = 0; i < listTravel.size(); i++) {
			TravelResponse response = new TravelResponse(listTravel.get(i));
			//TODO kiểm tra travel này đã được người dùng này đăng ký chưa, nếu đã đăng ký rồi thì bỏ qua
			if(directTravelRepo.checkDirectTravelExists(u.getId(), listTravel.get(i).getId()) == ExistsRow.NO_EXISTS){
				travelResponses.add(response);
			}
		}
		return responseSuccessDefault(travelResponses);
	}

	public ResponseEntity<ReponseMdl> directorSelectTour(String userName, DirectorSelectTourRequest directorTravelRequest) {
		MitapUser u = userRepo.findByUsername(userName);
		if(u == null) {
			return responseError(USER_NOT_EXIST);
		}
		Travel travel = travelRepo.findOne(Long.valueOf(directorTravelRequest.getIdTour()));
		if(travel == null) {
			return responseError(TRAVEL_EXISTS);
		}
		if(directTravelRepo.checkDirectTravelExists(u.getId(), travel.getId()) == ExistsRow.EXISTS){
			return responseError(DIRECT_TRAVEL_EXISTS);
		}
		
		DirectTravel directTravel = new DirectTravel();
		directTravel.setId_user(u.getId());
		directTravel.setId_travel(travel.getId());
		directTravel.setCreate_date(new Date());
		directTravel.setUpdate_date(new Date());
		directTravel.setUnique_id(createUniqueDirectTravel(directTravelRepo));
		directTravel.setDel_flag(EDeleleFlag.NOT_DELETE);
		directTravel.setState(EStateDirectTravel.USER_DIRECT_PERFORM);
		directTravel.setMessage(directorTravelRequest.getMessage());
		directTravelRepo.save(directTravel);
		
		return responseSuccessDefault(null);
	}
	
	private String createUniqueDirectTravel(DirectTravelRepo directTravelRepo) {
		String uniqueDirectTravel = Utils.generateUniqueId();
		while (directTravelRepo.checkUniqueDirectTravelExists(uniqueDirectTravel) == ExistsRow.EXISTS) {
			uniqueDirectTravel = Utils.generateUniqueId();
		}
		return uniqueDirectTravel;
	}

}
