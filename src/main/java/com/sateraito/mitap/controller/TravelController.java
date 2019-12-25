package com.sateraito.mitap.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sateraito.mitap.config.Auth;
import com.sateraito.mitap.model.request.DirectorSelectTourRequest;
import com.sateraito.mitap.model.request.DirectorTravelRequest;
import com.sateraito.mitap.model.request.RegisterTravelRequest;
import com.sateraito.mitap.model.response.ReponseMdl;

@Controller
public class TravelController extends MitapController {
	
	/**
	 * lấy ra tất cả những đăng ký du lịch mà đang trong trang thái chờ
	 */
	@RequestMapping(value = "/get_waiting_travel", method = { RequestMethod.POST }) 
	public ResponseEntity<ReponseMdl> getWaitingTravel(HttpServletRequest request) {
		return travelService.getWaitingTravel();
	}
	
	/**
	 * Thực hiện đăng ký du lịch với người du lịch
	 */
	@RequestMapping(value = "/register_travel", method = { RequestMethod.POST }) 
	public ResponseEntity<ReponseMdl> registerTravel(HttpServletRequest request, @RequestBody RegisterTravelRequest registerTravelRequest) {
		String usernameAuthen = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return travelService.registerTravel(usernameAuthen, registerTravelRequest);
	}
	
	/**
	 * Lấy ra những người chỉ đường đã nhận travel này (tất cả các trạng thái)
	 */
	@RequestMapping(value = "/directors_travel", method = { RequestMethod.POST }) 
	public ResponseEntity<ReponseMdl> directorsTravel(@RequestBody DirectorTravelRequest directorTravelRequest) {
		return travelService.directorsTravel(directorTravelRequest);
	}
	
	/**
	 * Lấy ra tất cả danh sách các travel của người du lịch (thường thì chỉ có 1)
	 */
	
	/**
	 * lấy ra những travel đã nhận từ người du lịch của người chỉ đường 
	 */
	
	/**
	 * Lây ra những travel đang chờ xác nhận từ người du lịch của người chỉ đường
	 */
	
	/**
	 * Lấy ra những travel đã bị hủy từ người du lịch hoặc từ chính người chỉ đường của người chỉ đường
	 */
	
	/**
	 * Lấy ra các travel đã được người du lịch tạo, chờ được liên kết
	 */
	@Auth(role = {Auth.Role.ROLE_USER_DIRECTOR})
	@RequestMapping(value = "/list_travel_waiting", method = { RequestMethod.GET } ) 
	public ResponseEntity<ReponseMdl> listTravelWaiting(HttpServletRequest request) {
		String usernameAuthen = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return travelService.listTravelWaiting(usernameAuthen);
	}
	
	/**
	 * người chỉ đường đăng ký chọn tour du lịch để hướng dẫn
	 */
	@Auth(role = {Auth.Role.ROLE_USER_DIRECTOR})
	@RequestMapping(value = "/director_select_tour", method = { RequestMethod.POST } ) 
	public ResponseEntity<ReponseMdl> directorSelectTour(HttpServletRequest request, @RequestBody DirectorSelectTourRequest directorTravelRequest) {
		String usernameAuthen = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return travelService.directorSelectTour(usernameAuthen, directorTravelRequest);
	}
}
















