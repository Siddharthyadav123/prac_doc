package com.pracdoc.dao;

import java.util.List;

import com.pracdoc.do_others.DrTimeResponseDo;
import com.pracdoc.do_table.DrProfileTableDO;
import com.pracdoc.do_table.DrSpecializationTableDO;

public interface IDrManagementDAO {

	// get all the specialization of doctors
	public List<DrSpecializationTableDO> getAllDrSpecialiationList();

	// get doctor detailed profile as per specialization id
	public List<DrProfileTableDO> getDrProfileBySpecializationId(
			int specializationId);

	// get doctor time slots by dr. id
	public DrTimeResponseDo getDrTimeSlotsByItsIds(int[] slotIds);
}
