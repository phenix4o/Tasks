package com.example.screeslidepagefragment;

import java.io.File;
import java.io.FileFilter;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

public class ScreenSlidePagerActivity extends FragmentActivity {

	private ViewPager mPager;
	private PagerAdapter mPagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen_slide);

		mPager = (ViewPager) findViewById(R.id.pager);
		mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
		mPager.setAdapter(mPagerAdapter);	
	}

	@Override
	public void onBackPressed() {
		if (mPager.getCurrentItem() == 0) {
			super.onBackPressed();
		} else {
			mPager.setCurrentItem(mPager.getCurrentItem() - 1);
		}
	}

	private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
		File filesPhotos = Environment
				.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		private int counter = filesPhotos.listFiles(new FileFilter() {

			@Override
			public boolean accept(File pathname) {
	
				return !pathname.isDirectory();
			}
		}).length;
		
		
		public ScreenSlidePagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			Uri[] uri = new Uri[counter];
			for (int i = 0; i < counter; i++) {
				uri[i] = Uri.fromFile(filesPhotos.listFiles()[i]);
			}
			return new ScreenSlidePageFragment(uri[position]);
		}

		@Override
		public int getCount() {

			return counter;
		}

	
	}
}