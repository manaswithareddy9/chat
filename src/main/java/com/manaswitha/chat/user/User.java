package com.manaswitha.chat.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity; // Import the Entity class
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id; // Import the Id class
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "first_name", nullable = false)
	@JsonProperty("first_name")
	private String firstName;

	@Column(name = "last_name", nullable = false)
	@JsonProperty("last_name")
	private String lastName;

	@Column(name = "full_name", nullable = false)
	@JsonProperty("full_name")
	private String fullName;

	@Column(name = "user_name", nullable = false, unique = true)
	@JsonProperty("user_name")
	private String userName;

	@Column(name = "created_at", nullable = false)
	private Date createdAt;

	@Column(name = "updated_at", nullable = false)
	private Date updatedAt;

	public User() {
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}

	public User(String firstName, String lastName, String fullName, String userName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.fullName = fullName;
		this.userName = userName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
		return "Users [id=" + id + ", first_name=" + firstName + ", last_name=" + lastName + ", full_name="
				+ fullName + ", user_name=" + userName + "]";
	}

}
