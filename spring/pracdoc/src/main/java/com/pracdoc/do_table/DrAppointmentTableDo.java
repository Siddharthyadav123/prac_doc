package com.pracdoc.do_table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dr_appointment_table")
public class DrAppointmentTableDo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	public int id;

	@Column(name = "patient_id")
	public int patient_id;

	@Column(name = "patient_name")
	public String patient_name;

	@Column(name = "dr_id")
	public int dr_id;

	@Column(name = "dr_name")
	public String dr_name;

	@Column(name = "date_time")
	public String date_time;

	@Column(name = "status")
	public int status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDr_id() {
		return dr_id;
	}

	public void setDr_id(int dr_id) {
		this.dr_id = dr_id;
	}

	public String getDate_time() {
		return date_time;
	}

	public void setDate_time(String date_time) {
		this.date_time = date_time;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}

	public String getPatient_name() {
		return patient_name;
	}

	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}

	public String getDr_name() {
		return dr_name;
	}

	public void setDr_name(String dr_name) {
		this.dr_name = dr_name;
	}

}
