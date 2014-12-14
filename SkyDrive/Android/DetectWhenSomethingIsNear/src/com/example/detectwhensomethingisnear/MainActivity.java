package com.example.detectwhensomethingisnear;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


public class MainActivity extends Activity implements  SensorEventListener{
	private SensorManager sm;
	private Sensor proximity;
	private SoundPool sound;
	private int soundId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sound = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId = sound.load(this, R.raw.roar, 1); 
        sm = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        if(sm.getDefaultSensor(Sensor.TYPE_PROXIMITY)!=null){
        	proximity = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        }
        else{
        	Toast.makeText(this, "There is no proximity Sensor",Toast.LENGTH_SHORT).show();
        }
    }
	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		Log.d("str",String.valueOf(proximity.getMaximumRange()));
		if(event.values[0]==0){
			sound.play(soundId, 1, 1, 0, 0, 1);
		}
	}
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
	
		
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		sm.registerListener(this, proximity, SensorManager.SENSOR_DELAY_GAME);
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		sm.unregisterListener(this);
	}


   
}
