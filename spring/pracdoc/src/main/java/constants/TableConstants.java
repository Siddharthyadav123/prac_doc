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
	public static String TABLE_USER_COL_CREATED_ON = "created_on";

	public static String[] TABLE_USER_PROJECTIONS = { TABLE_USER_COL_UNAME,
			TABLE_USER_COL_PWD, TABLE_USER_COL_FULL_NAME,
			TABLE_USER_COL_ADDRESS, TABLE_USER_COL_CREATED_ON };

}
