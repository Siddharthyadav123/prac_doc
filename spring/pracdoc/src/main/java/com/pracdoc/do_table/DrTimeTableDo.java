package com.pracdoc.do_table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "dr_time_slots")
public class DrTimeTableDo {

	@JsonProperty("id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int id;

	@JsonProperty("slot")
	@Column
	public String slot;

	@JsonProperty("timing")
	@Column
	public String timing;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSlot() {
		return slot;
	}

	public void setSlot(String slot) {
		this.slot = slot;
	}

	public String getTiming() {
		return timing;
	}

	public void setTiming(String timing) {
		this.timing = timing;
	}

}
