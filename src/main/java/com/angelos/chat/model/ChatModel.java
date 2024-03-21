package com.angelos.chat.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChatModel {

	private long id;

	@JsonProperty("from_user_contact_id")
	private long fromUserContactId;

	@JsonProperty("to_user_contact_id")
	private long toUserContactId;

	@JsonProperty("text")
	private String text;

	@JsonProperty("created_at")
	private Date createdAt;

	@JsonProperty("updated_at")
	private Date updatedAt;

	public ChatModel() {

	}

	public ChatModel(long id, long fromUserContactId, long toUserContactId, String text, Date createdAt,
			Date updatedAt) {
		this.id = id;
		this.fromUserContactId = fromUserContactId;
		this.toUserContactId = toUserContactId;
		this.text = text;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getFromUserContactId() {
		return fromUserContactId;
	}

	public void setFromUserContactId(long fromUserContactId) {
		this.fromUserContactId = fromUserContactId;
	}

	public long getToUserContactId() {
		return toUserContactId;
	}

	public void setToUserContactId(long toUserContactId) {
		this.toUserContactId = toUserContactId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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
		return "ChatModel [id=" + id + ", fromUserContactId=" + fromUserContactId + ", toUserContactId="
				+ toUserContactId + ", text=" + text + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

}
