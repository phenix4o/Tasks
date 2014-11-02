package com.example.swipeandzoom;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

@SuppressLint({ "Recycle", "ClickableViewAccessibility" }) public class MainActivity extends Activity {
	private static final float ZOOM_IN = 2f;
	private static final float ZOOM_OUT = 1f;
	private static int counter = 0;
	private static int doubleTapCounter = 0;
	List<Drawable> galleryElements = new ArrayList<Drawable>();
	ImageView img;
	TextView tx;
	GestureDetector myGestureDetector;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TypedArray type = getResources().obtainTypedArray(R.array.array);
		for (int j = 0; j < type.length(); j++) {
			galleryElements.add(type.getDrawable(j));
		}
		RelativeLayout root = (RelativeLayout) findViewById(R.id.root);
		root.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				myGestureDetector.onTouchEvent(event);
				
				return true;
			}
		});
		tx = (TextView) findViewById(R.id.textView1);
		img = (ImageView) findViewById(R.id.imageView1);
		tx.setText(String.valueOf(counter + 1) + "/"
				+ String.valueOf(galleryElements.size()));

		myGestureDetector = new GestureDetector(this,
				new GestureDetector.SimpleOnGestureListener() {

					@Override
					public boolean onSingleTapUp(MotionEvent e) {
						// TODO Auto-generated method stub
						return false;
					}

					@Override
					public void onShowPress(MotionEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public boolean onScroll(MotionEvent e1, MotionEvent e2,
							float distanceX, float distanceY) {
						// TODO Auto-generated method stub
						return false;
					}

					@Override
					public void onLongPress(MotionEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public boolean onFling(MotionEvent e1, MotionEvent e2,
							float velocityX, float velocityY) {
						// TODO Auto-generated method stub
						float diff = e2.getX() - e1.getX();
						if (diff < 0) {
							if (counter < galleryElements.size() - 1) {
								counter++;
								img.setImageDrawable(galleryElements
										.get(counter));
								tx.setText(String.valueOf(counter + 1)
										+ "/"
										+ String.valueOf(galleryElements.size()));
							} else {
								counter = 0;
								img.setImageDrawable(galleryElements
										.get(counter));
								tx.setText(String.valueOf(counter + 1)
										+ "/"
										+ String.valueOf(galleryElements.size()));
							}
						} else {
							if (counter > 0) {
								counter--;
								img.setImageDrawable(galleryElements
										.get(counter));
								tx.setText(String.valueOf(counter + 1)
										+ "/"
										+ String.valueOf(galleryElements.size()));
							} else {
								counter = galleryElements.size() - 1;
								img.setImageDrawable(galleryElements
										.get(counter));
								tx.setText(String.valueOf(counter + 1)
										+ "/"
										+ String.valueOf(galleryElements.size()));
							}
						}

						return true;
					}

					@Override
					public boolean onDoubleTap(MotionEvent e) {
						// TODO Auto-generated method stub
						if (doubleTapCounter == 0) {
							img.setScaleX(ZOOM_IN);
							img.setScaleY(ZOOM_IN);
							doubleTapCounter++;
						} else {
							img.setScaleX(ZOOM_OUT);
							img.setScaleY(ZOOM_OUT);
							doubleTapCounter--;
						}
						return true;

					}

					@Override
					public boolean onDown(MotionEvent e) {
						// TODO Auto-generated method stub
						return false;
					}
				});
	}
}
