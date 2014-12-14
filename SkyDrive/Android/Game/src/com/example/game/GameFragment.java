package com.example.game;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GameFragment extends Fragment implements OnTouchListener {
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
	public View view;
	private SoundPool collisionSound;
	private SoundPool fallingSound;
	private SoundPool winning;
	private int soundId,soundIdFall,winningId;

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
						collisionSound.play(soundId, 1, 1, 0, 0, 1);
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
						float j = gameView.getBack().getPosition().x - 15;
						gameView.getBack().setPosition(new PointF(j, 0));
						float i = gameView.getObstacle().getPosition().x - 15;
						gameView.getObstacle().setPosition(
								new PointF(i, gameView.getObstacle()
										.getPosition().y));
						for (Rect r : Obstacle.rectangles) {
							r.set(r.left - 15, r.top, r.right - 15, r.bottom);
						}

					}
				}
				if (isFalling) {
					float i = gameView.getBird().getPosition().y + 15;
					gameView.getBird().setPosition(
							new PointF(0.130208333f * width, i));

					if (gameView.getBird().getPosition().y > height) {
						gameOver();
						flag = true;
						fallingSound.play(soundIdFall, 1, 1, 0, 0, 1);
					}
				}
				handler.postDelayed(this, 1);
			}
		}
	}

	@Override
	public void onStop() {
		super.onStop();
		counter = 0;

	}

	@Override
	public void onPause() {
		super.onPause();
		media.pause();

	}

	@SuppressWarnings("deprecation")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.activity_game, container, false);
		runn = new MyRunnable();
		media = MediaPlayer.create(view.getContext(), R.raw.theme);
		collisionSound = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
		fallingSound = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
		winning = new SoundPool(5,AudioManager.STREAM_MUSIC,0);
		soundId = collisionSound.load(getActivity(), R.raw.collision, 1);
		soundIdFall = fallingSound.load(getActivity(), R.raw.falling, 1);
		winningId = winning.load(getActivity(), R.raw.winning, 1);
		tx = (TextView) view.findViewById(R.id.textView1);
		WindowManager wm = (WindowManager) super.getActivity()
				.getSystemService(Context.WINDOW_SERVICE);
		display = wm.getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		width = size.x;
		height = size.y;

		gameView = (GameView) view.findViewById(R.id.gameView1);
		frameLayout = (FrameLayout) view.findViewById(R.id.layout);

		if (savedInstanceState != null) {
			position = savedInstanceState.getInt("pos");
			media.seekTo(position);

		} else {
			media.start();
		}

		frameLayout.setOnTouchListener(this);
		return view;

	}

	@Override
	public void onResume() {
		super.onResume();
		if (isFalling) {
			media.start();
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (counter == 0) {
			isFalling = true;
			handler.post(runn);
			ImageView img = (ImageView) view.findViewById(R.id.temp);
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

	public void gameOver() {
		Toast.makeText(getActivity(), "Game Over!", Toast.LENGTH_SHORT).show();
		

		SharedPreferences sharedPref = getActivity().getPreferences(
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putInt("score", gameView.getObstacle().getCounter());
		editor.commit();

		Login_Fragment lf = new Login_Fragment();
		FragmentManager fm1 = getFragmentManager();
		FragmentTransaction ft1 = fm1.beginTransaction();
		ft1.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

		ft1.replace(R.id.container, lf).commit();
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
		Toast.makeText(getActivity(), "You win", Toast.LENGTH_SHORT).show();
		winning.play(winningId, 1, 1, 0, 0, 1);
		SharedPreferences sharedPref = getActivity().getPreferences(
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putInt("score", gameView.getObstacle().getCounter());
		editor.commit();

		Login_Fragment lf = new Login_Fragment();
		FragmentManager fm1 = getFragmentManager();
		FragmentTransaction ft1 = fm1.beginTransaction();
		ft1.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		ft1.replace(R.id.container, lf).commit();
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
