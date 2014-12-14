package com.example.easy_flags;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class FlagsEasyActivity extends Activity {

	int[] rainbow;
	private static int counter1;
	private static int counter2;
	private static int counter3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.flags_easy);
		final View vw = (View) findViewById(R.id.viewFirst);
		final View vw2 = (View) findViewById(R.id.viewSecond);
		final View vw3 = (View) findViewById(R.id.viewThird);
		rainbow = getResources().getIntArray(R.array.array);
		vw.setBackgroundColor(rainbow[counter1]);
		vw2.setBackgroundColor(rainbow[counter2]);
		vw3.setBackgroundColor(rainbow[counter3]);
		vw.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (counter1 < rainbow.length - 1) {
					counter1++;
					vw.setBackgroundColor(rainbow[counter1]);
				} else {
					counter1 = 0;
					vw.setBackgroundColor(rainbow[counter1]);
				}
			}
		});

		vw2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (counter2 < rainbow.length - 1) {
					counter2++;
					vw2.setBackgroundColor(rainbow[counter2]);
				} else {
					counter2 = 0;
					vw2.setBackgroundColor(rainbow[counter2]);
				}
			}
		});
		vw3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (counter3 < rainbow.length - 1) {
					counter3++;
					vw3.setBackgroundColor(rainbow[counter3]);
				} else {
					counter3 = 0;
					vw3.setBackgroundColor(rainbow[counter3]);
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.flags_easy, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
