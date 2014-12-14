package com.example.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class GameView extends View {

	
	private Bird birdie;
	private Background back;
	private RepeatedBackground rep;
	private Bitmap bitmapBird, bitmapBackground;
	private int width, height;
	private Obstacle obstacle;
	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		WindowManager wm = (WindowManager) getContext().getSystemService(
				Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		birdie = new Bird(size.x, size.y);
		back = new Background();
		rep = new RepeatedBackground(size.x, size.y,this);
		width = size.x;
		height = size.y;
		obstacle = new Obstacle(width,height);
		setBitmapSize();
		
		
		
	}

	

	public void setBitmapSize() {
		bitmapBird = extractedBird();
		bitmapBackground = extractedBackground();
		bitmapBackground = getResizedBitmap(bitmapBackground, height, width);
		//bitmapBird = getResizedBitmap(bitmapBird, 0.19f * height,0.105f * width);
		birdie.setBitmap(bitmapBird);
		rep.setBitmap(bitmapBackground);
		back.setBitmap(bitmapBackground);
	}


	private Bitmap extractedBackground() {
		new BitmapFactory();
		return BitmapFactory.decodeResource(getResources(), R.drawable.clouds);
	}

	private Bitmap extractedBird() {
		new BitmapFactory();
		return BitmapFactory.decodeResource(getResources(), R.drawable.bird);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		back.draw(canvas);	
		rep.draw(canvas);
		birdie.draw(canvas);
		obstacle.draw(canvas);
	}

	public Bitmap getResizedBitmap(Bitmap bm, float newHeight, float newWidth) {
		int width = bm.getWidth();
		int height = bm.getHeight();
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height,
				matrix, false);
		return resizedBitmap;
	}

	public Bird getBird() {
		return this.birdie;
	}

	public Background getBack() {
		return this.back;
	}
	public RepeatedBackground getRepBack(){
		return this.rep;
	}
	public Obstacle getObstacle(){
		return obstacle;
	}
}
