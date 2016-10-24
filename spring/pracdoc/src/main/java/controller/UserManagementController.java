package controller;

import models.UserManagementModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import data_objects.BaseResponseModel;
import data_objects.LoginDO;
import data_objects.UserDetailsDO;

@RestController
@RequestMapping(value = "api/user")
public class UserManagementController extends BaseController {

	@Autowired
	public UserManagementModel userManagementModel;

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
			UserDetailsDO userDetailsDO = userManagementModel.checkLogin(gson
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
		BaseResponseModel baseResponseModel = userManagementModel
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
		BaseResponseModel baseResponseModel = userManagementModel
				.updateUser(gson.fromJson(signUpJson, UserDetailsDO.class));
		return baseResponseModel;
	}

}
