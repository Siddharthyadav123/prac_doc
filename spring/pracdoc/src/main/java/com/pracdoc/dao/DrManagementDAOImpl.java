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
import com.pracdoc.do_others.DrTimeResponseDo;
import com.pracdoc.do_table.DrProfileTableDO;
import com.pracdoc.do_table.DrSpecializationTableDO;
import com.pracdoc.do_table.DrTimeTableDo;
import com.pracdoc.do_table.UserDetailsTableDO;

@Repository
public class DrManagementDAOImpl extends BaseDAOImpl implements
		IDrManagementDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<DrSpecializationTableDO> getAllDrSpecialiationList() {

		List<DrSpecializationTableDO> drSpecializationDOs = new ArrayList<DrSpecializationTableDO>();
		Session session = sessionFactory.openSession();
		drSpecializationDOs = session.createQuery(
				"From DrSpecializationTableDO").list();
		session.close();
		return drSpecializationDOs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DrProfileTableDO> getDrProfileBySpecializationId(
			int specializationId) {
		Session session = sessionFactory.openSession();
		List<DrProfileTableDO> drProfileDOs = new ArrayList<DrProfileTableDO>();
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

	@Override
	public DrTimeResponseDo getDrTimeSlotsByItsIds(int[] slotsIds) {
		Session session = sessionFactory.openSession();
		List<DrTimeTableDo> drTimeTableDos = new ArrayList<DrTimeTableDo>();
		DrTimeResponseDo drTimeResponseDo = null;
		try {
			String qryString = "from DrTimeTableDo where id=" + slotsIds[0]
					+ " or id=" + slotsIds[1] + " or id=" + slotsIds[2]
					+ " or id=" + slotsIds[3];

			System.out.println(">>time slot query>> " + qryString);
			drTimeTableDos = session.createQuery(qryString).list();
			session.close();

			drTimeResponseDo = new DrTimeResponseDo();
			for (int i = 0; i < drTimeTableDos.size(); i++) {
				String[] timeSlots = null;
				String timeString = drTimeTableDos.get(i).getTiming();
				if (timeString.contains(",")) {
					timeSlots = timeString.split(",");
				}
				if (drTimeTableDos.get(i).getSlot().equals("morning")) {
					drTimeResponseDo.setMorningTimeSlots(timeSlots);
				} else if (drTimeTableDos.get(i).getSlot().equals("afternoon")) {
					drTimeResponseDo.setAfternoonTimeSlots(timeSlots);
				} else if (drTimeTableDos.get(i).getSlot().equals("evening")) {
					drTimeResponseDo.setEveningTimeSlots(timeSlots);
				} else if (drTimeTableDos.get(i).getSlot().equals("night")) {
					drTimeResponseDo.setNightTimeSlots(timeSlots);
				}
			}
		} catch (Exception e) {
			session.close();
			return null;
		}
		return drTimeResponseDo;
	}
}
