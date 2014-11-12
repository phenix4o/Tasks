package com.example.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;

public class Bird {

	private Bitmap bm;
	private PointF Position;
	private int screenWidth, screenHeight;
	private float width_Image;
	private float height_Image;

	public Bird(float widthImg,float heightImg) {
		setHeight_Image(heightImg);
		setWidth_Image(widthImg);
		setPosition(new PointF(0.13f*getWidth_Image(), 0.41f*getHeight_Image()));
		

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
		return screenHeight;
	}

	public int getWidth() {
		return screenWidth;
	}

	public float getWidth_Image() {
		return width_Image;
	}

	public void setWidth_Image(float widthImg) {
		this.width_Image = widthImg;
	}

	public float getHeight_Image() {
		return height_Image;
	}

	public void setHeight_Image(float heightImg) {
		this.height_Image = heightImg;
	}

}
