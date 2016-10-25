package constants;

public interface TableConstants {
	/**
	 * USER TABLE Constants
	 */
	public static String TABLE_USER = "user_table";

	public static String TABLE_USER_COL_UNAME = "uname";
	public static String TABLE_USER_COL_PWD = "pwd";
	public static String TABLE_USER_COL_FULL_NAME = "full_name";
	public static String TABLE_USER_COL_ADDRESS = "address";
	public static String TABLE_USER_COL_MOBILE_NO = "mobile_no";
	public static String TABLE_USER_COL_CREATED_ON = "created_on";

	public static String[] TABLE_USER_PROJECTIONS = { TABLE_USER_COL_UNAME,
			TABLE_USER_COL_PWD, TABLE_USER_COL_FULL_NAME,
			TABLE_USER_COL_ADDRESS, TABLE_USER_COL_MOBILE_NO,
			TABLE_USER_COL_CREATED_ON };

	/**
	 * table for getting doctors specialization list
	 */
	public static String TABLE_DR_SPECIALIZATION = "dr_specialization";
	public static String TABLE_DR_SPECIALIZATION_COL_UNAME = "name";

	/**
	 * table for getting doctors list
	 */
	public static String TABLE_DR = "dr_table";
	public static String TABLE_USER_COL_DR_NAME = "dr_uname";
	public static String TABLE_USER_COL_DR_PWD = "dr_pwd";
	public static String TABLE_USER_COL_DR_FULL_NAME = "dr_full_name";
	public static String TABLE_USER_COL_DR_SPECIALIATION = "dr_specialization";
	public static String TABLE_USER_COL_DR_QUALIFICATION = "dr_qualification";
	public static String TABLE_USER_COL_DR_EXPERIENCE = "dr_experience";
	public static String TABLE_USER_COL_DR_CONSULATION_FEE = "dr_consulation_fee";
	public static String TABLE_USER_COL_DR_CONTACT_NUM = "dr_contact_num";
	public static String TABLE_USER_COL_DR_CLINIC_NAME = "dr_clinic_name";
	public static String TABLE_USER_COL_DR_CLINIC_ADDRESS = "dr_clinic_address";
	public static String TABLE_USER_COL_DR_CLINIC_RATING = "dr_clinic_rating";
	public static String TABLE_USER_COL_DR_VERIFIED_VIA = "dr_verified_via";
	public static String TABLE_USER_COL_DR_SERVICES = "dr_services";

}
