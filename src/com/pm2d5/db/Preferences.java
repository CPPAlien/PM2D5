package com.pm2d5.db;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreferences公共类
 * @author jiabin
 * */
public class Preferences {
	public static final int CURRENT_VERSION_CODE = 1;//当前小版本
	
	private static final String Preference_Pm2d5 = "Pm2d5Prefs";

	private SharedPreferences mPrefs;

	private static Preferences m_stInstance;

	private Context m_Context;

	public static synchronized Preferences getInstance(Context context) {
		if (m_stInstance == null)
			m_stInstance = new Preferences(context);

		return m_stInstance;
	}

	private Preferences(Context context) {
		m_Context = context.getApplicationContext();
		doLoadPrefs();
	}

	public void doLoadPrefs() {
		mPrefs = m_Context.getSharedPreferences(Preference_Pm2d5, 0);
	}
	
}
