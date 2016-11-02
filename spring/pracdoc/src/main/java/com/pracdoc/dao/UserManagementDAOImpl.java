package com.pracdoc.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pracdoc.constants.TableConstants;
import com.pracdoc.data_objects.BaseResponseModel;
import com.pracdoc.data_objects.LoginDO;
import com.pracdoc.data_objects.UserDetailsDO;

@Repository
public class UserManagementDAOImpl extends BaseDAOImpl implements
		IUserManagementDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public UserDetailsDO checkLogin(LoginDO login) {

		UserDetailsDO userDetailsDO = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			String qryString = "from " + TableConstants.TABLE_USER + " where "
					+ TableConstants.TABLE_USER_COL_UNAME + "=? and "
					+ TableConstants.TABLE_USER_COL_PWD + "=?";

			Query query = session.createQuery(qryString);

			userDetailsDO = (UserDetailsDO) query
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
	public BaseResponseModel signUpUser(UserDetailsDO userDetailsDO) {
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
			return getResponseModel(null, false,
					"User with this name already Exist.");
		}
	}

	@Override
	public BaseResponseModel updateUser(UserDetailsDO userDetailsDO) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		UserDetailsDO existingUser = findUserByName(userDetailsDO.getUname());

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
			return getResponseModel(null, false, "This username doesn't Exist.");
		}
	}

	public UserDetailsDO findUserByName(String uname) {
		UserDetailsDO userDetailsDO = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			String qryString = "from " + TableConstants.TABLE_USER + " where "
					+ TableConstants.TABLE_USER_COL_UNAME + "=?";
			
			
			Query query = session.createQuery(qryString);
			userDetailsDO = (UserDetailsDO) query.setString(0, uname)
					.uniqueResult();

			transaction.commit();
			session.close();
		} catch (Exception e) {
			session.close();
		}
		return userDetailsDO;
	}

}
