package com.sateraito.mitap.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "place_travel")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class PlaceTravel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private long id_place;
	private long id_travel;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getId_place() {
		return id_place;
	}
	public void setId_place(long id_place) {
		this.id_place = id_place;
	}
	public long getId_travel() {
		return id_travel;
	}
	public void setId_travel(long id_travel) {
		this.id_travel = id_travel;
	}
	
	
}
