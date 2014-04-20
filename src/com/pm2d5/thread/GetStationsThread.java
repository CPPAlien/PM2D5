package com.pm2d5.thread;

import java.util.Map;

import android.os.Handler;
import android.os.Message;

import com.pm2d5.include.Macro;
import com.pm2d5.net.FetchData;
import com.pm2d5.net.FetchData.EnumCode;

/**
 * 传入Handler、tag、请求参数map，进行Station数据请求
 * @author chris
 *
 */
public class GetStationsThread extends Thread {
	private Handler handler = null;
	private String tag = null;
	private Map<String, String> map = null;
	
	public GetStationsThread(Handler handler, String tag, Map<String, String> map) {
		this.handler = handler;
		this.tag = tag;
		this.map = map;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		FetchData fetchData = new FetchData(tag);
		EnumCode code = fetchData.setRequest(Macro.URL_STATION, map);
		Message message = new Message();
		switch(code) {
		case NETWORK_ERROR:
			message.what = Macro.NETWORK_ERROR;
			handler.sendMessage(message);
			break;
		case OPERATE_SUCCESS:
			message.what = Macro.FETCH_SUCCESS;
			message.obj = fetchData.getData();
			handler.sendMessage(message);
			break;
		default:
			message.what = Macro.FETCH_FAIL;
			handler.sendMessage(message);
			break;
		}
	}
}