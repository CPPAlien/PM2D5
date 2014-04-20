package com.pm2d5.lib;

import java.util.List;

import com.pm2d5.R;
import com.pm2d5.include.Macro;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class StationsListAdapter extends ArrayAdapter<StationItem>{
	public StationsListAdapter(Activity activity, List<StationItem> listcontent, ListView listview) {
		super(activity, 0, listcontent);
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		Activity activity = (Activity) getContext();
		View rowView = convertView;
	    StationViewCache viewCache;
	    if (rowView == null) {
	    	LayoutInflater inflater = activity.getLayoutInflater();
	    	rowView = inflater.inflate(R.layout.station_item, null);
	    	viewCache = new StationViewCache(rowView);
	    	rowView.setTag(viewCache);
	    } else {
	    	viewCache = (StationViewCache) rowView.getTag();
	    }
	    StationItem stationItem = getItem(position);
	    
	    String stationName = stationItem.getName();
	    TextView textName = viewCache.getTextName();
	    if (null != stationName) {
	    	textName.setText(stationName);
	    }
	    
	    /*处理pm2.5值显示*/
	    String stationValue = stationItem.getValue();
	    TextView textValue = viewCache.getTextValue();
	    if ((null != stationValue) && (!stationValue.equals(""))) {
	    	int stationValueInt = Integer.parseInt(stationValue);
	    	textValue.setText(stationValue);
	    	if(stationValueInt <= Macro.LEVEL_A_AIR) {
	    		textValue.setTextColor(Color.GREEN);
	    	} else if (stationValueInt <= Macro.LEVEL_B_AIR) {
	    		textValue.setTextColor(Color.BLUE);
	    	} else if (stationValueInt <= Macro.LEVEL_C_AIR) {
	    		textValue.setTextColor(Color.YELLOW);
	    	} else if (stationValueInt <= Macro.LEVEL_D_AIR) {
	    		textValue.setTextColor(Color.RED);
	    	} else {
	    		textValue.setTextColor(Color.MAGENTA);
	    	}
	    }
	    
		return rowView;
		
	}

}
