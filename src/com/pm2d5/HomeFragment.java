package com.pm2d5;

import com.pm2d5.R;
import com.pm2d5.db.DBService;
import com.pm2d5.include.Values;
import com.pm2d5.utils.DisplayRule;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeFragment extends Fragment{
	DBService dbService = null;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_home, null);
		dbService = DBService.getInstance(getActivity());
		TextView txAqi = (TextView) view.findViewById(R.id.home_text);
		TextView txTimePoint = (TextView)view.findViewById(R.id.time_point);
		ImageView iv = (ImageView)view.findViewById(R.id.home_image);
		
		String aqi = dbService.GetCityAqi(Values.currentCity);
		if (null != aqi) {
			txAqi.setText(aqi);
			txAqi.setTextColor(DisplayRule.GetColor(Integer.parseInt(aqi)));
			txTimePoint.setText(dbService.GetLastTime(Values.currentCity));
			iv.setImageResource(DisplayRule.GetImage(Integer.parseInt(aqi)));
		} else {
			txAqi.setText("获得失败");
		}
		
		return view;
	}
}
