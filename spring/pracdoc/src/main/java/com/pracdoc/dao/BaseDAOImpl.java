package com.pracdoc.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.pracdoc.do_others.BaseResponseModel;

public class BaseDAOImpl {

	protected BaseResponseModel getResponseModel(Object responseObject,
			boolean isSuccess, String msgText) {
		BaseResponseModel baseResponseModel = new BaseResponseModel();
		baseResponseModel.setResponse(responseObject);
		baseResponseModel.setMsg(msgText);
		baseResponseModel.setSuccess(isSuccess);
		return baseResponseModel;
	}

	public static String getCurrentTimeStamp() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// dd/MM/yyyy
		Date now = new Date();
		String strDate = sdfDate.format(now);
		return strDate;
	}
}
