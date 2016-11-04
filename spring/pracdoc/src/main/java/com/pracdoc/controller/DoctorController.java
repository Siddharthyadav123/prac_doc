package com.pracdoc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.google.gson.Gson;
import com.pracdoc.dao.IDrManagementDAO;
import com.pracdoc.do_others.BaseResponseModel;
import com.pracdoc.do_others.DrTimeResponseDo;
import com.pracdoc.do_request.DrTimeRequestDo;
import com.pracdoc.do_request.LoginRequestDO;
import com.pracdoc.do_table.DrAppointmentTableDo;
import com.pracdoc.do_table.DrProfileTableDO;
import com.pracdoc.do_table.DrSpecializationTableDO;
import com.pracdoc.do_table.UserDetailsTableDO;

@RestController
@RequestMapping(value = "api/dr")
@EnableWebMvc
public class DoctorController extends BaseController {
	@Autowired
	public IDrManagementDAO drManagementDAO;

	/**
	 * API to get Dr specialization list
	 * 
	 */
	@RequestMapping(value = "/specialization", method = RequestMethod.GET)
	@ResponseBody
	public BaseResponseModel getAllDrSpecialiationList() {
		try {
			List<DrSpecializationTableDO> drSpecializationDOs = drManagementDAO
					.getAllDrSpecialiationList();

			if (drSpecializationDOs == null || drSpecializationDOs.size() == 0) {
				return getResponseModel(null, false,
						"No Specialization Found !!");
			} else {
				return getResponseModel(drSpecializationDOs, true, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, false, e.getMessage());
		}
	}

	/**
	 * API to get Dr specialization list
	 * 
	 */
	@RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
	@ResponseBody
	public BaseResponseModel getDrProfileBySpecializationId(
			@PathVariable(value = "id") int specializationId) {
		try {
			List<DrProfileTableDO> drProfileDOs = drManagementDAO
					.getDrProfileBySpecializationId(specializationId);

			if (drProfileDOs == null || drProfileDOs.size() == 0) {
				return getResponseModel(null, false,
						"No Doctor Found in records !!");
			} else {
				return getResponseModel(drProfileDOs, true, null);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, false, e.getMessage());
		}
	}

	@RequestMapping(value = "/{dr_id}/time_slots", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponseModel checkLogin(
			@PathVariable(value = "dr_id") int drId,
			@RequestBody String timeSlotString) {
		try {
			Gson gson = new Gson();
			DrTimeRequestDo drTimeRequestDo = gson.fromJson(timeSlotString,
					DrTimeRequestDo.class);

			int[] slotsIds = new int[4];
			slotsIds[0] = drTimeRequestDo.getDr_morning_time_slot_id();
			slotsIds[1] = drTimeRequestDo.getDr_afternoon_time_slot_id();
			slotsIds[2] = drTimeRequestDo.getDr_evening_time_slot_id();
			slotsIds[3] = drTimeRequestDo.getDr_night_time_slot_id();

			DrTimeResponseDo drTimeResponseDo = drManagementDAO
					.getDrTimeSlotsByItsIds(drId, slotsIds);

			if (drTimeResponseDo == null) {
				return getResponseModel(null, false, "Time slots not Found !!");
			} else {
				return getResponseModel(drTimeResponseDo, true, "Time slots.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, false, e.getMessage());
		}
	}

	// ------------------------DOCTOR APIS----------------------------//
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponseModel loginDoctor(@RequestBody String loginJson) {
		try {
			Gson gson = new Gson();
			DrProfileTableDO drProfileTableDO = drManagementDAO
					.loginDoctor(gson.fromJson(loginJson, LoginRequestDO.class));

			if (drProfileTableDO == null) {
				return getResponseModel(null, false, "Doctor not Found !!");
			} else {
				return getResponseModel(drProfileTableDO, true,
						"Loging successful.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, false, e.getMessage());
		}
	}

	@RequestMapping(value = "/{dr_id}/appointments", method = RequestMethod.GET)
	@ResponseBody
	public BaseResponseModel getAppointmentList(
			@PathVariable(value = "dr_id") int drId) {
		try {
			List<DrAppointmentTableDo> appointments = drManagementDAO
					.getAppointmentList(drId);
			if (appointments != null && appointments.size() > 0) {
				return getResponseModel(appointments, true, "Appointment List.");
			} else {
				return getResponseModel(null, false,
						"No one booked Appointment yet.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, false, e.getMessage());
		}
	}

	@RequestMapping(value = "/appointment/update", method = RequestMethod.PUT)
	@ResponseBody
	public BaseResponseModel updateAppointment(
			@RequestBody String appointmentDetail) {
		try {
			Gson gson = new Gson();
			DrAppointmentTableDo drAppointmentTableDo = gson.fromJson(
					appointmentDetail, DrAppointmentTableDo.class);
			return drManagementDAO.updateAppointment(drAppointmentTableDo);

		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, false, e.getMessage());
		}
	}
}
