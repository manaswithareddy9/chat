package com.angelos.chat.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "chats")
public class Chat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "from_user_contact_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private UserContact fromUserContact;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "to_user_contact_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private UserContact toUserContact;

	@Column(name = "text", nullable = false)
	@JsonProperty("text")
	private String text;

	@Column(name = "created_at", nullable = false)
	private Date createdAt;

	@Column(name = "updated_at", nullable = false)
	private Date updatedAt;

	public Chat() {
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}

	public Chat(UserContact fromUserContact, UserContact toUserContact, String text) {
		this.fromUserContact = fromUserContact;
		this.toUserContact = toUserContact;
		this.text = text;
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserContact getFromUserContact() {
		return fromUserContact;
	}

	public void setFromUserContact(UserContact fromUserContact) {
		this.fromUserContact = fromUserContact;
	}

	public UserContact getToUserContact() {
		return toUserContact;
	}

	public void setToUserContact(UserContact toUserContact) {
		this.toUserContact = toUserContact;
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
		return "Chat [fromUserContact=" + fromUserContact + ", toUserContact=" + toUserContact + ", text=" + text + "]";
	}

}
