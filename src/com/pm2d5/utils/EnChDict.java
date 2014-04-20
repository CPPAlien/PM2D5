package com.pm2d5.utils;

import java.util.EnumSet;

/**
 * 城市名中英文字典转换
 * @author chris
 *
 */
public class EnChDict {
	public static enum Dict {
		BEIJING("beijing", "北京"),
		SHANGHAI("shanghai", "上海"),
		SHENZHEN("shenzhen","深圳"),
		CHONGQING("chongqing","重庆");
		
		private String cityNameEn = null;
		private String cityNameCh = null;
		Dict(String cityNameEn, String cityNameCh) {
			// TODO Auto-generated constructor stub
			this.cityNameEn = cityNameEn;
			this.cityNameCh = cityNameCh;
		}
		
		public String getEnName() {
			return cityNameEn;
		}
		
		public String getChName() {
			return cityNameCh;
		}
	}
	
	/**
	 * 转换英文到中文
	 * @param enName
	 * @return
	 */
	public static String ChangeEnToCh(String enName) {
		for (Dict dict:EnumSet.allOf(Dict.class)) {
			if (enName.equals(dict.getEnName())) {
				return dict.getChName();
			}
		}
		return null;
	}
	
	/**
	 * 转换中文到英文
	 * @param chName
	 * @return
	 */
	public static String ChangeChToEn(String chName) {
		for (Dict dict:EnumSet.allOf(Dict.class)) {
			if (chName.equals(dict.getChName())) {
				return dict.getEnName();
			}
		}
		return null;
	}
}
