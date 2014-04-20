package com.pm2d5.net;

import java.io.Serializable;
import java.util.Comparator;

public class StationData implements Serializable,Comparable<StationData>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8359603672915063297L;
	private int aqi = 0;
    private String area = null;
    private int pm2_5 = 0;
    private int pm2_5_24h = 0;
    private String position_name = null;
    private String primary_pollutant = null;
    private String quality = null;
    private String station_code = null;
    private String time_point = null;
	public int getAqi() {
		return aqi;
	}
	public void setAqi(int aqi) {
		this.aqi = aqi;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public int getPm2_5() {
		return pm2_5;
	}
	public void setPm2_5(int pm2_5) {
		this.pm2_5 = pm2_5;
	}
	public int getPm2_5_24h() {
		return pm2_5_24h;
	}
	public void setPm2_5_24h(int pm2_5_24h) {
		this.pm2_5_24h = pm2_5_24h;
	}
	public String getPosition_name() {
		return position_name;
	}
	public void setPosition_name(String position_name) {
		this.position_name = position_name;
	}
	public String getPrimary_pollutant() {
		return primary_pollutant;
	}
	public void setPrimary_pollutant(String primary_pollutant) {
		this.primary_pollutant = primary_pollutant;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	public String getStation_code() {
		return station_code;
	}
	public void setStation_code(String station_code) {
		this.station_code = station_code;
	}
	public String getTime_point() {
		return time_point;
	}
	public void setTime_point(String time_point) {
		this.time_point = time_point;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Station [aqi=" + aqi + ", area=" + area + ", pm2_5=" + pm2_5
                + ", pm2_5_24h=" + pm2_5_24h + ", position_name=" + position_name + 
                ", primary_pollutant=" + primary_pollutant + ", quality=" + quality
                + ", station_code=" + station_code + ", time_point=" + time_point +"]";
	}

	@Override
	public int compareTo(StationData another) {
		// TODO Auto-generated method stub
		return another.aqi - this.aqi;
	}
	
	
}
