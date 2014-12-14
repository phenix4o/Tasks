package com.example.starwarsgun;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity implements SensorEventListener {
	private SensorManager mSensorManager;
	private Sensor force;
	private SoundPool soundPool;
	private int soundId;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
		soundId = soundPool.load(this, R.raw.ray_gun, 1);
		
		if (mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {

			force = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		} else {
			Toast.makeText(this, "There is no accelerometer !",
					Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		double netForce = event.values[0] * event.values[0]; // X axis
		netForce += event.values[1] * event.values[1]; // Y axis
		netForce += (event.values[2]) * (event.values[2]); // Z axis (upwards)

		netForce = Math.sqrt(netForce) - SensorManager.GRAVITY_EARTH; // Take
		double CONST = this.force.getMaximumRange()/2;
		
		if(netForce>CONST){
			// the
			 soundPool.play(soundId, 1, 1, 0, 0, 1);
		}
																		// square
																		// root,
																		// minus
																		// gravity

	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mSensorManager.registerListener(this, force,
				SensorManager.SENSOR_DELAY_FASTEST);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mSensorManager.unregisterListener(this);

	}

}
