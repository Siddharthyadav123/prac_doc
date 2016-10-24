package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import constants.TableConstants;
import data_objects.BaseResponseModel;
import data_objects.LoginDO;
import data_objects.UserDetailsDO;

public class UserManagementModel extends BaseModel {

	@Autowired
	JdbcTemplate jdbcTemplate;

	/**
	 * Method to check the user name and pwd
	 */
	public UserDetailsDO checkLogin(LoginDO login) {

		String query = "select * from " + TableConstants.TABLE_USER + " where "
				+ TableConstants.TABLE_USER_COL_UNAME + "='" + login.getUname()
				+ "' and " + TableConstants.TABLE_USER_COL_PWD + "='"
				+ login.getPwd() + "'";

		System.out.println(">>sid Login url>> " + query);

		List<UserDetailsDO> userList = jdbcTemplate.query(query,
				new RowMapper<UserDetailsDO>() {
					public UserDetailsDO mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						UserDetailsDO userDetailsDO = new UserDetailsDO();
						userDetailsDO.setId(rs.getInt("id"));
						userDetailsDO.setUname(rs.getString("uname"));
						// userDetailsDO.setPwd(rs.getString("pwd"));
						userDetailsDO.setFull_name(rs.getString("full_name"));
						userDetailsDO.setAddress(rs.getString("address"));
						return userDetailsDO;
					}

				});

		// returning if user found
		if (userList != null && userList.size() > 0) {
			return userList.get(0);
		} else {
			return null;
		}

	}

	/**
	 * Method to check the user name and pwd
	 */
	public BaseResponseModel signUpUser(UserDetailsDO userDetailsDO) {

		// checking if user already exist or not
		if (checkIfUserNameAlreadyExist(userDetailsDO.getUname())) {
			return getResponseModel(null, false,
					"User with this name already Exist.");
		}

		String query = formInsertQuery(TableConstants.TABLE_USER,
				TableConstants.TABLE_USER_PROJECTIONS);
		System.out.println(">>sid signup url>> " + query);

		try {
			int rowId = jdbcTemplate
					.update(query,
							new Object[] { userDetailsDO.getUname(),
									userDetailsDO.getPwd(),
									userDetailsDO.getFull_name(),
									userDetailsDO.getAddress(),
									getCurrentTimeStamp() });

			userDetailsDO.setId(rowId);
		} catch (Exception e) {
			e.printStackTrace();
			return getResponseModel(null, false, e.getMessage());
		}

		return getResponseModel(userDetailsDO, true,
				"User Registered Successfully");

	}

	/**
	 * Method to update existing user
	 */
	public BaseResponseModel updateUser(UserDetailsDO userDetailsDO) {
		// checking if user already exist or not
		if (checkIfUserNameAlreadyExist(userDetailsDO.getUname())) {

			String[] TABLE_USER_PROJECTIONS = {
					TableConstants.TABLE_USER_COL_PWD,
					TableConstants.TABLE_USER_COL_FULL_NAME,
					TableConstants.TABLE_USER_COL_ADDRESS };

			String query = formUpdateQueryWithAnd(TableConstants.TABLE_USER,
					TABLE_USER_PROJECTIONS,
					new String[] { TableConstants.TABLE_USER_COL_UNAME },
					new String[] { userDetailsDO.getUname() });

			System.out.println(">>sid update user url>> " + query);

			try {
				int rowId = jdbcTemplate.update(query, new Object[] {
						userDetailsDO.getPwd(), userDetailsDO.getFull_name(),
						userDetailsDO.getAddress() });

				userDetailsDO.setId(rowId);
			} catch (Exception e) {
				e.printStackTrace();
				return getResponseModel(null, false, e.getMessage());
			}

			return getResponseModel(userDetailsDO, true,
					"User updated Successfully");

		} else {
			return getResponseModel(null, false, "User not found.");
		}

	}

	/**
	 * This method will just check the result count for the number of user
	 * 
	 * @param uname
	 * @return
	 */
	private boolean checkIfUserNameAlreadyExist(String uname) {
		String query = "select * from " + TableConstants.TABLE_USER + " where "
				+ TableConstants.TABLE_USER_COL_UNAME + "='" + uname + "'";

		System.out.println(">>sid Login url>> " + query);

		List<UserDetailsDO> userList = jdbcTemplate.query(query,
				new RowMapper<UserDetailsDO>() {
					public UserDetailsDO mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						UserDetailsDO userDetailsDO = new UserDetailsDO();
						userDetailsDO.setId(rs.getInt("id"));
						// userDetailsDO.setUname(rs.getString("uname"));
						// // userDetailsDO.setPwd(rs.getString("pwd"));
						// userDetailsDO.setFull_name(rs.getString("full_name"));
						// userDetailsDO.setAddress(rs.getString("address"));
						return userDetailsDO;
					}

				});

		// returning if user found
		if (userList != null && userList.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
}
