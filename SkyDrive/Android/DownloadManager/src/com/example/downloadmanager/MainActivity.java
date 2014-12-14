package com.example.downloadmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("bc", Environment.getExternalStorageDirectory().toString());
        Button btn = (Button)findViewById(R.id.button1);
        Button btn1 = (Button)findViewById(R.id.button2);
        Button btn2 = (Button)findViewById(R.id.button3);
        Button btn3 = (Button)findViewById(R.id.button4);
        btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,DownloadService.class);
				intent.putExtra("extra", 1);
				startService(intent);
			}
		});
        btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,DownloadService.class);
				intent.putExtra("extra", 2);
				startService(intent);
				
			}
		});
        btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,DownloadService.class);
				intent.putExtra("extra", 3);
				startService(intent);
				
			}
		});
        btn3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,DownloadService.class);
				intent.putExtra("extra", 4);
				startService(intent);
				
			}
		});
    }
}
