package com.sidproj.nagpurdrs.constants;

/**
 * Created by sid on 07/08/2016.
 */
public class URLConstants {
    //offic pc tomcat
  //  public static final String BASE_URL = "http://172.16.1.110:8089/pracdoc/";

    //offic pc local
    public static final String BASE_URL = "http://172.16.1.110:8080/";

    //Cloud server
    // public static final String BASE_URL = "http://nagpurdoc.jvmhost.net/pracdoc/";


    public static final String URL_GET_SPECIALIZATIONlIST = BASE_URL + "api/dr/specialization";
    public static final String URL_GET_DR_PROFILE_BY_SPECIALIZATION = BASE_URL + "api/dr/list/";

    //user management
    public static final String URL_POST_USER_SIGNUP = BASE_URL + "api/user/signup";
    public static final String URL_POST_USER_SIGNIN = BASE_URL + "api/user/login";
}
