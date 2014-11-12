package com.example.game;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends Activity implements OnTouchListener {
	private GameView gameView;
	private FrameLayout frameLayout;
	private int position;
	private boolean touchEvent = true;
	private Display display;
	private boolean isFalling = false;
	int counter = 0;
	private int width, height;
	private Handler handler = new Handler();
	private MediaPlayer media;
	private boolean gameOver = false;
	private TextView tx;
	private MyRunnable runn;
	private boolean flag;

	class MyRunnable implements Runnable {

		@Override
		public void run() {
			if (!flag) {
				tx.invalidate();
				tx.setText(String.valueOf(gameView.getObstacle().getCounter()));
				long c = System.currentTimeMillis();
				Log.d("str", String.valueOf(c));
				gameView.invalidate();
				if (Integer.valueOf(tx.getText().toString()) == 100) {
					youWin();
					flag = true;
				}
				for (int i = 0; i < gameView.getObstacle().getRectangles()
						.size(); i++) {
					if (gameView
							.getObstacle()
							.getRectangles()
							.get(i)
							.contains((int) gameView.getBird().getPosition().x,
									(int) gameView.getBird().getPosition().y)) {
						gameOver();
						flag = true;
						break;
					}

				}
				if ((int) Math.floor((Math
						.abs(gameView.getBack().getPosition().x) / width)) >= gameView
						.getRepBack().getI()
						&& !gameOver) {
					gameView.getRepBack().setI(
							(int) Math.ceil((Math.abs(gameView.getRepBack()
									.getPosition().x) / width)));
				}
				if (Math.abs(gameView.getBack().getPosition().x) >= width
						&& !gameOver) {
					gameView.getBack().setPosition(new PointF(0, 0));
				} else {
					if (!gameOver) {
						float j = gameView.getBack().getPosition().x - 10;
						gameView.getBack().setPosition(new PointF(j, 0));
						float i = gameView.getObstacle().getPosition().x - 10;
						gameView.getObstacle().setPosition(
								new PointF(i, gameView.getObstacle()
										.getPosition().y));
						for (Rect r : Obstacle.rectangles) {
							r.set(r.left - 10, r.top, r.right - 10, r.bottom);
						}

					}
				}
				if (isFalling) {
					float i = gameView.getBird().getPosition().y + 10;
					gameView.getBird().setPosition(
							new PointF(0.130208333f * width, i));

					if (gameView.getBird().getPosition().y > height) {
						gameOver();
						flag = true;
					}
				}
				handler.postDelayed(this, 1);
				Log.d("str", String.valueOf(System.currentTimeMillis() - c));
			}
		}

	}

	@Override
	protected void onStop() {
		super.onStop();
		counter = 0;

	}

	@Override
	protected void onPause() {
		super.onPause();
		media.pause();

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		runn = new MyRunnable();
		media = MediaPlayer.create(this, R.raw.prey_overture);
		tx = (TextView) findViewById(R.id.textView1);
		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		display = wm.getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		width = size.x;
		height = size.y;
		goImmersiveMode();
		gameView = (GameView) findViewById(R.id.gameView1);
		frameLayout = (FrameLayout) findViewById(R.id.layout);

		if (savedInstanceState != null) {
			position = savedInstanceState.getInt("pos");
			media.seekTo(position);

		} else {
			media.start();
		}

		frameLayout.setOnTouchListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (isFalling) {
			media.start();
		}
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus) {
			goImmersiveMode();
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (counter == 0) {
			isFalling = true;
			handler.post(runn);
			ImageView img = (ImageView) findViewById(R.id.temp);
			img.setVisibility(View.GONE);
			counter++;
		}
		if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
			isFalling = false;
			float y = gameView.getBird().getPosition().y;
			y = y - 0.15f * height;
			float i = 0;
			while (i < y) {
				gameView.getBird().setPosition(
						new PointF(gameView.getBird().getPosition().x, i));
				gameView.invalidate();
				i++;
			}
		} else if (event.getActionMasked() == MotionEvent.ACTION_UP) {
			isFalling = true;
		}
		return touchEvent;
	}

	private void goImmersiveMode() {
		if (android.os.Build.VERSION.SDK_INT >= 17) {
			getWindow().getDecorView().setSystemUiVisibility(
					View.SYSTEM_UI_FLAG_LAYOUT_STABLE
							| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
							| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
							| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
							| View.SYSTEM_UI_FLAG_FULLSCREEN
							| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
		}
	}

	public void gameOver() {
		Toast.makeText(GameActivity.this, "Game Over!", Toast.LENGTH_SHORT)
				.show();

		isFalling = false;
		gameOver = true;
		media.stop();
		frameLayout.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return false;
			}
		});

	}

	private void youWin() {
		// TODO Auto-generated method stub
		Toast.makeText(GameActivity.this, "You win", Toast.LENGTH_SHORT).show();

		isFalling = false;
		gameOver = true;
		media.stop();
		frameLayout.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return false;
			}
		});
	}
}
