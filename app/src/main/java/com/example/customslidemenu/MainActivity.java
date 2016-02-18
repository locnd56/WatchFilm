package com.example.customslidemenu;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.example.customslidemenu.Interface.ICallBack;
import com.example.customslidemenu.adapter.SlideMenuAdapter;
import com.example.customslidemenu.asynctask.ConnectServer;
import com.example.customslidemenu.fragment.FragmentDetail;
import com.example.customslidemenu.models.SlideMenuItem;

import MyJackson.Controller.ParseArrayJson;
import MyJackson.Interface.CallbackParseArrayJson;
import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends Activity implements ICallBack,
		CallbackParseArrayJson<SlideMenuItem> {
	ListView lv_slidemenu;
	ArrayList<SlideMenuItem> arrSlideMenu;
	ImageView iv_home;
	DrawerLayout drawerLayout;
	SlideMenuAdapter adapter;
	ConnectServer connectServer;
	FragmentManager fm;
	ParseArrayJson<SlideMenuItem> json;
	public String url = "http://phimtructuyen2014.tk/MediaApi/GetCategories";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		connectServer = new ConnectServer(MainActivity.this);
		fm = getFragmentManager();
		connectServer.execute(url);
		initListener();
	}

	public void initView() {
		connectServer = new ConnectServer(MainActivity.this);
		lv_slidemenu = (ListView) findViewById(R.id.lv_slidemenu);
		iv_home = (ImageView) findViewById(R.id.iv_actionbar_home);
		drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
		arrSlideMenu = new ArrayList<SlideMenuItem>();
		adapter = new SlideMenuAdapter(this,
				android.R.layout.simple_list_item_1, arrSlideMenu);
		lv_slidemenu.setAdapter(adapter);
	}

	public void initListener() {
		iv_home.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				drawerLayout.openDrawer(Gravity.LEFT);
			}
		});
		lv_slidemenu.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Bundle bundle = new Bundle();
				bundle.putInt("Id", arrSlideMenu.get(position).getId());
				FragmentDetail fragDetail = new FragmentDetail();
				fragDetail.setArguments(bundle);
				fm.beginTransaction().replace(R.id.fl_container, fragDetail)
						.commit();
				drawerLayout.closeDrawer(Gravity.LEFT);
			}
		});
	}

	@Override
	public void callBack(List<SlideMenuItem> arr) {
		// TODO Auto-generated method stub
		for (int i = 0; i < arr.size(); i++) {
			arrSlideMenu.add(arr.get(i));
		}
		Log.e("Idballab", arr.get(0).getId()+"");
		adapter.notifyDataSetChanged();
	}

	@Override
	public void callBack(InputStream inputStream) {
		json = new ParseArrayJson<SlideMenuItem>(MainActivity.this,
				SlideMenuItem.class);
		json.execute(inputStream);
		Log.e("input:", "" + inputStream.toString());
	}
}
