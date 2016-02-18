package com.example.customslidemenu.fragment;

import java.io.InputStream;

import MyJackson.Controller.ParseObjectJson;
import MyJackson.Interface.CallbackParseObjectjson;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.customslidemenu.R;
import com.example.customslidemenu.Interface.ICallBack;
import com.example.customslidemenu.asynctask.ConnectServer;
import com.example.customslidemenu.cacheimagevolley.AppController;
import com.example.customslidemenu.models.WatchFilmItem;

public class FragmentWatchFilm extends Fragment implements ICallBack, CallbackParseObjectjson<WatchFilmItem>{
	int id;
	static String url = "http://phimtructuyen2014.tk/MediaApi/GetClip?id=";
	ImageView iv_back;
	NetworkImageView iv_film;
	ImageLoader il_film;
	TextView tv_name;
	TextView tv_desc;
	Button btn_watchfilm;
	WatchFilmItem watchFilmItem;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		id = getArguments().getInt("Id");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.watchmovie, container, false);
		initView(view);
		(new ConnectServer(FragmentWatchFilm.this)).execute(url+Integer.toString(id));
		initListener();
		return view;
	}
	public void initView(View v){
		iv_film =  (NetworkImageView) v.findViewById(R.id.iv_watchfilm_image);
		il_film = AppController.getInstance().getImageLoader();
		tv_name = (TextView) v.findViewById(R.id.tv_watchfilm_name);
		tv_desc = (TextView) v.findViewById(R.id.tv_watchfilm_desc);
		btn_watchfilm = (Button) v.findViewById(R.id.btn_watchfilm_watchmovie);
	}
	public void initListener(){
		btn_watchfilm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Bundle bundle = new Bundle();
				bundle.putString("StreamPath", watchFilmItem.getFilePathMovie().toString());
				Log.e("streamPath:", watchFilmItem.getFilePathMovie().toString());
				FragmentVideo fragVideo = new FragmentVideo();
				fragVideo.setArguments(bundle);
				FragmentManager fm = getFragmentManager();
				fm.beginTransaction().replace(R.id.fl_container, fragVideo).commit();
			}
		});
	}
	@Override
	public void callBack(InputStream inputStream) {
		ParseObjectJson<WatchFilmItem> watchFilmItem = new ParseObjectJson<WatchFilmItem>(FragmentWatchFilm.this, WatchFilmItem.class);
		watchFilmItem.execute(inputStream);
	}

	@Override
	public void callBack(WatchFilmItem item) {
		watchFilmItem = item;
		iv_film.setImageUrl(item.getImage(), il_film);
		tv_name.setText(item.getTitle().toString());
		tv_desc.setText(item.getDesc().toString());
		
	}
	
}
