package com.pracdoc.controller;

import com.pracdoc.do_others.BaseResponseModel;

public class BaseController {

	public BaseResponseModel getResponseModel(Object responseObject,
			boolean isSuccess, String msgText) {
		BaseResponseModel baseResponseModel = new BaseResponseModel();
		baseResponseModel.setResponse(responseObject);
		baseResponseModel.setMsg(msgText);
		baseResponseModel.setSuccess(isSuccess);
		return baseResponseModel;
	}
}
