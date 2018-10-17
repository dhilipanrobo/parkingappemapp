package com.skyparking.admin.parkingappemapp;

import java.util.Calendar;

public class constant  {


    public  static  final String ROOT_URL="http://www.welleservices.com/emapp/api/";
    public  static  final String ROOT_URL1="http://www.welleservices.com/noticeboard/";

    public  static  final String URL_Datasyn=ROOT_URL+"sync";
    public  static  final String URL_Datasyn_update=ROOT_URL+"syncUpdate";
    public  static  final String URL_Datasyn_update1=ROOT_URL1+"tokenupdate.php";
    public  static  final String URL_log1 =ROOT_URL+"login";
    public  static  final String URL_Tokenget =ROOT_URL+"price";
    public  static  final String URL_msg =ROOT_URL+"header";
    public  static  final String URL_Msg =ROOT_URL+"tokengetmsg.php?zoneid=";
    public  static  final String URL_T_bno =ROOT_URL+"getbillno";

    public  static String foot = null;
    public  static String foot1 = null;
    public  static String head = null;
    public  static String head2 = null;
    public  static String lvno = null;
    public  static String l_amount = null;
    public  static String l_tno = null;
    public  static String l_day = null;
    public  static String l_vtype = null;

    static Calendar cal= Calendar.getInstance();
    static String y= String.format("%02d",cal.get(Calendar.YEAR));
    static String m= String.format("%02d",cal.get(Calendar.MONTH)+1);
    static String d= String.format("%02d",cal.get(Calendar.DATE));
    String h= String.format("%02d",cal.get(Calendar.HOUR));
    String hh= String.format("%02d",cal.get(Calendar.HOUR_OF_DAY));
    String mm= String.format("%02d",cal.get(Calendar.MINUTE));

    public  static String mDate=d+"-"+m+"-"+y;


}

