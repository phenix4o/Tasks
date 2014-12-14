package com.example.puzzlesec;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint({ "ClickableViewAccessibility", "ShowToast" })
public class MainActivity extends Activity {
	private static List<ImageView> views;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		int titleId = getResources().getIdentifier("action_bar_title", "id",
				"android");
		TextView abTitle = (TextView) findViewById(titleId);
		abTitle.setTextColor(Color.parseColor("#fff6fb"));
		final GridLayout grid = (GridLayout) findViewById(R.id.grid);
		grid.setBackgroundColor(Color.BLACK);
		final List<Drawable> images = obtainImagesOrdered();
		views = new ArrayList<ImageView>();
		for (int i = 0; i < 16; i++) {
			ImageView img = new ImageView(this);
			img.setScaleX(0.98f);
			img.setScaleY(0.98f);
			img.setImageDrawable(images.get(i));
			img.setTag(String.valueOf(i));
			img.setScaleType(ImageView.ScaleType.FIT_XY);
			img.setAdjustViewBounds(true);
			img.setLayoutParams(new LayoutParams(384, 215));
			views.add(img);
		}
		Collections.shuffle(views);
		for (int i = 0; i < 16; i++) {
			grid.addView(views.get(i));
			views.get(i).setOnTouchListener(new OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					v.startDrag(null, new DragShadowBuilder(v), v, 0);
					return false;
				}
			});
			views.get(i).setOnDragListener(new OnDragListener() {
				@Override
				public boolean onDrag(View v, DragEvent event) {
					if (event.getAction() == DragEvent.ACTION_DROP) {
						ImageView imgView = (ImageView) event.getLocalState();
						PropertyValuesHolder translationX = PropertyValuesHolder
								.ofFloat("x", v.getX(), imgView.getX());
						PropertyValuesHolder translationY = PropertyValuesHolder
								.ofFloat("y", v.getY(), imgView.getY());
						PropertyValuesHolder translationX1 = PropertyValuesHolder
								.ofFloat("x", imgView.getX(), v.getX());
						PropertyValuesHolder translationY1 = PropertyValuesHolder
								.ofFloat("y", imgView.getY(), v.getY());

						ObjectAnimator anims = ObjectAnimator
								.ofPropertyValuesHolder(v, translationX,
										translationY);
						anims.setDuration(200);
						ObjectAnimator anims1 = ObjectAnimator
								.ofPropertyValuesHolder(imgView, translationX1,
										translationY1);
						anims1.setDuration(200);
						AnimatorSet as = new AnimatorSet();
						as.playTogether(anims, anims1);
						as.start();
						Collections.swap(views, views.indexOf(v),
								views.indexOf(imgView));
						if (allTrue()) {
							Toast.makeText(getApplicationContext(),
									"Congratulations you win", 10000).show();
						}
					}
					return true;
				}
			});
		}
	}

	public List<Drawable> obtainImagesOrdered() {
		TypedArray ids = getResources().obtainTypedArray(R.array.pieces);
		List<Drawable> imagesOrdered = new ArrayList<Drawable>();
		for (int i = 0; i < ids.length(); i++) {
			imagesOrdered.add(ids.getDrawable(i));
		}
		ids.recycle();
		return imagesOrdered;
	}

	private static boolean allTrue() {
		for (ImageView s : views) {
			if (!(Integer.valueOf(s.getTag().toString()) == views.indexOf(s)))
				return false;
		}
		return true;
	}
}
