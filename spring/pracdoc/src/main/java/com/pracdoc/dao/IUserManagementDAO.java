package com.pracdoc.dao;

import com.pracdoc.do_others.BaseResponseModel;
import com.pracdoc.do_request.LoginRequestDO;
import com.pracdoc.do_table.DrAppointmentTableDo;
import com.pracdoc.do_table.UserDetailsTableDO;

public interface IUserManagementDAO {
	public UserDetailsTableDO checkLogin(LoginRequestDO login);

	public BaseResponseModel signUpUser(UserDetailsTableDO userDetailsDO);

	public BaseResponseModel updateUser(UserDetailsTableDO userDetailsDO);

	public BaseResponseModel takeAppointment(
			DrAppointmentTableDo drAppointmentTableDo);

	public BaseResponseModel updateAppointment(
			DrAppointmentTableDo drAppointmentTableDo);
}
