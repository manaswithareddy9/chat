package com.angelos.chat.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserContactModel {

	private long id;

	@JsonProperty("user_id")
	private long userId;

	@JsonProperty("phone_number")
	private String phoneNumber;

	@JsonProperty("email_id")
	private String emailId;

	@JsonProperty("created_at")
	private Date createdAt;

	@JsonProperty("updated_at")
	private Date updatedAt;

	public UserContactModel() {

	}

	public UserContactModel(long id, long userId, String phoneNumber, String emailId, Date createdAt, Date updatedAt) {
		this.id = id;
		this.userId = userId;
		this.phoneNumber = phoneNumber;
		this.emailId = emailId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "UserContactModel [id=" + id + ", userId=" + userId + ", phoneNumber=" + phoneNumber + ", emailId="
				+ emailId + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

}
