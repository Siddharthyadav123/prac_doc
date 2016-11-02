package com.pracdoc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.google.gson.Gson;
import com.pracdoc.dao.IDrManagementDAO;
import com.pracdoc.data_objects.BaseResponseModel;
import com.pracdoc.data_objects.DrProfileDO;
import com.pracdoc.data_objects.DrSpecializationDO;

@RestController
@RequestMapping(value = "api/dr")
@EnableWebMvc
public class DrControllerForPatient extends BaseController {
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
			List<DrSpecializationDO> drSpecializationDOs = drManagementDAO
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
			List<DrProfileDO> drProfileDOs = drManagementDAO
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
}
