package com.example.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

public class RepetedBackground {
	private Bitmap bm;
	private PointF Position;
	private int width, height;
	private int i;

	public RepetedBackground() {
		setPosition(new PointF(1920, 0));
		setI(0);
	}

	protected void draw(Canvas canvas) {
		// TODO Auto-generated method stub

		canvas.drawBitmap(getBitmap(),
				1920 * getI() + GameView.back.getPosition().x, getPosition().y,
				new Paint());

	}

	public Bitmap getBitmap() {
		return this.bm;
	}

	public void setBitmap(Bitmap bm) {
		this.bm = bm;
	}

	public PointF getPosition() {
		return Position;
	}

	public void setPosition(PointF getPosition) {

		this.Position = getPosition;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

}
