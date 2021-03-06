package com.pracdoc.do_others;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This will be the common response format
 * 
 * Its not necessary to implement this class with everything. just extend this
 * if required with the particular object
 * 
 * @author siddharth
 * 
 */
public class BaseResponseModel {
	@JsonProperty("success")
	public boolean success = true;

	@JsonProperty("msg")
	public String msg = null;

	@JsonProperty("response")
	public Object response = null;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

}
