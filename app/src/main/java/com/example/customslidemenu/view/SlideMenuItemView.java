package com.example.customslidemenu.view;

import com.example.customslidemenu.R;
import com.example.customslidemenu.R.id;
import com.example.customslidemenu.R.layout;
import com.example.customslidemenu.models.SlideMenuItem;

import android.app.Service;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SlideMenuItemView extends LinearLayout {
	ImageView iv_item;
	TextView tv_item;

	public SlideMenuItemView(Context context) {
		super(context);
		initView(context);
	}

	public void initView(Context context) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.slidemenu_item, this);
		iv_item = (ImageView) findViewById(R.id.iv_item);
		tv_item = (TextView) findViewById(R.id.tv_item);
	}

	public void setData(SlideMenuItem item) {
		iv_item.setBackgroundResource(item.getIcon());
		tv_item.setText(item.getName());
	}

}
