package com.pracdoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pracdoc.dao.IUserManagementDAO;
import com.pracdoc.do_others.BaseResponseModel;
import com.pracdoc.do_request.LoginRequestDO;
import com.pracdoc.do_table.DrAppointmentTableDo;
import com.pracdoc.do_table.UserDetailsTableDO;

@Service
public class UserManagementServiceImpl implements IUserManagementService {

	@Autowired
	private IUserManagementDAO userManagementDAO;

	@Override
	public UserDetailsTableDO checkLogin(LoginRequestDO login) {
		return userManagementDAO.checkLogin(login);
	}

	@Override
	public BaseResponseModel signUpUser(UserDetailsTableDO userDetailsDO) {
		return userManagementDAO.signUpUser(userDetailsDO);
	}

	@Override
	public BaseResponseModel updateUser(UserDetailsTableDO userDetailsDO) {
		return userManagementDAO.updateUser(userDetailsDO);
	}

	@Override
	public BaseResponseModel takeAppointment(
			DrAppointmentTableDo drAppointmentTableDo) {
		return userManagementDAO.takeAppointment(drAppointmentTableDo);
	}

	@Override
	public BaseResponseModel updateAppointment(
			DrAppointmentTableDo drAppointmentTableDo) {
		return userManagementDAO.updateAppointment(drAppointmentTableDo);
	}

	@Override
	public List<DrAppointmentTableDo> getAppointmentList(int userId) {
		return userManagementDAO.getAppointmentList(userId);
	}

}
