package data_objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginDO {
	@JsonProperty("uname")
	public String uname;

	@JsonProperty("pwd")
	public String pwd;

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}
