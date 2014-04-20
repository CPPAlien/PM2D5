package com.pm2d5.net;

import java.io.Serializable;
import java.util.ArrayList;

public class Data implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1536767465223825563L;
	private StationData stationData = null;
	private ArrayList<StationData> stationDataList = null;

	public StationData getStationData() {
		return stationData;
	}
	public void setStationData(StationData stationData) {
		this.stationData = stationData;
	}
	public ArrayList<StationData> getStationDataList() {
		return stationDataList;
	}
	public void setStationDataList(ArrayList<StationData> stationDataList) {
		this.stationDataList = stationDataList;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Data [ stationData:" + stationData + "stationDataList:" + stationDataList + "]";
	}
	
	
	
}
