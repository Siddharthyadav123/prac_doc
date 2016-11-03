package com.pracdoc.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pracdoc.constants.TableConstants;
import com.pracdoc.do_others.BaseResponseModel;
import com.pracdoc.do_request.LoginRequestDO;
import com.pracdoc.do_table.DrAppointmentTableDo;
import com.pracdoc.do_table.UserDetailsTableDO;

@Repository
public class UserManagementDAOImpl extends BaseDAOImpl implements
		IUserManagementDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public UserDetailsTableDO checkLogin(LoginRequestDO login) {

		UserDetailsTableDO userDetailsDO = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			String qryString = "from " + TableConstants.TABLE_USER + " where "
					+ TableConstants.TABLE_USER_COL_UNAME + "=? and "
					+ TableConstants.TABLE_USER_COL_PWD + "=?";

			Query query = session.createQuery(qryString);

			userDetailsDO = (UserDetailsTableDO) query
					.setString(0, login.getUname())
					.setString(1, login.getPwd()).uniqueResult();

			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}
		return userDetailsDO;
	}

	@Override
	public BaseResponseModel signUpUser(UserDetailsTableDO userDetailsDO) {
		Session session = sessionFactory.openSession();

		Transaction transaction = session.beginTransaction();
		if (findUserByName(userDetailsDO.getUname()) == null) {
			try {
				userDetailsDO.setCreated_on(getCurrentTimeStamp());
				userDetailsDO.setUpdated_on(getCurrentTimeStamp());

				int id = (Integer) session.save(userDetailsDO);
				userDetailsDO.setId(id);
				transaction.commit();
				session.close();
				return getResponseModel(userDetailsDO, true,
						"User Registered Successfully");

			} catch (Exception e) {
				transaction.rollback();
				session.close();
				return getResponseModel(null, false, e.getMessage());
			}
		} else {
			session.close();
			return getResponseModel(null, false,
					"User with this name already Exist.");
		}
	}

	@Override
	public BaseResponseModel updateUser(UserDetailsTableDO userDetailsDO) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		UserDetailsTableDO existingUser = findUserByName(userDetailsDO
				.getUname());

		if (existingUser != null) {
			try {
				userDetailsDO.setUpdated_on(getCurrentTimeStamp());

				if (userDetailsDO.getCreated_on() == null)
					userDetailsDO.setCreated_on(existingUser.getCreated_on());

				if (userDetailsDO.getPwd() == null)
					userDetailsDO.setPwd(existingUser.getPwd());

				session.update(userDetailsDO);
				transaction.commit();
				session.close();

				return getResponseModel(userDetailsDO, true,
						"User updated Successfully");

			} catch (Exception e) {
				transaction.rollback();
				session.close();
				return getResponseModel(null, false, e.getMessage());
			}
		} else {
			session.close();
			return getResponseModel(null, false, "This username doesn't Exist.");
		}
	}

	public UserDetailsTableDO findUserByName(String uname) {
		UserDetailsTableDO userDetailsDO = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			String qryString = "from " + TableConstants.TABLE_USER + " where "
					+ TableConstants.TABLE_USER_COL_UNAME + "=?";

			Query query = session.createQuery(qryString);
			userDetailsDO = (UserDetailsTableDO) query.setString(0, uname)
					.uniqueResult();

			transaction.commit();
			session.close();
		} catch (Exception e) {
			session.close();
		}
		return userDetailsDO;
	}

	@Override
	public BaseResponseModel takeAppointment(
			DrAppointmentTableDo drAppointmentTableDo) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {

			String qryString = "from DrAppointmentTableDo where dr_id=? and date_time=?";
			Query query = session.createQuery(qryString);

			DrAppointmentTableDo alreadyExistingAppointment = (DrAppointmentTableDo) query
					.setInteger(0, drAppointmentTableDo.getDr_id())
					.setString(1, drAppointmentTableDo.getDate_time())
					.uniqueResult();

			if (alreadyExistingAppointment != null) {
				if (drAppointmentTableDo.getUser_id() == alreadyExistingAppointment
						.getUser_id()) {
					session.close();
					return getResponseModel(null, false,
							"You have already taken appointment for this date time !!");
				} else {
					session.close();
					return getResponseModel(null, false,
							"Sorry this timeslot is already booked !!");
				}

			} else {
				int app_id = (Integer) session.save(drAppointmentTableDo);
				drAppointmentTableDo.setId(app_id);
				transaction.commit();
				session.close();
				return getResponseModel(drAppointmentTableDo, true,
						"Appointment taken Successfully");
			}

		} catch (Exception e) {
			transaction.rollback();
			session.close();
			return getResponseModel(null, false, e.getMessage());
		}
	}
}
