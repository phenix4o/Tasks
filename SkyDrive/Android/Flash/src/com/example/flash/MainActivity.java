package com.example.flash;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.util.Log;

@SuppressWarnings("deprecation")
public class MainActivity extends Activity {

	private boolean isLighOn = false;

	private Camera camera;
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		final Parameters p = camera.getParameters();
		p.setFlashMode(Parameters.FLASH_MODE_TORCH);

		camera.setParameters(p);
		camera.startPreview();
		isLighOn = true;

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		final Parameters p = camera.getParameters();
		p.setFlashMode(Parameters.FLASH_MODE_OFF);
		camera.setParameters(p);
		camera.stopPreview();
		isLighOn = false;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Context context = this;
		PackageManager pm = context.getPackageManager();
		if (!pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
			Log.e("err", "Device has no camera!");
			return;
		}

		camera = Camera.open();
		final Parameters p = camera.getParameters();

		if (isLighOn) {

			Log.i("info", "torch is turn off!");

			p.setFlashMode(Parameters.FLASH_MODE_OFF);
			camera.setParameters(p);
			camera.stopPreview();
			isLighOn = false;

		} else {

			Log.i("info", "torch is turn on!");

			p.setFlashMode(Parameters.FLASH_MODE_TORCH);

			camera.setParameters(p);
			camera.startPreview();
			isLighOn = true;

		}

	}

}
