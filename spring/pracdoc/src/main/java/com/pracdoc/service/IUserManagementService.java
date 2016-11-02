package com.pracdoc.service;

import com.pracdoc.data_objects.BaseResponseModel;
import com.pracdoc.data_objects.LoginDO;
import com.pracdoc.data_objects.UserDetailsDO;

public interface IUserManagementService {
	public UserDetailsDO checkLogin(LoginDO login);

	public BaseResponseModel signUpUser(UserDetailsDO userDetailsDO);

	public BaseResponseModel updateUser(UserDetailsDO userDetailsDO);
}
