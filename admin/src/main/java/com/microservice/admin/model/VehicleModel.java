package com.microservice.admin.model;


import java.sql.Date;

import java.time.LocalDateTime;



public class VehicleModel {

	private long vehicle_id;
	
	private String img;
	

	private String name;
	

	private String reg_number;
	

	private Date expery_reg_date;
	

	private String engin_number;
	

	private String brand;
	

	private String model;
	

	private int seat_number;
	

	private LocalDateTime created_at;
	
	
	private LocalDateTime updated_at;
	
	
	private long created_by;
	
	
	private long updated_by;
	
	
	private Short status;
	

	private Short approve;
	
	
	private VehicleTypeModel vehicle_type;
	
	
	private DriverModel driver;
	
	public VehicleModel() {
		// TODO Auto-generated constructor stub
	}
	

	public long getVehicle_id() {
		return vehicle_id;
	}

	public void setVehicle_id(long vehicle_id) {
		this.vehicle_id = vehicle_id;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReg_number() {
		return reg_number;
	}

	public void setReg_number(String reg_number) {
		this.reg_number = reg_number;
	}

	public Date getExpery_reg_date() {
		return expery_reg_date;
	}

	public void setExpery_reg_date(Date expery_reg_date) {
		this.expery_reg_date = expery_reg_date;
	}

	public String getEngin_number() {
		return engin_number;
	}

	public void setEngin_number(String engin_number) {
		this.engin_number = engin_number;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getSeat_number() {
		return seat_number;
	}

	public void setSeat_number(int seat_number) {
		this.seat_number = seat_number;
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

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Short getApprove() {
		return approve;
	}

	public void setApprove(Short approve) {
		this.approve = approve;
	}

	public VehicleTypeModel getVehicle_type() {
		return vehicle_type;
	}

	public void setVehicle_type(VehicleTypeModel vehicle_type) {
		this.vehicle_type = vehicle_type;
	}

	public DriverModel getDriver() {
		return driver;
	}

	public void setDriver(DriverModel driver) {
		this.driver = driver;
	}


	@Override
	public String toString() {
		return "VehicleModel [vehicle_id=" + vehicle_id + ", img=" + img + ", name=" + name + ", reg_number="
				+ reg_number + ", expery_reg_date=" + expery_reg_date + ", engin_number=" + engin_number + ", brand="
				+ brand + ", model=" + model + ", seat_number=" + seat_number + ", created_at=" + created_at
				+ ", updated_at=" + updated_at + ", created_by=" + created_by + ", updated_by=" + updated_by
				+ ", status=" + status + ", approve=" + approve + ", vehicle_type=" + vehicle_type + ", driver="
				+ driver + ", getVehicle_id()=" + getVehicle_id() + ", getImg()=" + getImg() + ", getName()="
				+ getName() + ", getReg_number()=" + getReg_number() + ", getExpery_reg_date()=" + getExpery_reg_date()
				+ ", getEngin_number()=" + getEngin_number() + ", getBrand()=" + getBrand() + ", getModel()="
				+ getModel() + ", getSeat_number()=" + getSeat_number() + ", getCreated_at()=" + getCreated_at()
				+ ", getUpdated_at()=" + getUpdated_at() + ", getCreated_by()=" + getCreated_by() + ", getUpdated_by()="
				+ getUpdated_by() + ", getStatus()=" + getStatus() + ", getApprove()=" + getApprove()
				+ ", getVehicle_type()=" + getVehicle_type() + ", getDriver()=" + getDriver() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
	
}

