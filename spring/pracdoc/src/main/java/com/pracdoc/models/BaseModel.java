package com.pracdoc.models;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.pracdoc.data_objects.BaseResponseModel;

public class BaseModel {

	/**
	 * This is a common method to form the insert query with certain projections
	 * which are required only
	 * 
	 * @param tableName
	 * @param projections
	 * @return
	 */
	protected String formInsertQuery(String tableName, String[] projections) {
		String query = "insert into " + tableName;

		String columnsWithComma = "";
		String questionMarksWithComma = "";

		for (int i = 0; i < projections.length; i++) {
			if (i + 1 == projections.length) {
				columnsWithComma += projections[i];
				questionMarksWithComma += "?";
			} else {
				columnsWithComma += projections[i] + ",";
				questionMarksWithComma += "?,";
			}
		}

		String columns = "(" + columnsWithComma + ")";
		String questionMarks = "(" + questionMarksWithComma + ")";

		return query + columns + " values" + questionMarks;
	}

	/*
	 * Method to form update query with AND conditions if multiple conditions
	 * projections is there
	 */
	protected String formUpdateQueryWithAnd(String tableName,
			String[] updateProjections, String[] conditionProjections,
			String[] conditions) {

		String query = "update " + tableName + " set ";

		String columnsNeedToUpdate = "";

		for (int i = 0; i < updateProjections.length; i++) {
			if (i + 1 == updateProjections.length) {
				columnsNeedToUpdate += updateProjections[i] + "=?";
			} else {
				columnsNeedToUpdate += updateProjections[i] + "=?,";
			}
		}

		String conditionToUpdate = "";

		for (int i = 0; i < conditionProjections.length; i++) {
			if (i + 1 == conditionProjections.length) {
				conditionToUpdate += conditionProjections[i] + "=" + "'"
						+ conditions[i] + "'";
			} else {
				conditionToUpdate += updateProjections[i] + "=" + "'"
						+ conditions[i] + "'" + " and ";
			}
		}
		return query + columnsNeedToUpdate + " where " + conditionToUpdate;
	}

	/*
	 * Method to form update query with OR conditions if multiple conditions
	 * projections is there
	 */
	protected String formUpdateQueryWithOR(String tableName,
			String[] updateProjections, String[] conditionProjections,
			String[] conditions) {

		String query = "update " + tableName + " set ";

		String columnsNeedToUpdate = "";

		for (int i = 0; i < updateProjections.length; i++) {
			if (i + 1 == updateProjections.length) {
				columnsNeedToUpdate += updateProjections[i] + "=?";
			} else {
				columnsNeedToUpdate += updateProjections[i] + "=?,";
			}
		}

		String conditionToUpdate = "";

		for (int i = 0; i < conditionProjections.length; i++) {
			if (i + 1 == conditionProjections.length) {
				conditionToUpdate += conditionProjections[i] + "=" + "'"
						+ conditions[i] + "'";
			} else {
				conditionToUpdate += updateProjections[i] + "=" + "'"
						+ conditions[i] + "'" + " or ";
			}
		}
		return query + columnsNeedToUpdate + " where " + conditionToUpdate;
	}

	protected BaseResponseModel getResponseModel(Object responseObject,
			boolean isSuccess, String msgText) {
		BaseResponseModel baseResponseModel = new BaseResponseModel();
		baseResponseModel.setResponse(responseObject);
		baseResponseModel.setMsg(msgText);
		baseResponseModel.setSuccess(isSuccess);
		return baseResponseModel;
	}

	public static String getCurrentTimeStamp() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// dd/MM/yyyy
		Date now = new Date();
		String strDate = sdfDate.format(now);
		return strDate;
	}
}
