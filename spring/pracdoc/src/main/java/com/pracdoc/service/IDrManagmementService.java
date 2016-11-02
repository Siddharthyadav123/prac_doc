package com.pracdoc.service;

import java.util.List;

import com.pracdoc.data_objects.DrProfileDO;
import com.pracdoc.data_objects.DrSpecializationDO;

public interface IDrManagmementService {
	public List<DrSpecializationDO> getAllDrSpecialiationList();

	public List<DrProfileDO> getDrProfileBySpecializationId(int specializationId);

}
