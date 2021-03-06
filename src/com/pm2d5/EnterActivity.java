package com.pm2d5;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import com.pm2d5.db.DBService;
import com.pm2d5.include.Macro;
import com.pm2d5.include.Values;
import com.pm2d5.net.Data;
import com.pm2d5.thread.GetStationsThread;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.widget.Toast;

public class EnterActivity extends Activity{
	private String tag = "EnterActivity";
	private DBService dbService = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_enter);
		
		/*从sharedpreferences中获得城市名，如果没有，以默认名代替*/
		SharedPreferences preferences = getSharedPreferences("pm2d5pref", MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		String cityName = preferences.getString("cityName", null);
		if (null == cityName) {
			cityName = Macro.DEFAULT_CITY;
			editor.putString("cityName", Macro.DEFAULT_CITY);
			editor.commit();
		}
		Values.currentCity = cityName;
		dbService = DBService.getInstance(this);
		
		/*如果数据库中无值，才进行请求*/
		if(null == dbService.GetCityAqi(cityName)) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("city", cityName);
			new GetStationsThread(handler, tag, map).start();
		} else {
			/*new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						Thread.sleep(Macro.DURATION_JUMP);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					startActivity(new Intent(EnterActivity.this, MainActivity.class));
				}
			}).start();*/
			startActivity(new Intent(EnterActivity.this, MainActivity.class));
		}
	}
	
	
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		this.finish();
		super.onPause();
	}
	
	static class MyInnerHandler extends Handler {
		WeakReference<EnterActivity> mActivity;

		MyInnerHandler(EnterActivity mAct) {
			mActivity = new WeakReference<EnterActivity>(mAct);
		}

		@Override
		public void handleMessage(Message msg) {
			EnterActivity theAct = mActivity.get();
			switch (msg.what) {
			case Macro.FETCH_SUCCESS:
				Data data = (Data) msg.obj;
				Toast.makeText(theAct, "获取成功", Toast.LENGTH_SHORT).show();
				theAct.dbService.SaveDatas(data.getStationDataList());
				theAct.startActivity(new Intent(theAct, MainActivity.class));
				break;
			case Macro.FETCH_FAIL:
				Toast.makeText(theAct, "获取失败", Toast.LENGTH_SHORT).show();
				break;
			case Macro.NETWORK_ERROR:
				Toast.makeText(theAct, "网络连接错误", Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
			}
			theAct.finish();
		}
	}

	MyInnerHandler handler = new MyInnerHandler(this);
	
	
}
