package com.example.helpludogorets;

import java.io.File;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.VideoView;

public class MainActivity extends Activity {
	private static int counter = 0;
	VideoView vw;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		vw = (VideoView) findViewById(R.id.video);
		Uri u = Uri.fromFile(new File(
				Environment.getExternalStorageDirectory(), "ron.mp4"));
		vw.setVideoURI(u);
		final ImageView imv = (ImageView) findViewById(R.id.play);
		final ImageView imv_next = (ImageView) findViewById(R.id.next);
		final ImageView imv_prev = (ImageView) findViewById(R.id.prev);
		int pos = 0;
		if (savedInstanceState != null) {
	        pos = savedInstanceState.getInt("pos");
	        vw.seekTo(pos);
	        vw.pause();
	        imv.setImageDrawable(getResources().getDrawable(
					R.drawable.play));
	    }
		imv.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (counter == 0) {
					
					vw.start();
					imv.setImageDrawable(getResources().getDrawable(
							R.drawable.pause));
					counter++;

				} else {
					counter--;
					imv.setImageDrawable(getResources().getDrawable(
							R.drawable.play));
					vw.pause();
				}
			}
		});
		imv_prev.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				vw.pause();
				vw.seekTo(vw.getCurrentPosition() - 3000);
				vw.start();
			}
		});
		imv_next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				vw.pause();
				vw.seekTo(vw.getCurrentPosition() + 3000);
				vw.start();
			}
		});
		vw.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				// TODO Auto-generated method stub
				vw.seekTo(0);
				imv.setImageDrawable(getResources()
						.getDrawable(R.drawable.play));
			}
		});
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		if (vw.isPlaying())
			outState.putInt("pos", vw.getCurrentPosition());
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
