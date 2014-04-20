package com.pm2d5;

import com.pm2d5.utils.EnChDict;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsFragment extends Fragment{
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view =  inflater.inflate(R.layout.city_list, null);
		ListView list = (ListView)view.findViewById(R.id.city_list);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				TextView textView = (TextView)view;
				getActivity();
				SharedPreferences preferences = getActivity().getSharedPreferences("pm2d5pref", Context.MODE_PRIVATE);
				SharedPreferences.Editor editor = preferences.edit();
				editor.putString("cityName", EnChDict.ChangeChToEn(textView.getText().toString()));
				editor.commit();
				getActivity().startActivity(new Intent(getActivity(), EnterActivity.class));
				Toast.makeText(getActivity(), textView.getText().toString(), Toast.LENGTH_SHORT).show();
			}
		});

		return view;
	}
	
	
}
