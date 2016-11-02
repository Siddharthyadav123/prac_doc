package com.pracdoc.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pracdoc.constants.TableConstants;
import com.pracdoc.data_objects.DrProfileDO;
import com.pracdoc.data_objects.DrSpecializationDO;
import com.pracdoc.data_objects.UserDetailsDO;

@Repository
public class DrManagementDAOImpl extends BaseDAOImpl implements
		IDrManagementDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<DrSpecializationDO> getAllDrSpecialiationList() {

		List<DrSpecializationDO> drSpecializationDOs = new ArrayList<DrSpecializationDO>();
		Session session = sessionFactory.openSession();
		drSpecializationDOs = session.createQuery("From DrSpecializationDO")
				.list();
		session.close();
		return drSpecializationDOs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DrProfileDO> getDrProfileBySpecializationId(int specializationId) {
		Session session = sessionFactory.openSession();
		List<DrProfileDO> drProfileDOs = new ArrayList<DrProfileDO>();
		try {

			String qryString = "from " + TableConstants.TABLE_DR + " where "
					+ TableConstants.TABLE_USER_COL_DR_SPECIALIATION + "="
					+ specializationId;
			drProfileDOs = session.createQuery(qryString).list();
			session.close();
		} catch (Exception e) {
			session.close();
			return null;
		}
		return drProfileDOs;
	}

}
