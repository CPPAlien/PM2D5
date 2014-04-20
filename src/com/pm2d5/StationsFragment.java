package com.pm2d5;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pm2d5.R;
import com.pm2d5.db.DBService;
import com.pm2d5.include.Macro;
import com.pm2d5.lib.PullToRefreshBase.OnRefreshListener;
import com.pm2d5.lib.StationItem;
import com.pm2d5.lib.StationsListAdapter;
import com.pm2d5.net.Data;
import com.pm2d5.net.StationData;
import com.pm2d5.thread.GetStationsThread;
import com.pm2d5.view.PullToRefreshListView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

public class StationsFragment extends Fragment {
	private String tag = "StationsFragment";
	private PullToRefreshListView refreshList = null;
	private ListView list = null;
	private DBService dbService = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.stations_list, null);
		dbService = DBService.getInstance(getActivity());
		refreshList = (PullToRefreshListView)view.findViewById(R.id.stations_list);
		list = refreshList.getRefreshableView();
		
		ArrayList<StationData> stationDatas = dbService.GetStations("北京");
		DisplayStations(stationDatas);

		refreshList.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				Map<String, String> map = new HashMap<String, String>();
				map.put("city", "beijing");
				new GetStationsThread(handler, tag, map).start();
			}
			
		});
		return view;
	}
	
	static class MyInnerHandler extends Handler {
		WeakReference<StationsFragment> mActivity;

		MyInnerHandler(StationsFragment mAct) {
			mActivity = new WeakReference<StationsFragment>(mAct);
		}

		@Override
		public void handleMessage(Message msg) {
			StationsFragment theAct = mActivity.get();
			switch (msg.what) {
			case Macro.FETCH_SUCCESS:
				Data data = (Data) msg.obj;
				theAct.dbService.SaveDatas(data.getStationDataList());
				ArrayList<StationData> stationDatas = theAct.dbService.GetStations("北京");
				theAct.DisplayStations(stationDatas);
				Toast.makeText(theAct.getActivity(), "刷新成功", Toast.LENGTH_SHORT).show();
				break;
			case Macro.FETCH_FAIL:
				Toast.makeText(theAct.getActivity(), "刷新失败", Toast.LENGTH_SHORT).show();
				break;
			case Macro.NETWORK_ERROR:
				Toast.makeText(theAct.getActivity(), "网络连接错误", Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
			}
			theAct.refreshList.onRefreshComplete();
		}
	}

	MyInnerHandler handler = new MyInnerHandler(this);
	
	/**
	 * 传入获得到的站点信息，进行排序listview显示
	 * @param stationDatas
	 */
	private void DisplayStations(ArrayList<StationData> stationDatas) {
		List<StationItem> stationList = new ArrayList<StationItem> ();
		StationItem stationItem;
		Collections.sort(stationDatas);
		for(StationData station : stationDatas) {
			stationItem = new StationItem(station.getPosition_name(), Integer.toString(station.getAqi()));
			stationList.add(stationItem);
		}
		/*不足十行，补足成十行*/
		int size = 0;
		if (null != stationDatas) {
			size = stationDatas.size();
		}
		for (int i = 0; i < 10 - size; ++i) {
			stationItem = new StationItem("", "");		
			stationList.add(stationItem);
		}
		StationsListAdapter stationsListAdapter = new StationsListAdapter(getActivity(), stationList, list);
		list.setAdapter(stationsListAdapter);
	}

}
