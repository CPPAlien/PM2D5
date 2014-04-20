package com.pm2d5;

import com.pm2d5.R;
import com.pm2d5.db.DBService;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HomeFragment extends Fragment{
	DBService dbService = null;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_home, null);
		dbService = DBService.getInstance(getActivity());
		TextView tx = (TextView) view.findViewById(R.id.home_text);
		String aqi = dbService.GetCityAqi("北京");
		if (null != aqi) {
			tx.setText(aqi);
		} else {
			tx.setText("获得失败");
		}
		return view;
	}
}
