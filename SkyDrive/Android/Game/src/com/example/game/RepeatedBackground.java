package com.example.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;

public class RepeatedBackground {
	private Bitmap bm;
	private PointF Position;
	private int i;
	private float widthCord;
	private GameView gameView;

	public RepeatedBackground(float widthCord, float heightCord, GameView gv) {
		setWidthCord(widthCord);
		setPosition(new PointF(getWidthCord(), 0));
		setI(0);
		gameView = (GameView) gv.findViewById(R.id.gameView1);
	}

	protected void draw(Canvas canvas) {

		canvas.drawBitmap(getBitmap(), getWidthCord() * getI()
				+ gameView.getBack().getPosition().x, getPosition().y,
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

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public float getWidthCord() {
		return widthCord;
	}

	public void setWidthCord(float widthCord) {
		this.widthCord = widthCord;
	}

}
