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
import com.sateraito.mitap.converter.SexConvertor;
import com.sateraito.mitap.utils.ESex;

@Entity(name = "user")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class MitapUser implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String unique_id;
	private String password;
	private String full_name;
	private String username;
	private String email;
	private int age;
	private String phone_number;
	private String address;
	private String avata;
	private Date create_date;
	private String article_intro;
	private String first_name;
	private String last_name;
	private Date update_date;
	private String national;
	private int total_tour_success;
	private int total_tour_fail;
	private String current_job;
	private Date visa_expired;
	private String passport_code;
	private String id_card;
	
	@Convert(converter = SexConvertor.class)
	private ESex sex;
	@Convert(converter = BooleanConvertor.class)
	private boolean wallet_flag;
	@Convert(converter = BooleanConvertor.class)
	private boolean lock_flag;
	@Convert(converter = BooleanConvertor.class)
	private boolean del_flag;
	@Convert(converter = BooleanConvertor.class)
	private boolean active_flag;
	@Convert(converter = BooleanConvertor.class)
	private boolean public_flag;
	@Convert(converter = BooleanConvertor.class)
	private boolean accuracy_email;
	@Convert(converter = BooleanConvertor.class)
	private boolean accuracy_phone_number;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public ESex getSex() {
		return sex;
	}

	public void setSex(ESex sex) {
		this.sex = sex;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAvata() {
		return avata;
	}

	public void setAvata(String avata) {
		this.avata = avata;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getArticle_intro() {
		return article_intro;
	}

	public void setArticle_intro(String article_intro) {
		this.article_intro = article_intro;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public String getNational() {
		return national;
	}

	public void setNational(String national) {
		this.national = national;
	}

	public int getTotal_tour_success() {
		return total_tour_success;
	}

	public void setTotal_tour_success(int total_tour_success) {
		this.total_tour_success = total_tour_success;
	}

	public int getTotal_tour_fail() {
		return total_tour_fail;
	}

	public void setTotal_tour_fail(int total_tour_fail) {
		this.total_tour_fail = total_tour_fail;
	}

	public String getCurrent_job() {
		return current_job;
	}

	public void setCurrent_job(String current_job) {
		this.current_job = current_job;
	}

	public Date getVisa_expired() {
		return visa_expired;
	}

	public void setVisa_expired(Date visa_expired) {
		this.visa_expired = visa_expired;
	}

	public String getPassport_code() {
		return passport_code;
	}

	public void setPassport_code(String passport_code) {
		this.passport_code = passport_code;
	}

	public String getId_card() {
		return id_card;
	}

	public void setId_card(String id_card) {
		this.id_card = id_card;
	}

	public boolean isWallet_flag() {
		return wallet_flag;
	}

	public void setWallet_flag(boolean wallet_flag) {
		this.wallet_flag = wallet_flag;
	}

	public boolean isLock_flag() {
		return lock_flag;
	}

	public void setLock_flag(boolean lock_flag) {
		this.lock_flag = lock_flag;
	}

	public boolean isDel_flag() {
		return del_flag;
	}

	public void setDel_flag(boolean del_flag) {
		this.del_flag = del_flag;
	}

	public boolean isActive_flag() {
		return active_flag;
	}

	public void setActive_flag(boolean active_flag) {
		this.active_flag = active_flag;
	}

	public boolean isPublic_flag() {
		return public_flag;
	}

	public void setPublic_flag(boolean public_flag) {
		this.public_flag = public_flag;
	}

	public boolean isAccuracy_email() {
		return accuracy_email;
	}

	public void setAccuracy_email(boolean accuracy_email) {
		this.accuracy_email = accuracy_email;
	}

	public boolean isAccuracy_phone_number() {
		return accuracy_phone_number;
	}

	public void setAccuracy_phone_number(boolean accuracy_phone_number) {
		this.accuracy_phone_number = accuracy_phone_number;
	}

}
