package com.example.screeslidepagefragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ScreenSlidePageFragment extends Fragment {


	private Uri uri;

	public ScreenSlidePageFragment(Uri uri) {
		this.uri = uri;

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup rootView = (ViewGroup) inflater.inflate(
				R.layout.fragment_screen_slide_page, container, false);
		ImageView img = (ImageView) rootView.findViewById(R.id.img);

		img.setImageURI(uri);

		return rootView;
	}

}
