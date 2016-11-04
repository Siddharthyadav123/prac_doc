package com.pracdoc.dao;

import java.util.List;

import com.pracdoc.do_others.BaseResponseModel;
import com.pracdoc.do_others.DrTimeResponseDo;
import com.pracdoc.do_request.LoginRequestDO;
import com.pracdoc.do_table.DrAppointmentTableDo;
import com.pracdoc.do_table.DrProfileTableDO;
import com.pracdoc.do_table.DrSpecializationTableDO;

public interface IDrManagementDAO {

	// get all the specialization of doctors
	public List<DrSpecializationTableDO> getAllDrSpecialiationList();

	// get doctor detailed profile as per specialization id
	public List<DrProfileTableDO> getDrProfileBySpecializationId(
			int specializationId);

	// get doctor time slots by dr. id
	public DrTimeResponseDo getDrTimeSlotsByItsIds(int drId, int[] slotsIds);

	public DrProfileTableDO loginDoctor(LoginRequestDO loginRequestDO);

	public List<DrAppointmentTableDo> getAppointmentList(int drId);

	public BaseResponseModel updateAppointment(
			DrAppointmentTableDo drAppointmentTableDo);
}
