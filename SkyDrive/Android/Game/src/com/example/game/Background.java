package com.example.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;

public class Background {
	private Bitmap bm;
	private PointF Position;
	private int width, height;
	public Background() {
		setPosition(new PointF(0, 0));
	}

	protected void draw(Canvas canvas) {
		canvas.drawBitmap(getBitmap(), getPosition().x, getPosition().y,
				null);
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

}
