package com.pracdoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.google.gson.Gson;
import com.pracdoc.data_objects.BaseResponseModel;
import com.pracdoc.data_objects.LoginDO;
import com.pracdoc.data_objects.UserDetailsDO;
import com.pracdoc.service.IUserManagementService;

@RestController
@RequestMapping(value = "api/user")
@EnableWebMvc
public class UserManagementController extends BaseController {

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
			UserDetailsDO userDetailsDO = userManagementService.checkLogin(gson
					.fromJson(loginJson, LoginDO.class));

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
				.signUpUser(gson.fromJson(signUpJson, UserDetailsDO.class));
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
				.updateUser(gson.fromJson(signUpJson, UserDetailsDO.class));
		return baseResponseModel;
	}

}
