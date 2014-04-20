package com.pm2d5.lib;

import com.pm2d5.R;

import android.view.View;
import android.widget.TextView;

public class StationViewCache {
	private View baseView = null;
	private TextView textName = null;
	private TextView textValue = null;
	
	public StationViewCache(View baseView) {
		this.baseView = baseView;
	}
	public TextView getTextName() {
		if (null == textName) {
			textName = (TextView)baseView.findViewById(R.id.station_item_name);
		}
		return textName;
	}
	public void setTextName(TextView textName) {		
		this.textName = textName;
	}
	public TextView getTextValue() {
		if (null == textValue) {
			textValue = (TextView)baseView.findViewById(R.id.station_item_value);
		}
		return textValue;
	}
	public void setTextValue(TextView textValue) {
		this.textValue = textValue;
	}

}
