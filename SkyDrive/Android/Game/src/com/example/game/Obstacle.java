package com.example.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;

public class Obstacle {
	private Bitmap bm;
	private PointF Position;
	public static List<Rect> rectangles;
	private float width_Image;
	private float height_Image;
	private Paint mPaint;
	private Random rand;
	private Paint clearPaint;
	private int[] array;
	private int counter;

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public Obstacle(float widthImg, float heightImg) {
		setHeight_Image(heightImg);
		setWidth_Image(widthImg);
		setPosition(new PointF(600, 0));
		rectangles = new ArrayList<Rect>();
		// delete rectangle elements to save memory or use Queue
		mPaint = new Paint();
		mPaint.setColor(Color.GREEN);
		mPaint.setStyle(Style.FILL);
		rand = new Random();
		clearPaint = new Paint();
		rectInitialisation();
		array = new int[100];
		for (int i = 0; i < array.length; i++) {
			array[i] = 0;
		}
	}

	public int[] getArray() {
		return array;
	}

	public void setArray(int[] array) {
		this.array = array;
	}

	private void rectInitialisation() {
		for (int i = 0; i < 100; i++) {
			float c = (float) rand.nextInt(7);
			if (c > 2) {
				c = c / 10f;
			} else {
				c = (c / 10f) + 0.3f;
			}
			if (i % 2 == 0) {

				rectangles.add(new Rect((int) (getPosition().x + 0.26 * i
						* width_Image), (int) getPosition().y,
						(int) (getPosition().x + 0.26 * i * width_Image + 100),
						(int) (getPosition().y + c * height_Image)));
			} else {
				rectangles.add(new Rect((int) (getPosition().x + 0.26 * i
						* width_Image),
						(int) (height_Image - c * height_Image),
						(int) (getPosition().x + 0.26 * i * width_Image + 100),
						(int) (height_Image)));
			}
		}
	}

	public List<Rect> getRectangles() {
		return rectangles;
	}

	public static void setRectangles(List<Rect> rectangles) {
		Obstacle.rectangles = rectangles;
	}

	protected void draw(Canvas canvas) {
		counter = 0;
		for (int i = 0; i < rectangles.size(); i++) {
			if (rectangles.get(i).left <= 0.13f * 1920) {
				counter++;
			}
			if (rectangles.get(i).right > 0) {

				canvas.drawRect(rectangles.get(i), mPaint);
			} else {
				array[i] = 1;
				clearPaint.setXfermode(new PorterDuffXfermode(
						PorterDuff.Mode.CLEAR));
				canvas.drawRect(rectangles.get(i), clearPaint);
			}
		}
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
