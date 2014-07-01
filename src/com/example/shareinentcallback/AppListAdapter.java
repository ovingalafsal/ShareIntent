package com.example.shareinentcallback;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class AppListAdapter extends ArrayAdapter<ShareApps>{

	ArrayList<ShareApps> appList;
	Activity context;
	
	public AppListAdapter(Activity context, int textViewResourceId,
			ArrayList<ShareApps> objects) {
		super(context, textViewResourceId, objects);
		this.appList = objects;
		this.context = context;
	}
	
	static class ViewHolder {
		ImageView appIcon;
		TextView appName;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		ViewHolder holder;

		if (convertView == null) {
			LayoutInflater vi = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.app_row, null);

			holder = new ViewHolder();
			holder.appIcon = (ImageView) v.findViewById(R.id.img_row);
			holder.appName = (TextView) v.findViewById(R.id.txt_name);
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		ShareApps model = appList.get(position);
		holder.appName.setText(model.appName);
		holder.appIcon.setImageDrawable(model.appIcon);
		
		return v;
	}
}
