package com.sidproj.nagpurdrs.constants;

/**
 * Created by sid on 07/08/2016.
 */
public class URLConstants {
    //home pc tomcat
//    public static final String BASE_URL = "http://192.168.1.102:8080/";

    //offic pc local
    public static final String BASE_URL = "http://172.16.1.110:8080/";


    //Cloud server
    // public static final String BASE_URL = "http://nagpurdoc.jvmhost.net/pracdoc/";

    //user management
    public static final String URL_POST_USER_SIGNUP = BASE_URL + "api/user/signup";
    public static final String URL_POST_USER_SIGNIN = BASE_URL + "api/user/login";

    public static final String URL_GET_SPECIALIZATIONlIST = BASE_URL + "api/dr/specialization";
    public static final String URL_GET_DR_PROFILE_BY_SPECIALIZATION = BASE_URL + "api/dr/list/";
    public static final String URL_GET_DR_TIMESLOTS = BASE_URL + "api/dr/";
    public static final String URL_POST_TAKE_APPOINTMENT = BASE_URL + "api/user/appointment/take";

    public static final String URL_GET_APPOINTMENT_LIST = BASE_URL + "api/user/{user_id}/appointments";

    public static final String URL_POST_DR_SIGNIN = BASE_URL + "api/dr/login";

    public static final String URL_GET_DR_APPOINTMENT_LIST = BASE_URL + "api/dr/{dr_id}/appointments";

    public static final String URL_PUT_DR_APPOINTMENT_UPDATE = BASE_URL + "api/dr/appointment/update";


}
