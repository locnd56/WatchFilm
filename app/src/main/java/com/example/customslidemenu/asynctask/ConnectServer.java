package com.example.customslidemenu.asynctask;

import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

import com.example.customslidemenu.Interface.ICallBack;

public class ConnectServer extends AsyncTask<String, Void, InputStream> {
	ICallBack icallback;
	InputStream inputStream;

	public ConnectServer(ICallBack icallback) {
		this.icallback = icallback;
		inputStream = null;
	}

	public InputStream makeServiceCall(String url) {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpResponse httpResponse = null;
			httpResponse = httpClient.execute(new HttpPost(url));
			inputStream = httpResponse.getEntity().getContent();
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return inputStream;
	}

	@Override
	protected InputStream doInBackground(String... params) {
		// inputStream = makeServiceCall(params[0]);
		return makeServiceCall(params[0]);
	}

	@Override
	protected void onPostExecute(InputStream result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		icallback.callBack(result);
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();

	}

}
