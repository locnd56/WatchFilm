package com.example.customslidemenu.adapter;

import java.util.List;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.customslidemenu.R;
import com.example.customslidemenu.cacheimagevolley.AppController;
import com.example.customslidemenu.models.DetailFilmItem;

import android.app.Service;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListFilmAdapter extends ArrayAdapter<DetailFilmItem> {
	Context context;
	List<DetailFilmItem> arr;
	int LayoutId;
	NetworkImageView iv_item;
	TextView tv_item;
	ImageLoader imgLoader;

	public ListFilmAdapter(Context context, int LayoutId,
			List<DetailFilmItem> objects) {
		super(context, LayoutId, objects);
		this.context = context;
		this.arr = objects;
		this.LayoutId = LayoutId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
		convertView = inflater.inflate(LayoutId, null);
		iv_item = (NetworkImageView) convertView.findViewById(R.id.iv_item);
		imgLoader = AppController.getInstance().getImageLoader();
		tv_item = (TextView) convertView.findViewById(R.id.tv_item);
		iv_item.setImageUrl(arr.get(position).getImage(), imgLoader);
		tv_item.setText(arr.get(position).getName());
		return convertView;
	}
}
