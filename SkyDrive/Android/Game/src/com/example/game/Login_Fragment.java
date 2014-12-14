package com.example.game;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Login_Fragment extends Fragment {
	private View view;
	private ProgressBar progress;
	private RadioButton choice;
	private RadioGroup radioGroup;
	private static long highScore;
	private UploadScoreTask mTask;
	private StatusLine statusLine;
	private EditText et1, et;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.login__fragment, container, false);
		progress = (ProgressBar) view.findViewById(R.id.progressBar1);
		SharedPreferences sharedPref = getActivity().getPreferences(
				Context.MODE_PRIVATE);
		radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup1);
		et = (EditText) view.findViewById(R.id.editText1);
		et1 = (EditText) view.findViewById(R.id.editText2);
		highScore = sharedPref.getInt("score", 0);

		TextView tx2 = (TextView) view.findViewById(R.id.textView1);
		tx2.setText(tx2.getText() + String.valueOf(highScore));
		Button btn = (Button) view.findViewById(R.id.button1);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (et.getText().toString().trim().length() != 0
						&& et1.getText().toString().trim().length() != 0) {
					uploadScore();
				}
				else{
					Toast.makeText(getActivity(), "Please enter name and email", Toast.LENGTH_SHORT)
					.show();
				}
			}
		});
		return view;
	}

	private class UploadScoreTask extends AsyncTask<Void, Integer, Void> {
		
		// ako shte iznasqm klasa
		private WeakReference<Context> ctx;

		public UploadScoreTask(Context context) {
			ctx = new WeakReference<Context>(context);
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			progress.setVisibility(View.VISIBLE);
		}

		@Override
		protected Void doInBackground(Void... params) {

			choice = (RadioButton) view.findViewById(radioGroup
					.getCheckedRadioButtonId());
			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpPut putRequest = new HttpPut(
						"http://www.posttestserver.com/post.php?dir=martin");
				putRequest.addHeader("Content-Type", "application/json");
				JSONObject loginObject = new JSONObject();
				loginObject.put("name", et.getText().toString());
				loginObject.put("mail", et1.getText().toString());
				loginObject.put("whereFrom", choice.getText().toString());
				loginObject.put("score", highScore);
				putRequest.setEntity(new StringEntity(loginObject.toString()));
				HttpResponse response = httpClient.execute(putRequest);
				statusLine = response.getStatusLine();

			} catch (JSONException e) {
				// TODO Auto-generated catch block

			} catch (UnsupportedEncodingException e) {

				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {

			super.onPostExecute(result);
			progress.setVisibility(View.INVISIBLE);
			if (statusLine == null) {
				Toast.makeText(ctx.get(), "Network error",
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(ctx.get(), "Success", Toast.LENGTH_SHORT)
						.show();
				GameFragment gf = new GameFragment();
				FragmentManager fm1 = getFragmentManager();
				FragmentTransaction ft1 = fm1.beginTransaction();
				ft1.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);			
				ft1.replace(R.id.container, gf).commit();
			}
		}

		@Override
		protected void onProgressUpdate(Integer... values) {

			super.onProgressUpdate(values);
		}

	}

	private void uploadScore() {
		if (mTask != null
				&& mTask.getStatus() != UploadScoreTask.Status.FINISHED) {
			mTask.cancel(true);
		}
		mTask = new UploadScoreTask(getActivity());
		mTask.execute();
	}
}
