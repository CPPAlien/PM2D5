package com.pm2d5.db;

import java.util.ArrayList;

import com.pm2d5.include.Macro;
import com.pm2d5.net.StationData;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBService extends DaoHelper {

	private static DBService m_stInstance;

	public static DBService getInstance(Context context) {
		if (m_stInstance == null)
			m_stInstance = new DBService(context);
		return m_stInstance;
	}

	protected DBService(Context context) {
		super(context);
	}

	/**
	 * 保存获得到的数据
	 * @param list
	 */
	public synchronized void SaveDatas(ArrayList<StationData> list) {
		Cursor c = null;
		SQLiteDatabase db = mDb.getWritableDatabase();
		db.beginTransaction();
		
		try {
			if (list != null && list.size() > 0) {
				for (StationData station : list) {
					//站点名为空时，说明该条记录为该城市平均值,记入城市表
					if (null == station.getPosition_name()) {
						ContentValues values = new ContentValues();
						values.put(Macro.DB_ELEMENT_CITYNAME, station.getArea());
						values.put(Macro.DB_ELEMENT_CITYVALUE, station.getAqi());
						long rows = db.update(Macro.DB_TABLE_CITY, values, 
								Macro.DB_ELEMENT_CITYNAME + EQUAL,
								new String[] { station.getArea() });
						if(rows <= 0){
							rows = db.insert(Macro.DB_TABLE_CITY, null, values);
						}
					} else {
						ContentValues values = new ContentValues();
						values.put(Macro.DB_ELEMENT_STATIONCODE, station.getStation_code());
						values.put(Macro.DB_ELEMENT_STATIONNAME, station.getPosition_name());
						values.put(Macro.DB_ELEMENT_STATIONVALUE, station.getAqi());
						values.put(Macro.DB_ELEMENT_CITYNAME, station.getArea());
						long rows = db.update(Macro.DB_TABLE_STATION, values, 
								Macro.DB_ELEMENT_STATIONCODE + EQUAL,
								new String[] { station.getStation_code() });
						if(rows <= 0){
							rows = db.insert(Macro.DB_TABLE_STATION, null, values);
						}
					}
				}
			}
			db.setTransactionSuccessful();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.endTransaction();
			closeCursor(c);
			closeDB(db);
		}
	}
	/**
	 * 根据城市名获得该城市所有检测站空气质量信息
	 * @param cityName，为空，默认为北京
	 * @return 该城市所有站点空气质量信息，数据库为空，则返回null
	 */
	public ArrayList<StationData> GetStations(String cityName){
		ArrayList<StationData> list = new ArrayList<StationData>();
		SQLiteDatabase db = null;
		Cursor c = null;
		
		if (null == cityName) {
			cityName = Macro.DEFAULT_CITY;
		}
		try{
			db = mDb.getWritableDatabase();
			c = db.rawQuery(SELECT_FROM + Macro.DB_TABLE_STATION + WHERE + 
					Macro.DB_ELEMENT_CITYNAME + EQUAL, new String[]{cityName});
			while(c.moveToNext()){
				StationData station = new StationData();
				station.setPosition_name(c.getString(c.getColumnIndex(Macro.DB_ELEMENT_STATIONNAME)));
				station.setAqi(c.getInt((c.getColumnIndex(Macro.DB_ELEMENT_STATIONVALUE))));
				list.add(station);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			closeCursorAndDB(c, db);
		}
		return list;
	}
	
	public String GetCityAqi(String cityName) {
		SQLiteDatabase db = null;
		Cursor c = null;
		String cityAqi = null;
		try{
			db = mDb.getWritableDatabase();
			c = db.rawQuery(SELECT_FROM + Macro.DB_TABLE_CITY + WHERE + 
					Macro.DB_ELEMENT_CITYNAME + EQUAL, new String[]{cityName});
			if (c.moveToFirst()) {
				cityAqi = c.getString(c.getColumnIndex(Macro.DB_ELEMENT_CITYVALUE));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			closeCursorAndDB(c, db);
		}
		return cityAqi;
		
	}
}
