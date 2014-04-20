package com.pm2d5.net;

import java.io.Serializable;

public class ServerInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4284591951842396794L;
	private String error = null;
	private Data data = null;
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "error:" + error + ", data:" + data;
	}
	
	
}
