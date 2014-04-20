package com.pm2d5.utils;

import com.pm2d5.R;
import com.pm2d5.include.Macro;

import android.R.integer;
import android.graphics.Color;
/**
 * 根据传参决定现实规则
 * @author chris
 *
 */
public class DisplayRule {
	/**
	 * 根据不同值，返回不同颜色
	 * @param value
	 * @return
	 */
	public static int GetColor(int value){
    	if(value <= Macro.LEVEL_A_AIR) {
    		return Color.GREEN;
    	} else if (value <= Macro.LEVEL_B_AIR) {
    		return Color.BLUE;
    	} else if (value <= Macro.LEVEL_C_AIR) {
    		return Color.YELLOW;
    	} else if (value <= Macro.LEVEL_D_AIR) {
    		return Color.RED;
    	} else {
    		return Color.MAGENTA;
    	}
	}
	/**
	 * 
	 */
	public static int GetImage(int value){
		if(value <= Macro.LEVEL_A_AIR) {
    		return R.drawable.face_level_1;
    	} else if (value <= Macro.LEVEL_B_AIR) {
    		return R.drawable.face_level_2;
    	} else if (value <= Macro.LEVEL_C_AIR) {
    		return R.drawable.face_level_3;
    	} else if (value <= Macro.LEVEL_D_AIR) {
    		return R.drawable.face_level_4;
    	} else {
    		return R.drawable.face_level_4;
    	}
	}
	
}
