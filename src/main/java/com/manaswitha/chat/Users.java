package com.manaswitha.chat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity; // Import the Entity class
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.sql.*;
import jakarta.persistence.Id; // Import the Id class
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "users")
public class Users {

    private long id;
    private String first_name;
    private String last_name;
    private String full_name;
    private String user_name;
    private Date created_at;
	private Date updated_at;

	public Users() {
		
	}
	
	public Users(String first_name, String last_name, String full_name, String user_name) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.full_name = full_name;
		this.user_name = user_name;
		this.created_at = new Date();
		this.updated_at = new Date();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "first_name", nullable = false)
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	
	@Column(name = "last_name", nullable = false)
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	
	@Column(name = "full_name", nullable = false)
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	@Column(name = "user_name", nullable = false)
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	@Column(name = "created_at", nullable = false)
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	@Column(name = "updated_at", nullable = false)
	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", full_name=" + full_name + ", user_name=" + user_name + "]";
	}


}
