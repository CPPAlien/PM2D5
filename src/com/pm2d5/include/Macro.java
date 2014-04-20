package com.pm2d5.include;

public class Macro {
	public final static String TOKEN = "6o3mHY9i1DuQNZLBpcqx";
	public final static String DEFAULT_CITY = "北京";
	
	/*For DB*/
	public final static String DB_NAME = "pm2d5.db";
	public final static String DB_TABLE_STATION = "pm2d5_station";
	public final static String DB_TABLE_CITY = "pm2d5_city";
	public final static String DB_ELEMENT_CITYNAME = "cityName";
	public final static String DB_ELEMENT_CITYVALUE = "cityValue";
	public final static String DB_ELEMENT_STATIONNAME = "stationName";
	public final static String DB_ELEMENT_STATIONVALUE = "stationValue";
	public final static String DB_ELEMENT_STATIONCODE = "stationCode";
	
	/*For View*/
	public final static int SLIDEMENU_HOME = 0;
	public final static int SLIDEMENU_STATIONS = 1;
	public final static int SLIDEMENU_ABOUT = 3;
	public final static int SLIDEMENU_SETTINGS = 2;
	
	/*空气质量等级划分*/
	public final static int LEVEL_A_AIR = 50;
	public final static int LEVEL_B_AIR = 100;
	public final static int LEVEL_C_AIR = 200;
	public final static int LEVEL_D_AIR = 300;
	public final static int LEVEL_E_AIR = 400;
	
	/*For net*/
	public final static String URL = "http://www.pm25.in";
	public final static String URL_STATION = URL + "/api/querys/pm2_5.json";
	
	public final static int FETCH_FAIL = 0;
	public final static int FETCH_SUCCESS = 1;
	public final static int NETWORK_ERROR = 999;
	
	/*For Preference*/
	public final static int DURATION_JUMP = 1000;  //从首页跳转到主界面时间
}
