package com.example.game;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

@SuppressLint({ "NewApi", "WrongCall", "DrawAllocation" })
public class GameView extends View {

	public static Bird birdie;
	public static Background back;
	public static RepetedBackground rep;

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);

		birdie = new Bird();
		back = new Background();
		rep = new RepetedBackground();
	}

	@SuppressWarnings({ "deprecation", "static-access" })
	@Override
	protected void onDraw(Canvas canvas) {
		Bitmap bm = new BitmapFactory().decodeResource(getResources(),
				R.drawable.bird);
		Bitmap background = new BitmapFactory().decodeResource(getResources(),
				R.drawable.clouds);

		WindowManager wm = (WindowManager) getContext().getSystemService(
				Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		background = getResizedBitmap(background, display.getHeight(),
				display.getWidth());
		bm = getResizedBitmap(bm, 200, 200);

		back.setBitmap(background);
		back.draw(canvas);
		rep.setBitmap(background);
		rep.draw(canvas);
		birdie.setBitmap(bm);
		birdie.draw(canvas);

	}

	public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
		int width = bm.getWidth();
		int height = bm.getHeight();
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		// create a matrix for the manipulation
		Matrix matrix = new Matrix();
		// resize the bit map
		matrix.postScale(scaleWidth, scaleHeight);
		// recreate the new Bitmap
		Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height,
				matrix, false);
		return resizedBitmap;
	}

}
