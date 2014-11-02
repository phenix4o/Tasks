package com.example.game;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.PointF;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnSystemUiVisibilityChangeListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

@SuppressLint({ "InlinedApi", "ShowToast", "ClickableViewAccessibility" })
public class GameActivity extends Activity {
	private MediaPlayer mPl;
	private int position;
	private boolean touch = true;
	private boolean isTrue = false;
	Thread thread;

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mPl.pause();

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mPl.release();
	}

	@SuppressLint("InlinedApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_game);
		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		final Display display = wm.getDefaultDisplay();
		final GameView gv = (GameView) findViewById(R.id.gameView1);

		final LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
		mPl = MediaPlayer.create(this, R.raw.prey_overture);

		int pos = 0;
		if (savedInstanceState != null) {
			pos = savedInstanceState.getInt("pos");
			mPl.seekTo(pos);
			mPl.pause();
		}
		mPl.start();
		mPl.setLooping(true);

		if (android.os.Build.VERSION.SDK_INT >= 19) {
			getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(
					new OnSystemUiVisibilityChangeListener() {
						@Override
						public void onSystemUiVisibilityChange(int visibility) {
							if (visibility == 0) {
								getWindow()
										.getDecorView()
										.setSystemUiVisibility(
												View.SYSTEM_UI_FLAG_LAYOUT_STABLE
														| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
														| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
														| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
														| View.SYSTEM_UI_FLAG_FULLSCREEN
														| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
							}
						}
					});

			thread = new Thread() {
				@SuppressWarnings("deprecation")
				@Override
				public void run() {

					try {

						while (GameView.birdie.getPosition().y < (display
								.getHeight() * 0.82)) {
							Log.d("log", String.valueOf(GameView.birdie
									.getPosition().y));
							Thread.sleep(16);
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									gv.invalidate();

									if ((int) Math
											.floor((Math.abs(GameView.back
													.getPosition().x) / display
													.getWidth())) >= GameView.rep
											.getI()) {
										GameView.rep
												.setI((int) Math.ceil((Math.abs(GameView.back
														.getPosition().x) / display
														.getWidth())));

									}
									if (Math.abs(GameView.back.getPosition().x) >= 1920) {
										GameView.back.setPosition(new PointF(0,
												0));
									} else {
										float j = GameView.back.getPosition().x - 10;
										GameView.back.setPosition(new PointF(j,
												0));
									}

									if (isTrue) {
										float i = GameView.birdie.getPosition().y + 10;

										GameView.birdie.setPosition(new PointF(
												200, i));
										if (GameView.birdie.getPosition().y > display
												.getHeight() * 0.82) {
											Toast.makeText(
													getApplicationContext(),
													"You lose", 10000).show();
											mPl.stop();
											layout.setOnTouchListener(new OnTouchListener() {

												@Override
												public boolean onTouch(View v,
														MotionEvent event) {
													// TODO Auto-generated
													// method stub
													return false;
												}
											});
										}

										return;
									}

								}
							});

						}
						return;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				};
			};

			isTrue = true;
			thread.start();
			Log.d("str", String.valueOf(GameView.birdie.getPosition().x));

		}
		layout.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
					isTrue = false;
					Log.d("touch", "touch");
					float y = GameView.birdie.getPosition().y;
					y = y - 100;
					float i = 0;
					while (i < y) {

						GameView.birdie.setPosition(new PointF(GameView.birdie
								.getPosition().x, i));
						gv.invalidate();
						i++;
					}

				} else if (event.getActionMasked() == MotionEvent.ACTION_UP) {
					isTrue = true;
				}
				return touch;
			}
		});

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mPl.seekTo(position);
		mPl.start();
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus) {
			getWindow().getDecorView().setSystemUiVisibility(
					View.SYSTEM_UI_FLAG_LAYOUT_STABLE
							| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
							| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
							| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
							| View.SYSTEM_UI_FLAG_FULLSCREEN
							| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		position = mPl.getCurrentPosition();
		if (mPl.isPlaying())
			outState.putInt("pos", position);
	}

}
