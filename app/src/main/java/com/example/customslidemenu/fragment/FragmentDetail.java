package com.example.customslidemenu.fragment;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.example.customslidemenu.R;
import com.example.customslidemenu.Interface.ICallBack;
import com.example.customslidemenu.adapter.ListFilmAdapter;
import com.example.customslidemenu.asynctask.ConnectServer;
import com.example.customslidemenu.models.CategoryItem;
import com.example.customslidemenu.models.DetailFilmItem;

import MyJackson.Controller.ParseObjectJson;
import MyJackson.Interface.CallbackParseObjectjson;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class FragmentDetail extends Fragment implements ICallBack, CallbackParseObjectjson<CategoryItem>{
	int id;
	static String url="http://phimtructuyen2014.tk/MediaApi/GetClips?categoryId=";
	List<DetailFilmItem> arr;
	ListFilmAdapter adapter;
	ListView lv_listfilm;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		id = getArguments().getInt("Id");   
		Log.e("Id:", id+"");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.listfilm,container,false);
		arr = new ArrayList<DetailFilmItem>();
		lv_listfilm = (ListView) view.findViewById(R.id.lv_listfilm);
		adapter = new ListFilmAdapter(getActivity(), R.layout.listfilm_item, arr);
		lv_listfilm.setAdapter(adapter);
		Log.e("CHeck", url+Integer.toString(id)+"");
		(new ConnectServer(FragmentDetail.this)).execute(url+Integer.toString(id));
		initListener();
		return view;
	}
	public void initListener(){
		lv_listfilm.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Bundle bundle = new Bundle();
				bundle.putInt("Id", arr.get(position).getId());
				FragmentWatchFilm fragWatchFilm = new FragmentWatchFilm();
				fragWatchFilm.setArguments(bundle);
				FragmentManager fm = getFragmentManager();
				fm.beginTransaction().replace(R.id.fl_container, fragWatchFilm).commit();
			}
		});
	}
	@Override
	public void callBack(InputStream inputStream) {
		// TODO Auto-generated method stub
		ParseObjectJson<CategoryItem> item = new ParseObjectJson<CategoryItem>(FragmentDetail.this, CategoryItem.class);
		item.execute(inputStream);
	}

	@Override
	public void callBack(CategoryItem arg0) {
		Log.e("abcdefgh", arg0.getArr().size()+"");
		List<DetailFilmItem> list = new ArrayList<DetailFilmItem>();
		list = arg0.getArr();
		for(int i = 0; i < list.size();i++){
			arr.add(list.get(i));
		}
		adapter.notifyDataSetChanged();
	}

}
