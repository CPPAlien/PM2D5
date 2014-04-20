package com.pm2d5.view;


import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.pm2d5.AboutFragment;
import com.pm2d5.HomeFragment;
import com.pm2d5.R;
import com.pm2d5.SettingsFragment;
import com.pm2d5.StationsFragment;
import com.pm2d5.include.Macro;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class SlideMenuView extends ListFragment {
	private FragmentActivity activity = null;
	private SlidingMenu menu = null;
	public SlideMenuView(){}
	
	public SlideMenuView(FragmentActivity activity) {
		this.activity = activity;
	}
	
	public SlideMenuView(FragmentActivity activity, SlidingMenu menu) {
		this.activity = activity;
		this.menu = menu;
	}
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.slidemenu_list, null);
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		SampleAdapter adapter = new SampleAdapter(getActivity());
		adapter.add(new SampleItem("Home", -1));
		adapter.add(new SampleItem("Stations", -1));
		adapter.add(new SampleItem("Settings", -1));
		adapter.add(new SampleItem("About", -1));
		setListAdapter(adapter);
	}

	private class SampleItem {
		public String tag;
		public int iconRes;

		public SampleItem(String tag, int iconRes) {
			this.tag = tag;
			this.iconRes = iconRes;
		}
	}

	public class SampleAdapter extends ArrayAdapter<SampleItem> {

		public SampleAdapter(Context context) {
			super(context, 0);
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = LayoutInflater.from(getContext()).inflate(
						R.layout.slidemenu_row, null);
			}
			ImageView icon = (ImageView) convertView
					.findViewById(R.id.row_icon);
			icon.setImageResource(getItem(position).iconRes);
			TextView title = (TextView) convertView
					.findViewById(R.id.row_title);
			title.setText(getItem(position).tag);

			return convertView;
		}
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		switch(position) {
		case Macro.SLIDEMENU_HOME:
			menu.showContent();
			activity.getSupportFragmentManager()
			.beginTransaction()
			.replace(R.id.content_frame, new HomeFragment())
			.commit();
			break;
		case Macro.SLIDEMENU_STATIONS:
			menu.showContent();
			activity.getSupportFragmentManager()
			.beginTransaction()
			.replace(R.id.content_frame, new StationsFragment())
			.commit();
			break;
		case Macro.SLIDEMENU_ABOUT:
			menu.showContent();
			activity.getSupportFragmentManager()
			.beginTransaction()
			.replace(R.id.content_frame, new AboutFragment())
			.commit();
			break;
		case Macro.SLIDEMENU_SETTINGS:
			menu.showContent();
			activity.getSupportFragmentManager()
			.beginTransaction()
			.replace(R.id.content_frame, new SettingsFragment())
			.commit();
			break;
		default:
			break;
		}
	}

}