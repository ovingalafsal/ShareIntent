package com.example.shareinentcallback;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class PlaceholderFragment extends Fragment implements OnClickListener {
	
	ArrayList<ShareApps> appList = new ArrayList<ShareApps>();
	AppListAdapter adapter;
	EditText shareEdt;
	
	public PlaceholderFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main, container,
				false);
		rootView.findViewById(R.id.share_btn).setOnClickListener(this);
		
		shareEdt = (EditText)rootView.findViewById(R.id.share_text);
		ListView list = (ListView)rootView.findViewById(R.id.list);
		adapter = new AppListAdapter(getActivity(), R.id.list, appList);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String text = shareEdt.getText().toString();
				Intent sendIntent = new Intent();
				sendIntent.setAction(Intent.ACTION_SEND);
				sendIntent.putExtra(Intent.EXTRA_TEXT, text);
				sendIntent.setType("text/plain");
				sendIntent.setPackage(appList.get(position).appPackageName);
				startActivity(sendIntent);
				Toast.makeText(getActivity(), "Share to " + appList.get(position).appName, Toast.LENGTH_LONG).show();
			}
		});
		return rootView;
	}

	private void setShareAppList() {
		PackageManager manager = getActivity().getPackageManager();
	    Intent intent = new Intent();
	    intent.setAction(Intent.ACTION_SEND);
	    intent.addCategory(Intent.CATEGORY_DEFAULT);
	    // NOTE: Provide some data to help the Intent resolver
	    intent.setType("text/plain");
	    // Query for all activities that match my filter and request that the filter used
	    //  to match is returned in the ResolveInfo
	    List<ResolveInfo> infos = manager.queryIntentActivities (intent,
	                                   PackageManager.GET_RESOLVED_FILTER);
	    for (ResolveInfo info : infos) {
	        ActivityInfo activityInfo = info.activityInfo;
	        IntentFilter filter = info.filter;
	        if (filter != null && filter.hasAction(Intent.ACTION_SEND) &&
	                  filter.hasCategory(Intent.CATEGORY_DEFAULT)) {
//		
				String appname = activityInfo.applicationInfo
						.loadLabel(getActivity().getPackageManager())
						.toString();
				String pname = activityInfo.packageName;
				Drawable icon = activityInfo.applicationInfo
						.loadIcon(getActivity().getPackageManager());
				appList.add(new ShareApps(appname, pname, icon));
				Log.e("", appname + " \t" + pname);
			}
		}
	   adapter.notifyDataSetChanged();
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.share_btn:
			if(appList.size() > 0) {
				appList.removeAll(appList);
				adapter.notifyDataSetChanged();
			}
			setShareAppList();
			break;

		default:
			break;
		}

	}


}
