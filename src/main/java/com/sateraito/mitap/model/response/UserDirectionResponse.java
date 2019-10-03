package com.sateraito.mitap.model.response;

import com.sateraito.mitap.entity.UserDirection;
import com.sateraito.mitap.utils.Utils;

public class UserDirectionResponse {
	private long id;
	private String unique_id;
	private int points;
	private long id_user;
	private String img_alien_card;
	private String auth_state_alien_card;
	private String auth_date_alien_card;
	private String auth_id_alien_card;
	private String degree_japanese_ability;
	private String auth_state_degree_japanese_ability;
	private String auth_date_degree_japanese_ability;
	private String auth_id_degree_japanese_ability;
	private String time_start;
	private boolean del_flag;
	private String time_living;
	
	public UserDirectionResponse(){
		
	}
	
	public UserDirectionResponse(UserDirection userDirect) {
		setId(userDirect.getId());
		setUnique_id(userDirect.getUnique_id());
		setPoints(userDirect.getPoints());
		setId_user(userDirect.getId_user());
		setImg_alien_card(userDirect.getImg_alien_card());
		setAuth_state_alien_card(userDirect.getAuth_state_alien_card());
		setAuth_date_alien_card(Utils.fomatDateToString(userDirect.getAuth_date_alien_card()));
		setAuth_id_alien_card(userDirect.getAuth_id_alien_card());
		setDegree_japanese_ability(userDirect.getDegree_japanese_ability());
		setAuth_state_degree_japanese_ability(userDirect.getAuth_state_degree_japanese_ability());
		setAuth_date_degree_japanese_ability(Utils.fomatDateToString(userDirect.getAuth_date_degree_japanese_ability()));
		setAuth_id_degree_japanese_ability(userDirect.getAuth_id_degree_japanese_ability());
		setTime_start(Utils.fomatDateToString(userDirect.getTime_start()));
		setDel_flag(userDirect.isDel_flag());
		setTime_living(userDirect.getTime_living());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUnique_id() {
		return unique_id;
	}

	public void setUnique_id(String unique_id) {
		this.unique_id = unique_id;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public long getId_user() {
		return id_user;
	}

	public void setId_user(long id_user) {
		this.id_user = id_user;
	}

	public String getImg_alien_card() {
		return img_alien_card;
	}

	public void setImg_alien_card(String img_alien_card) {
		this.img_alien_card = img_alien_card;
	}

	public String getAuth_state_alien_card() {
		return auth_state_alien_card;
	}

	public void setAuth_state_alien_card(String auth_state_alien_card) {
		this.auth_state_alien_card = auth_state_alien_card;
	}

	public String getAuth_date_alien_card() {
		return auth_date_alien_card;
	}

	public void setAuth_date_alien_card(String auth_date_alien_card) {
		this.auth_date_alien_card = auth_date_alien_card;
	}

	public String getAuth_id_alien_card() {
		return auth_id_alien_card;
	}

	public void setAuth_id_alien_card(String auth_id_alien_card) {
		this.auth_id_alien_card = auth_id_alien_card;
	}

	public String getDegree_japanese_ability() {
		return degree_japanese_ability;
	}

	public void setDegree_japanese_ability(String degree_japanese_ability) {
		this.degree_japanese_ability = degree_japanese_ability;
	}

	public String getAuth_state_degree_japanese_ability() {
		return auth_state_degree_japanese_ability;
	}

	public void setAuth_state_degree_japanese_ability(String auth_state_degree_japanese_ability) {
		this.auth_state_degree_japanese_ability = auth_state_degree_japanese_ability;
	}

	public String getAuth_date_degree_japanese_ability() {
		return auth_date_degree_japanese_ability;
	}

	public void setAuth_date_degree_japanese_ability(String auth_date_degree_japanese_ability) {
		this.auth_date_degree_japanese_ability = auth_date_degree_japanese_ability;
	}

	public String getAuth_id_degree_japanese_ability() {
		return auth_id_degree_japanese_ability;
	}

	public void setAuth_id_degree_japanese_ability(String auth_id_degree_japanese_ability) {
		this.auth_id_degree_japanese_ability = auth_id_degree_japanese_ability;
	}

	public String getTime_start() {
		return time_start;
	}

	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}

	public boolean isDel_flag() {
		return del_flag;
	}

	public void setDel_flag(boolean del_flag) {
		this.del_flag = del_flag;
	}

	public String getTime_living() {
		return time_living;
	}

	public void setTime_living(String time_living) {
		this.time_living = time_living;
	}

}
