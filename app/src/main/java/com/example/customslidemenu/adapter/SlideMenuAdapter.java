package com.example.customslidemenu.adapter;

import java.util.List;

import com.example.customslidemenu.models.SlideMenuItem;
import com.example.customslidemenu.view.SlideMenuItemView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class SlideMenuAdapter extends ArrayAdapter<SlideMenuItem>{
	public List<SlideMenuItem> arrItem;
	Context context;
	public SlideMenuAdapter(Context context, int resource,
			List<SlideMenuItem> objects) {
		super(context, resource, objects);
		this.context = context;
		this.arrItem = objects;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = new SlideMenuItemView(context);
		((SlideMenuItemView) convertView).setData(arrItem.get(position));
		return convertView;
	}
	
}
