package com.sateraito.mitap.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "tourist-rating-direction")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class TouristRatingDirection implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String unique_id;
	private long id_tourist;
	private Date create_date;
	private Date update_date;
	private int del_flag;
	private long id_direction;
	private float point;
	private String description;
	private long id_register;

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

	public long getId_tourist() {
		return id_tourist;
	}

	public void setId_tourist(long id_tourist) {
		this.id_tourist = id_tourist;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public int getDel_flag() {
		return del_flag;
	}

	public void setDel_flag(int del_flag) {
		this.del_flag = del_flag;
	}

	public long getId_direction() {
		return id_direction;
	}

	public void setId_direction(long id_direction) {
		this.id_direction = id_direction;
	}

	public float getPoint() {
		return point;
	}

	public void setPoint(float point) {
		this.point = point;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getId_register() {
		return id_register;
	}

	public void setId_register(long id_register) {
		this.id_register = id_register;
	}

}
