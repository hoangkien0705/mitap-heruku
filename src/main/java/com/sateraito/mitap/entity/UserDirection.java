package com.sateraito.mitap.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sateraito.mitap.converter.BooleanConvertor;

@Entity(name = "user_direction")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class UserDirection implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String unique_id;
	private int points;
	private long id_user;
	private String img_alien_card;
	private String auth_state_alien_card;
	private Date auth_date_alien_card;
	private String auth_id_alien_card;
	private String degree_japanese_ability;
	private String auth_state_degree_japanese_ability;
	private Date auth_date_degree_japanese_ability;
	private String auth_id_degree_japanese_ability;
	private Date time_start;
	@Convert(converter = BooleanConvertor.class)
	private boolean del_flag;
	private String time_living;

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

	public Date getAuth_date_alien_card() {
		return auth_date_alien_card;
	}

	public void setAuth_date_alien_card(Date auth_date_alien_card) {
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

	public Date getAuth_date_degree_japanese_ability() {
		return auth_date_degree_japanese_ability;
	}

	public void setAuth_date_degree_japanese_ability(Date auth_date_degree_japanese_ability) {
		this.auth_date_degree_japanese_ability = auth_date_degree_japanese_ability;
	}

	public String getAuth_id_degree_japanese_ability() {
		return auth_id_degree_japanese_ability;
	}

	public void setAuth_id_degree_japanese_ability(String auth_id_degree_japanese_ability) {
		this.auth_id_degree_japanese_ability = auth_id_degree_japanese_ability;
	}

	public Date getTime_start() {
		return time_start;
	}

	public void setTime_start(Date time_start) {
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
