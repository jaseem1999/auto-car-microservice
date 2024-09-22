package com.autocar.user.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_current_location_table")
public class UserCurrentLocationDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long current_location_id;
	
	@Column(length = 150, nullable = false)
	private String location_name;
	
	@Column(name = "longitude", precision = 11, scale = 8)
	private BigDecimal longitude;

	@Column(name = "latitude", precision = 10, scale = 8)
    private BigDecimal latitude;
	
	private LocalDateTime created_at;
	
	private LocalDateTime updated_at;
	
	private Short status;
	
	@OneToOne
	@JoinColumn(name="user")
	private UserDTO user;

	public long getCurrent_location_id() {
		return current_location_id;
	}

	public void setCurrent_location_id(long current_location_id) {
		this.current_location_id = current_location_id;
	}

	public String getLocation_name() {
		return location_name;
	}

	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
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

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "UserCurrentLocationDTO [current_location_id=" + current_location_id + ", location_name=" + location_name
				+ ", longitude=" + longitude + ", latitude=" + latitude + ", created_at=" + created_at + ", updated_at="
				+ updated_at + ", status=" + status + ", user=" + user + "]";
	}
	
	
}
