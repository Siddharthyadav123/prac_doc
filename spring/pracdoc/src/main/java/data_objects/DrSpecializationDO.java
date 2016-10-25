package data_objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DrSpecializationDO {

	@JsonProperty("id")
	public int id;

	@JsonProperty("name")
	public String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
