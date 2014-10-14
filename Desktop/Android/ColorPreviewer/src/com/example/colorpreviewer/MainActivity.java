package com.example.colorpreviewer;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final EditText et = (EditText) findViewById(R.id.editText1);
		Button btn1 = (Button)findViewById(R.id.btn1); 
		btn1.setOnClickListener(new OnClickListener() {
		final RelativeLayout lr = (RelativeLayout)findViewById(R.id.RL);
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(et.getText().toString().trim().length()!= 0){
				lr.setBackgroundColor(Color.parseColor(et.getText().toString()));
				}
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
