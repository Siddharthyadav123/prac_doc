package com.pracdoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pracdoc.dao.IDrManagementDAO;
import com.pracdoc.do_table.DrProfileTableDO;
import com.pracdoc.do_table.DrSpecializationTableDO;

@Service
public class DrManagementServiceImpl implements IDrManagmementService {

	@Autowired
	private IDrManagementDAO drManagementDAO;

	@Override
	public List<DrSpecializationTableDO> getAllDrSpecialiationList() {
		return drManagementDAO.getAllDrSpecialiationList();
	}

	@Override
	public List<DrProfileTableDO> getDrProfileBySpecializationId(int specializationId) {
		return drManagementDAO.getDrProfileBySpecializationId(specializationId);
	}

}
