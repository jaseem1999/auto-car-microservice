package com.autocar.user.model;

import java.time.LocalDateTime;

public class VehicleTypeModel {
	 private Integer vehicleTypeId;

	    
	    private String typeOfVehicle;

	   
	    private LocalDateTime createdAt;

	  
	    private LocalDateTime updatedAt;

	   
	    private Long createdBy;

	    
	    private Long updatedBy;

	    private Short status;

		public Integer getVehicleTypeId() {
			return vehicleTypeId;
		}

		public void setVehicleTypeId(Integer vehicleTypeId) {
			this.vehicleTypeId = vehicleTypeId;
		}

		public String getTypeOfVehicle() {
			return typeOfVehicle;
		}

		public void setTypeOfVehicle(String typeOfVehicle) {
			this.typeOfVehicle = typeOfVehicle;
		}

		public LocalDateTime getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}

		public LocalDateTime getUpdatedAt() {
			return updatedAt;
		}

		public void setUpdatedAt(LocalDateTime updatedAt) {
			this.updatedAt = updatedAt;
		}

		public Long getCreatedBy() {
			return createdBy;
		}

		public void setCreatedBy(Long createdBy) {
			this.createdBy = createdBy;
		}

		public Long getUpdatedBy() {
			return updatedBy;
		}

		public void setUpdatedBy(Long updatedBy) {
			this.updatedBy = updatedBy;
		}

		public Short getStatus() {
			return status;
		}

		public void setStatus(Short status) {
			this.status = status;
		}
}
