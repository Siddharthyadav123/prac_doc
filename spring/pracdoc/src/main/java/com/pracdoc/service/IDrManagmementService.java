package com.pracdoc.service;

import java.util.List;

import com.pracdoc.do_table.DrProfileTableDO;
import com.pracdoc.do_table.DrSpecializationTableDO;

public interface IDrManagmementService {
	public List<DrSpecializationTableDO> getAllDrSpecialiationList();

	public List<DrProfileTableDO> getDrProfileBySpecializationId(int specializationId);

}
