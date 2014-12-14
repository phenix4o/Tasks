package com.example.fragmentslesson;

import java.lang.ref.WeakReference;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LayoutInflater inflate = LayoutInflater.from(this);
        inflate.inflate(R.layout.activity_main, null);
        new AsyncTassk(this);
        FragmentManager manager = getFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        trans.commit();
        trans.add(R.layout.activity_main, null);
       // trans.replace(R.layout.activity_main, MyOtherFragment());
        trans.commit();
        
        //manager.findFragmentById(id);
        WeakReference<Integer>a;
        Integer b;
        AsyncTask<Void,Integer, Integer> c = new AsyncTask<Void, Integer, Integer>(){
        	//da byde statichen ili otdelen klas 
			@Override
			protected void onPreExecute() {
				// TODO on UI
				super.onPreExecute();
			}

			@Override
			protected void onPostExecute(Integer result) {
				// TODO on UI
				super.onPostExecute(result);
			}

			@Override
			protected Integer doInBackground(Void... params) {
				// TODO request server
				this.publishProgress(1);
				return new Integer(13);
			}

			@Override
			protected void onProgressUpdate(Integer... values) {
				// TODO Auto-generated method stub
				super.onProgressUpdate(values);
			}
        	
        };
       // a.execute(null);
       
    }
    
    private class AsyncTassk extends AsyncTask<Integer, Integer, Integer>{
    	private WeakReference <Context>ctx; // vmesto Context
    	
    	
    


		public AsyncTassk(Context mainActivity) {
			// TODO Auto-generated constructor stub
			this.ctx = new WeakReference<Context>(mainActivity);
		}





		//pri podavane na kontekst
		@Override
		protected Integer doInBackground(Integer... params) {
			// TODO Auto-generated method stub
			return null;
		}
    	
    }
    
    
    private class MyFragment extends Fragment{

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			//Interface ma =(Interface)getActivity(); 
			getActivity();
			return super.onCreateView(inflater, container, savedInstanceState);
		}
    	
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
