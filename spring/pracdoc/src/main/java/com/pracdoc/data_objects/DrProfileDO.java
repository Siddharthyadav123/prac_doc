package com.pracdoc.data_objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dr_table")
public class DrProfileDO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;

	@Column(name = "dr_full_name")
	private String drFullName;

	@Column(name = "dr_specialization")
	private int drSpecialization;

	@Column(name = "dr_qualification")
	private String drQualification;

	@Column(name = "dr_experience")
	private int drExperience;

	@Column(name = "dr_consulation_fee")
	private int drConsulationFee;

	@Column(name = "dr_contact_num")
	private String drContactNum;

	@Column(name = "dr_clinic_name")
	private String drClinicName;

	@Column(name = "dr_clinic_address")
	private String drClinicAddress;

	@Column(name = "dr_clinic_rating")
	private float drClinicRating;

	@Column(name = "dr_verified_via")
	private String drVerifiedVia;

	@Column(name = "dr_services")
	private String drServices;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDrFullName() {
		return drFullName;
	}

	public void setDrFullName(String drFullName) {
		this.drFullName = drFullName;
	}

	public int getDrSpecialization() {
		return drSpecialization;
	}

	public void setDrSpecialization(int drSpecialization) {
		this.drSpecialization = drSpecialization;
	}

	public String getDrQualification() {
		return drQualification;
	}

	public void setDrQualification(String drQualification) {
		this.drQualification = drQualification;
	}

	public int getDrExperience() {
		return drExperience;
	}

	public void setDrExperience(int drExperience) {
		this.drExperience = drExperience;
	}

	public int getDrConsulationFee() {
		return drConsulationFee;
	}

	public void setDrConsulationFee(int drConsulationFee) {
		this.drConsulationFee = drConsulationFee;
	}

	public String getDrContactNum() {
		return drContactNum;
	}

	public void setDrContactNum(String drContactNum) {
		this.drContactNum = drContactNum;
	}

	public String getDrClinicName() {
		return drClinicName;
	}

	public void setDrClinicName(String drClinicName) {
		this.drClinicName = drClinicName;
	}

	public String getDrClinicAddress() {
		return drClinicAddress;
	}

	public void setDrClinicAddress(String drClinicAddress) {
		this.drClinicAddress = drClinicAddress;
	}

	public float getDrClinicRating() {
		return drClinicRating;
	}

	public void setDrClinicRating(float drClinicRating) {
		this.drClinicRating = drClinicRating;
	}

	public String getDrVerifiedVia() {
		return drVerifiedVia;
	}

	public void setDrVerifiedVia(String drVerifiedVia) {
		this.drVerifiedVia = drVerifiedVia;
	}

	public String getDrServices() {
		return drServices;
	}

	public void setDrServices(String drServices) {
		this.drServices = drServices;
	}

}
