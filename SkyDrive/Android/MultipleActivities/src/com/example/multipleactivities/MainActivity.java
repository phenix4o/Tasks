package com.example.multipleactivities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button btn1 = (Button) findViewById(R.id.button1);
		Button btn2 = (Button) findViewById(R.id.button2);
		Button btn3 = (Button) findViewById(R.id.button3);
		final EditText tx = (EditText) findViewById(R.id.editText1);
		btn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent callIntent = new Intent(Intent.ACTION_CALL);
				String tel = tx.getText().toString();

				if (PhoneNumberUtils.isGlobalPhoneNumber(tel)) {
					callIntent.setData(Uri.parse("tel:" + tel));
					startActivity(callIntent);
				}
			}
		});
		btn2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent siteIntent = new Intent(Intent.ACTION_VIEW);
				String site = tx.getText().toString();

				if (android.util.Patterns.WEB_URL.matcher(site).matches()) {

					siteIntent.setData(Uri.parse("https://" + site));
					startActivity(siteIntent);
				}
			}
		});
		btn3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String alarm = tx.getText().toString();

				if (alarm.matches("[0-9][0-9]:[0-9][0-9]")) {
					String[] hour = alarm.split(":");
					int c = Integer.valueOf(hour[0]);
					int d = Integer.valueOf(hour[1]);
					Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
					i.putExtra(AlarmClock.EXTRA_MESSAGE, "New Alarm");
					i.putExtra(AlarmClock.EXTRA_HOUR, c);
					i.putExtra(AlarmClock.EXTRA_MINUTES, d);
					startActivity(i);

				}

			}
		});
	}

}
