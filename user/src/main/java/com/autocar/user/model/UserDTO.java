package com.autocar.user.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_table")
public class UserDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long user_id;
	
	@Column(length = 150, unique = true, nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@Column(length = 150, nullable = false)
	private String firstname;
	
	@Column(length = 150, nullable = true)
	private String lastname;
	
	@Column(nullable = false)
	private long phone;
	
	@Column(nullable =true)
	private String img;
	
	private LocalDateTime created_at;
	private LocalDateTime updated_at;
	private long created_by;
	private long updated_by;
	private short status;
	
	@Column(columnDefinition = "smallint default 0")
	private short accessGmail;
	
	public UserDTO() {
		// TODO Auto-generated constructor stub
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public LocalDateTime getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}

	public long getCreated_by() {
		return created_by;
	}

	public void setCreated_by(long created_by) {
		this.created_by = created_by;
	}

	public long getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(long updated_by) {
		this.updated_by = updated_by;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}
	
	

	public short getAccessGmail() {
		return accessGmail;
	}

	public void setAccessGmail(short accessGmail) {
		this.accessGmail = accessGmail;
	}

	@Override
	public String toString() {
		return "UserDTO [user_id=" + user_id + ", email=" + email + ", password=" + password + ", firstname="
				+ firstname + ", lastname=" + lastname + ", phone=" + phone + ", img=" + img + ", created_at="
				+ created_at + ", updated_at=" + updated_at + ", created_by=" + created_by + ", updated_by="
				+ updated_by + ", status=" + status + "]";
	}
	
	
}
