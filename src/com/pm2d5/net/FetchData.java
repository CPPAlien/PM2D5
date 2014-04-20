package com.pm2d5.net;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import com.alibaba.fastjson.JSONException;
import com.pm2d5.include.Macro;
import com.pm2d5.utils.LogHelper;

public class FetchData {
	private String tag = null;
	private Data data = null;
	public enum EnumCode{
		/************系统级返回码*************/
		//操作成功
		OPERATE_SUCCESS,
		//操作失败
		OPERATE_FAIL,
		//参数错误
		NETWORK_ERROR;
		
	}
	
	public FetchData(String tag) {
		this.tag = tag;
	}
	public EnumCode setRequest(String url, Map<String, String> map) {
		map.put("token", Macro.TOKEN);
		
		String strResult = null;
		ServerInfo si = null;
		try {
			strResult = StaticHttpClient.doGet(
					url, map);
			if (null == strResult) {
				LogHelper.v(this.tag, url+":"+"null == strResult");
				return EnumCode.NETWORK_ERROR;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			LogHelper.v(this.tag, url+":"+"ClientProtocolException");
			return EnumCode.NETWORK_ERROR;
		} catch (IOException e) {
			e.printStackTrace();
			LogHelper.v(this.tag, url+":"+"IOException");
			return EnumCode.NETWORK_ERROR;
		}
		if (strResult.startsWith("[")) {
			strResult = "{\"error\":\"ok\",\"data\":{\"stationDataList\":" + strResult + "}}";
		}
		System.out.println(strResult);
		try {
			si = (ServerInfo)JsonParser.parseObject(strResult,
					ServerInfo.class);
		} catch (JSONException e) {
			e.printStackTrace();
			LogHelper.v(this.tag, url+":"+"JSONException");
			return EnumCode.NETWORK_ERROR;
		}
		
		String error = si.getError();
		if (!error.equals("ok")) {
			LogHelper.v(this.tag, url+":"+"ERROR:"+error);
			return EnumCode.OPERATE_FAIL;
		}
		
		Data data = si.getData();
		if(null == data) {
			LogHelper.v(this.tag, url+":"+"null == data");
			return EnumCode.NETWORK_ERROR;
		}
		this.data = data;
		
		return EnumCode.OPERATE_SUCCESS;
		
	}
	
	public Data getData() {
		return this.data;
	}
}
