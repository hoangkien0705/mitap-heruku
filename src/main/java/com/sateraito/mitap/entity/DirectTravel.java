package com.sateraito.mitap.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sateraito.mitap.converter.DeleteFlagConvertor;
import com.sateraito.mitap.converter.StateDirectTravelConvertor;
import com.sateraito.mitap.utils.EDeleleFlag;
import com.sateraito.mitap.utils.EStateDirectTravel;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "direct_travel")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class DirectTravel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String unique_id;
	private long id_user;
	private long id_travel;
	private Date create_date;
	private Date update_date;
	private String message;
	
	@Convert(converter = DeleteFlagConvertor.class)
	private EDeleleFlag del_flag;
	@Convert(converter = StateDirectTravelConvertor.class)
	private EStateDirectTravel state;

}
