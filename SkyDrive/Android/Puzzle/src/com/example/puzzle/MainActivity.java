package com.example.puzzle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		int titleId = getResources().getIdentifier("action_bar_title", "id",
				"android");
		TextView abTitle = (TextView) findViewById(titleId);
		abTitle.setTextColor(Color.parseColor("#fff6fb"));
		LinearLayout linearLayoutStatic = (LinearLayout) findViewById(R.id.container);

		List<Drawable> images = obtainImagesOrdered();

		Collections.shuffle(images);
		int array[][] = new int[images.size()][images.size()];

		for (int i = 0; i < Math.sqrt(images.size()); i++) {
			LinearLayout linearLayoutDynamic = new LinearLayout(this);
			linearLayoutDynamic.setLayoutParams(new LayoutParams(
					ViewGroup.LayoutParams.WRAP_CONTENT,
					ViewGroup.LayoutParams.WRAP_CONTENT));
			ScrollView sv = (ScrollView) findViewById(R.id.scroll);

			sv.setBackgroundColor(Color.BLACK);
			linearLayoutDynamic.setBackgroundColor(Color.parseColor("#442446"));
			linearLayoutStatic.addView(linearLayoutDynamic);

			for (int j = 0; j < Math.sqrt(images.size()); j++) {
				final ImageView imageView = new ImageView(this);

				imageView.setScaleX(0.99f);
				imageView.setScaleY(0.99f);
				array[i][j] = (int) (j + Math.sqrt(images.size()) * i);

				imageView.setImageDrawable(images.get(array[i][j]));

				imageView.setScaleType(ImageView.ScaleType.FIT_XY);
				imageView.setAdjustViewBounds(true);
				imageView.setLayoutParams(new LayoutParams(384, 216));
				linearLayoutDynamic.addView(imageView);
				imageView.setOnTouchListener(new OnTouchListener() {

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub

						v.startDrag(null, new DragShadowBuilder(v), v, 0);

						return false;
					}
				});
				imageView.setOnDragListener(new OnDragListener() {

					@Override
					public boolean onDrag(View v, DragEvent event) {
						if (event.getAction() == DragEvent.ACTION_DROP) {
							Drawable image = ((ImageView) v).getDrawable();
							((ImageView) v).setImageDrawable(((ImageView) event
									.getLocalState()).getDrawable());
							((ImageView) event.getLocalState())
									.setImageDrawable(image);

						}
						return true;
					}
				});
			}
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

}
