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
import com.pracdoc.do_others.BaseResponseModel;
import com.pracdoc.do_others.DrTimeResponseDo;
import com.pracdoc.do_request.LoginRequestDO;
import com.pracdoc.do_table.DrAppointmentTableDo;
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
	public DrTimeResponseDo getDrTimeSlotsByItsIds(int drId, int[] slotsIds) {
		Session session = sessionFactory.openSession();
		List<DrTimeTableDo> drTimeTableDos = new ArrayList<DrTimeTableDo>();
		DrTimeResponseDo drTimeResponseDo = null;
		try {
			String qryString = "from DrTimeTableDo where id=" + slotsIds[0]
					+ " or id=" + slotsIds[1] + " or id=" + slotsIds[2]
					+ " or id=" + slotsIds[3];

			System.out.println(">>time slot query>> " + qryString);
			drTimeTableDos = session.createQuery(qryString).list();

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

			// status=2 means Appointment approved.
			String query = "from DrAppointmentTableDo where dr_id=" + drId
					+ " and status=2";

			List<DrAppointmentTableDo> busyAppointments = session.createQuery(
					query).list();

			if (busyAppointments != null && busyAppointments.size() > 0) {
				String[] busySlotsArray = new String[busyAppointments.size()];
				for (int i = 0; i < busyAppointments.size(); i++) {
					busySlotsArray[i] = busyAppointments.get(i).getDate_time();
				}
				drTimeResponseDo.setBusySlopts(busySlotsArray);
			}
			session.close();

		} catch (Exception e) {
			session.close();
			return drTimeResponseDo;
		}
		return drTimeResponseDo;
	}

	// ------------------------DOCTOR APIS----------------------------//
	@Override
	public DrProfileTableDO loginDoctor(LoginRequestDO loginRequestDO) {
		DrProfileTableDO drProfileTableDO = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			String qryString = "from DrProfileTableDO where dr_uname=? and dr_pwd=?";

			Query query = session.createQuery(qryString);

			drProfileTableDO = (DrProfileTableDO) query
					.setString(0, loginRequestDO.getUname())
					.setString(1, loginRequestDO.getPwd()).uniqueResult();

			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}
		return drProfileTableDO;
	}

	@Override
	public List<DrAppointmentTableDo> getAppointmentList(int drId) {
		Session session = sessionFactory.openSession();
		List<DrAppointmentTableDo> appointments = new ArrayList<DrAppointmentTableDo>();
		try {

			String qryString = "from DrAppointmentTableDo where dr_id=" + drId;
			appointments = session.createQuery(qryString).list();
			session.close();
		} catch (Exception e) {
			session.close();
			return null;
		}
		return appointments;
	}

	@Override
	public BaseResponseModel updateAppointment(
			DrAppointmentTableDo drAppointmentTableDo) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {

			String qryString = "from DrAppointmentTableDo where id=?";
			Query query = session.createQuery(qryString);

			DrAppointmentTableDo alreadyExistingAppointment = (DrAppointmentTableDo) query
					.setInteger(0, drAppointmentTableDo.getId()).uniqueResult();
			transaction.commit();
			session.close();

			if (alreadyExistingAppointment != null) {
				session = sessionFactory.openSession();
				transaction = session.beginTransaction();
				session.update(drAppointmentTableDo);
				transaction.commit();
				session.close();

				// 3 means cancelled...
				if (drAppointmentTableDo.getStatus() == 2) {
					return getResponseModel(drAppointmentTableDo, true,
							"Appointment Approved Successfully !!");
				} else if (drAppointmentTableDo.getStatus() == 3) {
					return getResponseModel(drAppointmentTableDo, true,
							"Appointment Cancelled Successfully !!");
				} else {
					return getResponseModel(drAppointmentTableDo, true,
							"Appointment Completed Successfully !!");
				}
			} else {
				session.close();
				return getResponseModel(null, false, "Appointment not Found !!");
			}

		} catch (Exception e) {
			transaction.rollback();
			session.close();
			return getResponseModel(null, false, e.getMessage());
		}
	}
}
