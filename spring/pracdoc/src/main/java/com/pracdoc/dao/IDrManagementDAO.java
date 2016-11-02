package com.pracdoc.dao;

import java.util.List;

import com.pracdoc.data_objects.DrProfileDO;
import com.pracdoc.data_objects.DrSpecializationDO;

public interface IDrManagementDAO {
	public List<DrSpecializationDO> getAllDrSpecialiationList();

	public List<DrProfileDO> getDrProfileBySpecializationId(int specializationId);
}
