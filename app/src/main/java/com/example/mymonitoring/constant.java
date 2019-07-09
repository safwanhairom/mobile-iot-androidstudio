package com.example.mymonitoring;

public class constant {

   // public static final String localhost = "192.168.1.2";10.59.35.72, 172.20.10.3
    private static final String ROOT_URL = "http://10.59.37.82/android/v1/"; // home:192.168.1.6 //salol:192.168.0.126 // miit:10.59.34.82 5gh:10.59.37.82
    public static final String URL_REGISTER = ROOT_URL+"registerUser.php";
    public static final String ROOT_URLLOGIN = ROOT_URL+"userLogin.php";
    public static final String ROOT_URLRECORD = ROOT_URL+"Record.php";

    //insert register
    public static final String ID = "id";
    public static final String FULLNAME = "fullname";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String CPASSWORD = "cpassword";

    //insert record
   public static final String TFincubatorid = "id";
   public static final String TFTotal = "total";
   public static final String Calender = "Calender";



    //display information
    public static final String TAG_USERNAME = "username";
    public static final String TAG_FIRST_NAME = "first_name";
    public static final String TAG_LAST_NAME = "last_name";
    public static final String TAG_EMAIL = "email";
    public static final String TAG_PHONENO = "phoneNo";
    public static final String TAG_PASSWORD = "password";


    //public static final String DIAGNOSE = "diagnose";*/

}
