package com.pracdoc.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.pracdoc.constants.TableConstants;
import com.pracdoc.data_objects.DrProfileDO;
import com.pracdoc.data_objects.DrSpecializationDO;

public class DrManagementModelForPatient extends BaseModel {
	@Autowired
	JdbcTemplate jdbcTemplate;

	/**
	 * Method to get all the specialization list of drs.
	 */
	public List<DrSpecializationDO> getAllDrSpecialiationList() {

		String query = "select * from "
				+ TableConstants.TABLE_DR_SPECIALIZATION;

		List<DrSpecializationDO> drSpecializationDOs = jdbcTemplate.query(
				query, new RowMapper<DrSpecializationDO>() {
					public DrSpecializationDO mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						DrSpecializationDO drSpecializationDO = new DrSpecializationDO();
						drSpecializationDO.setId(rs.getInt("id"));
						drSpecializationDO.setName(rs.getString("name"));
						return drSpecializationDO;
					}

				});

		return drSpecializationDOs;

	}

	/**
	 * Method to get the list of Drs. belonging to specialization
	 * 
	 * */
	public List<DrProfileDO> getDrProfileBySpecializationId(int specializationId) {

		String query = "select * from " + TableConstants.TABLE_DR + " where "
				+ TableConstants.TABLE_USER_COL_DR_SPECIALIATION + "="
				+ specializationId;

		List<DrProfileDO> drProfileDOs = jdbcTemplate.query(query,
				new RowMapper<DrProfileDO>() {
					public DrProfileDO mapRow(ResultSet rs, int rowNum)
							throws SQLException {

						DrProfileDO drProfileDO = new DrProfileDO();

						drProfileDO.setId(rs.getInt("id"));

						drProfileDO.setDrFullName(rs
								.getString(TableConstants.TABLE_USER_COL_DR_FULL_NAME));

						drProfileDO.setDrSpecialization(rs
								.getInt(TableConstants.TABLE_USER_COL_DR_SPECIALIATION));

						drProfileDO.setDrQualification(rs
								.getString(TableConstants.TABLE_USER_COL_DR_QUALIFICATION));

						drProfileDO.setDrExperience(rs
								.getInt(TableConstants.TABLE_USER_COL_DR_EXPERIENCE));

						drProfileDO.setDrConsulationFee(rs
								.getInt(TableConstants.TABLE_USER_COL_DR_CONSULATION_FEE));

						drProfileDO.setDrContactNum(rs
								.getString(TableConstants.TABLE_USER_COL_DR_CONTACT_NUM));

						drProfileDO.setDrClinicName(rs
								.getString(TableConstants.TABLE_USER_COL_DR_CLINIC_NAME));

						drProfileDO.setDrClinicAddress(rs
								.getString(TableConstants.TABLE_USER_COL_DR_CLINIC_ADDRESS));

						drProfileDO.setDrClinicRating(rs
								.getFloat(TableConstants.TABLE_USER_COL_DR_CLINIC_RATING));

						drProfileDO.setDrVerifiedVia(rs
								.getString(TableConstants.TABLE_USER_COL_DR_VERIFIED_VIA));

						String services = rs.getString(
								TableConstants.TABLE_USER_COL_DR_SERVICES)
								.trim();

						if (services != null && services.contains(",")) {

							String[] servicesArray = rs
									.getString(
											TableConstants.TABLE_USER_COL_DR_SERVICES)
									.trim().split(",");
							for (int i = 0; i < servicesArray.length; i++) {
								servicesArray[i] = servicesArray[i].trim();
							}
							drProfileDO.setDrServices(servicesArray);

						} else {
							drProfileDO
									.setDrServices(new String[] { services });
						}

						return drProfileDO;
					}

				});

		return drProfileDOs;

	}
}
