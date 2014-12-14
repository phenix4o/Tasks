package com.example.intentsconfigchanges;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification.Action;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;

@SuppressLint("NewApi")
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// context,klas kym koito iskam da otida, ekspliciten

		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);

		Intent intent2 = new Intent();
		intent2.setAction(Intent.ACTION_CALL);
		intent2.putExtra("bla", 2);
		// intent2.setData(new Uri("sada"));
		// uri.fromfile
		startActivity(intent2);
		startActivityForResult(intent2, 12);

		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (requestCode == 12) {
			setResult(RESULT_OK);
	//		data.putExtras(extras);
			finish();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState,
			PersistableBundle persistentState) {
		// persistance storage onPause();
		super.onRestoreInstanceState(savedInstanceState, persistentState);
	}

}
