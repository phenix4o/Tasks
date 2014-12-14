package com.example.gestureimageview;

import java.util.ArrayList;
import java.util.List;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

@SuppressLint("FloatMath")
public class MainActivity extends Activity {
	private View v;
	private ImageView img;
	float historyValueX;
	float historyValueY;
	float history_angle;
	float history_dist;
	float history_Scale;
	List<Animator> listOfAnimator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		v = (View) findViewById(R.id.root);
		img = (ImageView) findViewById(R.id.imageView1);
		final Button save = (Button) findViewById(R.id.button1);
		final Button play = (Button) findViewById(R.id.button2);
		listOfAnimator = new ArrayList<Animator>();
		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				float scaleXParameter = img.getScaleX();
				float scaleYParameter = img.getScaleY();
				float rotation = img.getRotation();
				float coordinateX = img.getX();
				float coordinateY = img.getY();
				PropertyValuesHolder translationX = PropertyValuesHolder
						.ofFloat("x", coordinateX);
				PropertyValuesHolder translationY = PropertyValuesHolder
						.ofFloat("y", coordinateY);
				PropertyValuesHolder rotate = PropertyValuesHolder.ofFloat(
						"rotation", rotation);
				PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat(
						"scaleX", scaleXParameter);
				PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat(
						"scaleY", scaleYParameter);
				ObjectAnimator anims = ObjectAnimator
						.ofPropertyValuesHolder(img, translationX,
								translationY, rotate, scaleX, scaleY);
				anims.setInterpolator(new AccelerateDecelerateInterpolator());
				listOfAnimator.add(anims);
			}
		});
		play.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				AnimatorSet as = new AnimatorSet();
				as.playSequentially(listOfAnimator);
				as.start();
			}
		});
		v.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getPointerCount() == 1) {
					if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
						historyValueX = event.getX(0);
						historyValueY = event.getY(0);

					} else if (event.getActionMasked() == MotionEvent.ACTION_MOVE) {

						float deltaX = event.getX(0) - historyValueX;
						float deltaY = event.getY(0) - historyValueY;

						img.setTranslationX(img.getTranslationX() + deltaX);
						img.setTranslationY(img.getTranslationY() + deltaY);
						historyValueX = event.getX(0);
						historyValueY = event.getY(0);
					}

				} else if (event.getPointerCount() == 2) {

					if (event.getActionMasked() == MotionEvent.ACTION_POINTER_DOWN
							|| event.getActionMasked() == MotionEvent.ACTION_DOWN) {
						float diff_y = event.getY(0) - event.getY(1);
						float diff_x = event.getX(0) - event.getX(1);
						history_angle = (float) Math.toDegrees(Math.atan2(
								diff_y, diff_x));

						history_dist = FloatMath.sqrt(diff_x * diff_x + diff_y
								* diff_y);

					} else if (event.getActionMasked() == MotionEvent.ACTION_MOVE) {

						float diff_y = event.getY(0) - event.getY(1);
						float diff_x = event.getX(0) - event.getX(1);
						float angle = (float) Math.toDegrees(Math.atan2(diff_y,
								diff_x));
						float dist_angle = angle - history_angle;
						img.setRotation(img.getRotation() + dist_angle);
						history_angle = angle;

						float distCurrent = FloatMath.sqrt(diff_x * diff_x
								+ diff_y * diff_y);

						float curScale = distCurrent / history_dist;

						img.setScaleX(img.getScaleX() * curScale);
						img.setScaleY(img.getScaleY() * curScale);
						history_dist = distCurrent;
					}
				}
				return true;
			}
		});

	}
}