package com.autocar.user.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "user_address")
public class AddressDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long address_id;
	
	@Column(name = "address_type", nullable = false, length = 100)
	private String address_type;
	
	@Column(name = "location_name", nullable = false, length = 255)
	private String location_name;
	   
	@Column(name = "longitude", precision = 11, scale = 8)
	private BigDecimal longitude;

	@Column(name = "latitude", precision = 10, scale = 8)
    private BigDecimal latitude;
	
	
	private LocalDateTime created_at;
	
	private LocalDateTime updated_at;
	
	private long updated_by;
	
	private long created_by;
	
	private short status;
	
	@ManyToOne
	@JoinColumn(name="user")
	private UserDTO user;
	
	public AddressDTO() {
		// TODO Auto-generated constructor stub
	}

	public long getAddress_id() {
		return address_id;
	}

	public void setAddress_id(long address_id) {
		this.address_id = address_id;
	}

	public String getAddress_type() {
		return address_type;
	}

	public void setAddress_type(String address_type) {
		this.address_type = address_type;
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

	public long getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(long updated_by) {
		this.updated_by = updated_by;
	}

	public long getCreated_by() {
		return created_by;
	}

	public void setCreated_by(long created_by) {
		this.created_by = created_by;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
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
		return "AddressDTO [address_id=" + address_id + ", address_type=" + address_type + ", location_name="
				+ location_name + ", longitude=" + longitude + ", latitude=" + latitude + ", created_at=" + created_at
				+ ", updated_at=" + updated_at + ", updated_by=" + updated_by + ", created_by=" + created_by
				+ ", status=" + status + ", user=" + user + "]";
	}
}
