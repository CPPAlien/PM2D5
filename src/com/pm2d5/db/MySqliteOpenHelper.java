package com.pm2d5.db;

import com.pm2d5.include.Macro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySqliteOpenHelper extends SQLiteOpenHelper {

	public MySqliteOpenHelper(Context context)
	{
		super(context, Macro.DB_NAME, null, Preferences.CURRENT_VERSION_CODE);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		onCreateAllTables(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if(oldVersion > newVersion){
			dropAllTables(db);
			onCreateAllTables(db);
		}else{
			switch (oldVersion) {
			case 1://
				break;
			default:
				dropAllTables(db);
				onCreateAllTables(db);
				break;
			}
		}
	}
	
	private void onCreateAllTables(SQLiteDatabase db){
		try{
			if(db != null){
				db.beginTransaction();
				//创建检测点表
				db.execSQL("CREATE TABLE IF NOT EXISTS " + Macro.DB_TABLE_STATION 
						+ "( _id integer primary key autoincrement, "
						+ Macro.DB_ELEMENT_STATIONCODE + " varchar, "
						+ Macro.DB_ELEMENT_STATIONNAME + " varchar, "
						+ Macro.DB_ELEMENT_STATIONVALUE +" varchar, "
						+ Macro.DB_ELEMENT_CITYNAME + " varchar) ");
				
				db.setTransactionSuccessful();
			}
		}finally{
			if(db != null)
			{
				db.endTransaction();
			}
		}
		
		try{
			if(db != null){
				db.beginTransaction();
				//创建城市表
				db.execSQL("CREATE TABLE IF NOT EXISTS " + Macro.DB_TABLE_CITY 
						+ "( _id integer primary key autoincrement, "
						+ Macro.DB_ELEMENT_CITYVALUE +" varchar, "
						+ Macro.DB_ELEMENT_CITYNAME + " varchar) ");
				
				db.setTransactionSuccessful();
			}
		}finally{
			if(db != null)
			{
				db.endTransaction();
			}
		}
	}
	private void dropAllTables(SQLiteDatabase db)
	{
		try {
            if (db != null) {
                db.beginTransaction();
                db.execSQL("DROP TABLE IF EXISTS " + Macro.DB_TABLE_STATION);

                db.setTransactionSuccessful();
            }
        } finally {
            if (db != null) {
                db.endTransaction();
            }
        }
		
		try {
            if (db != null) {
                db.beginTransaction();
                db.execSQL("DROP TABLE IF EXISTS " + Macro.DB_TABLE_CITY);

                db.setTransactionSuccessful();
            }
        } finally {
            if (db != null) {
                db.endTransaction();
            }
        }
	}

}
