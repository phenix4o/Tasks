package com.example.splashscreen;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {
	private static int SPLASH_TIME_OUT = 3000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		getActionBar().hide();
		SharedPreferences pref = getPreferences(MODE_WORLD_READABLE);
		boolean haveSplashed = pref.getBoolean("splash", false);
		if (!haveSplashed) {
			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() {

					Intent i = new Intent(SplashActivity.this,
							MainActivity.class);
					SharedPreferences pref = getPreferences(MODE_WORLD_WRITEABLE);
					Editor edit = pref.edit();
					edit.putBoolean("splash", true);
					edit.commit();
					startActivity(i);

					finish();
				}
			}, SPLASH_TIME_OUT);
		} else {
			Intent i = new Intent(SplashActivity.this, MainActivity.class);
			startActivity(i);
			finish();
		}
	}

}
