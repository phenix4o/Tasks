package com.example.animations;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ImageView img;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		img = new ImageView(this);
		setContentView(R.layout.activity_main);
		final Button btn = (Button) findViewById(R.id.btn);
		
		/*final Animator anim = ObjectAnimator.ofFloat(btn, "scaleX", 0.0f,1.0f);
		final Animator anim1 = ObjectAnimator.ofFloat(btn, "rotate", 0.0f,360.0f);*/
		PropertyValuesHolder scale = PropertyValuesHolder.ofFloat("translationX", 100.0f,120.0f); 
		PropertyValuesHolder rotate = PropertyValuesHolder.ofFloat("translationY", 200.0f,250.0f); 
		final AnimatorSet as = new AnimatorSet();
		final ObjectAnimator anims = ObjectAnimator.ofPropertyValuesHolder(btn, scale,rotate);
		anims.setInterpolator(new AccelerateDecelerateInterpolator());
		//btn.animate().x(100).alpha(0.5f).start();
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				as.play(anims).before(anims);
				as.playSequentially();//LIst
			
				anims.start();
			}
		});
	
		/*final Animation anim = new TranslateAnimation(0,100,0,100);
		anim.setDuration(100);
		anim.setFillAfter(true);
		
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btn.startAnimation(anim);
			}
		});
		anim.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				
			}
		});*/

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
