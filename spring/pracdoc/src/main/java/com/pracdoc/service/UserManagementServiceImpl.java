package com.pracdoc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pracdoc.dao.IUserManagementDAO;
import com.pracdoc.data_objects.BaseResponseModel;
import com.pracdoc.data_objects.LoginDO;
import com.pracdoc.data_objects.UserDetailsDO;

@Service
public class UserManagementServiceImpl implements IUserManagementService {

	@Autowired
	private IUserManagementDAO userManagementDAO;

	@Override
	public UserDetailsDO checkLogin(LoginDO login) {
		return userManagementDAO.checkLogin(login);
	}

	@Override
	public BaseResponseModel signUpUser(UserDetailsDO userDetailsDO) {
		return userManagementDAO.signUpUser(userDetailsDO);
	}

	@Override
	public BaseResponseModel updateUser(UserDetailsDO userDetailsDO) {
		return userManagementDAO.updateUser(userDetailsDO);
	}

}
