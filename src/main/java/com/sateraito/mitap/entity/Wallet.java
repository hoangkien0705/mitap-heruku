package com.sateraito.mitap.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "wallet")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Wallet implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

//	private String unique_id;
//	private long user_id;
	//tạm thời chưa làm vội
}
