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
import com.pracdoc.do_others.BaseResponseModel;
import com.pracdoc.do_others.DrTimeResponseDo;
import com.pracdoc.do_request.DrTimeRequestDo;
import com.pracdoc.do_request.LoginRequestDO;
import com.pracdoc.do_table.DrAppointmentTableDo;
import com.pracdoc.do_table.UserDetailsTableDO;
import com.pracdoc.service.IUserManagementService;

@RestController
@RequestMapping(value = "api/user")
@EnableWebMvc
public class UserController extends BaseController {

	@Autowired
	private IUserManagementService userManagementService;

	/**
	 * API to perform LOGIN
	 * 
	 * @param loginJson
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponseModel checkLogin(@RequestBody String loginJson) {
		try {
			Gson gson = new Gson();
			UserDetailsTableDO userDetailsDO = userManagementService
					.checkLogin(gson.fromJson(loginJson, LoginRequestDO.class));

			if (userDetailsDO == null) {
				return getResponseModel(null, false, "User not Found !!");
			} else {
				return getResponseModel(userDetailsDO, true,
						"Loging successful.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, false, e.getMessage());
		}
	}

	/**
	 * API to perform SIGNUP
	 * 
	 * @param signUpJson
	 * @return
	 */
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponseModel signUpUser(@RequestBody String signUpJson) {
		Gson gson = new Gson();
		BaseResponseModel baseResponseModel = userManagementService
				.signUpUser(gson.fromJson(signUpJson, UserDetailsTableDO.class));
		return baseResponseModel;
	}

	/**
	 * API to perform SIGNUP
	 * 
	 * @param signUpJson
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseBody
	public BaseResponseModel updateUser(@RequestBody String signUpJson) {
		Gson gson = new Gson();
		BaseResponseModel baseResponseModel = userManagementService
				.updateUser(gson.fromJson(signUpJson, UserDetailsTableDO.class));
		return baseResponseModel;
	}

	/**
	 * Get Appointment list of self being user/patient
	 * 
	 * @param userId
	 * @param appointmentDetail
	 * @return
	 */
	@RequestMapping(value = "/{user_id}/appointments", method = RequestMethod.GET)
	@ResponseBody
	public BaseResponseModel getAppointmentList(
			@PathVariable(value = "user_id") int userId) {
		try {
			List<DrAppointmentTableDo> appointments = userManagementService
					.getAppointmentList(userId);
			if (appointments != null && appointments.size() > 0) {
				return getResponseModel(appointments, true, "Appointment List.");
			} else {
				return getResponseModel(null, false,
						"No Appointment found for you.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, false, e.getMessage());
		}
	}

	@RequestMapping(value = "/appointment/take", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponseModel takeAppointment(
			@RequestBody String appointmentDetail) {
		try {
			Gson gson = new Gson();
			DrAppointmentTableDo drAppointmentTableDo = gson.fromJson(
					appointmentDetail, DrAppointmentTableDo.class);
			return userManagementService.takeAppointment(drAppointmentTableDo);

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
			return userManagementService
					.updateAppointment(drAppointmentTableDo);

		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, false, e.getMessage());
		}
	}

}
