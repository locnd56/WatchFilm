package com.example.customslidemenu.fragment;

import com.example.customslidemenu.R;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

public class FragmentVideo extends Fragment {
	String streamPath = null;
	VideoView vv_video;
	ProgressDialog progressDialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		streamPath = getArguments().getString("StreamPath");
		// Log.e("StreamPath", streamPath);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.videoview, container, false);
		progressDialog = new ProgressDialog(getActivity());
		progressDialog.setTitle("JavaCodeGeeks Android Video View Example");
		progressDialog.setMessage("Loading...");
		progressDialog.setCancelable(false);
		progressDialog.show();
		vv_video = (VideoView) view.findViewById(R.id.vv_video);
		MediaController mediacontroller = new MediaController(getActivity());
		mediacontroller.setAnchorView(vv_video);
		Uri video = Uri.parse(streamPath);
		vv_video.setMediaController(mediacontroller);
		vv_video.setVideoURI(video);
		vv_video.requestFocus();
		vv_video.setOnPreparedListener(new OnPreparedListener() {
			@Override
			public void onPrepared(MediaPlayer mp) {
				// TODO Auto-generated method stub
				progressDialog.hide();
				vv_video.start();
			}
		});
		return view;
	}

}
