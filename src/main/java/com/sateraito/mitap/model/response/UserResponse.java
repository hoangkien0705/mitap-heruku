package com.sateraito.mitap.model.response;

import com.sateraito.mitap.entity.MitapUser;
import com.sateraito.mitap.utils.Utils;

public class UserResponse {
	private long id;
	private String unique_id;
	private String full_name;
	private String username;
	private String email;
	private int age;
	private String sex;
	private String phone_number;
	private String address;
	private String avata;
	private String create_date;
	private String article_intro;
	private String first_name;
	private String last_name;
	private String update_date;
	private String national;
	private int total_tour_success;
	private int total_tour_fail;
	private String current_job;
	private String visa_expired;
	private String passport_code;
	private String id_card;
	private boolean wallet_flag;
	private boolean lock_flag;
	private boolean del_flag;
	private boolean active_flag;
	private boolean public_flag;
	private boolean accuracy_email;
	private boolean accuracy_phone_number;
	private String birday;
	
	public UserResponse(MitapUser user) {
		setId(user.getId());
		setUnique_id(user.getUnique_id());
		setUsername(user.getUsername());
		setEmail(user.getEmail());
		setAge(user.getAge());
		setSex(user.getSex().getValue());
		setPhone_number(user.getPhone_number());
		setAddress(user.getAddress());
		setAvata(user.getAvata());
		setCreate_date(Utils.fomatDateToString(user.getCreate_date()));
		setArticle_intro(user.getArticle_intro());
		setFirst_name(user.getFirst_name());
		setLast_name(user.getLast_name());
		setUpdate_date(Utils.fomatDateToString(user.getUpdate_date()));
		setNational(user.getNational());
		setTotal_tour_success(user.getTotal_tour_success());
		setTotal_tour_fail(user.getTotal_tour_fail());
		setCurrent_job(user.getCurrent_job());
		setVisa_expired(Utils.fomatDateToString(user.getVisa_expired()));
		setPassport_code(user.getPassport_code());
		setId_card(user.getId_card());
		setWallet_flag(user.isWallet_flag());
		setLock_flag(user.isLock_flag());
		setDel_flag(user.isDel_flag());
		setActive_flag(user.isActive_flag());
		setPublic_flag(user.isPublic_flag());
		setAccuracy_email(user.isAccuracy_email());
		setAccuracy_phone_number(user.isAccuracy_phone_number());
		setBirday(Utils.fomatDateToString(user.getBirday()));
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
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

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
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

	public String getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(String update_date) {
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

	public String getVisa_expired() {
		return visa_expired;
	}

	public void setVisa_expired(String visa_expired) {
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

	public String getBirday() {
		return birday;
	}

	public void setBirday(String birday) {
		this.birday = birday;
	}

	
}
