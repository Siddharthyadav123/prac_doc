package com.pracdoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pracdoc.dao.IDrManagementDAO;
import com.pracdoc.data_objects.DrProfileDO;
import com.pracdoc.data_objects.DrSpecializationDO;

@Service
public class DrManagementServiceImpl implements IDrManagmementService {

	@Autowired
	private IDrManagementDAO drManagementDAO;

	@Override
	public List<DrSpecializationDO> getAllDrSpecialiationList() {
		return drManagementDAO.getAllDrSpecialiationList();
	}

	@Override
	public List<DrProfileDO> getDrProfileBySpecializationId(int specializationId) {
		return drManagementDAO.getDrProfileBySpecializationId(specializationId);
	}

}
