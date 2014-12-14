package com.example.downloadmanager;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Environment;

public class DownloadService extends IntentService {
	public static int total;

	public DownloadService() {
		super("DownloadService");

	}

	@Override
	protected void onHandleIntent(Intent intent) {
		String urlDiablo = "http://95.111.103.224:8080/static/diablo_vs_imperius.mp4";
		String urlObicham = "http://95.111.103.224:8080/static/obicham.mp3";
		String urlPark = "http://95.111.103.224:8080/static/parking_genius.mp4";
		String urlPenny = "http://95.111.103.224:8080/static/penny.mp4";
		int number = intent.getIntExtra("extra", 0);
		switch (number) {
		case 1:
			download(urlDiablo, 1);
			break;
		case 2:
			download(urlObicham, 2);
			break;
		case 3:
			download(urlPark, 3);
			break;
		case 4:
			download(urlPenny, 4);
			break;
		}

	}

	public void download(String url, int i) {
		try {
			URL download = new URL(url);
			URLConnection connection = download.openConnection();
			// if i use percentage
			int fileLength = connection.getContentLength();
			InputStream input = new BufferedInputStream(
					connection.getInputStream());
			OutputStream output = new FileOutputStream(new File(
					Environment.getExternalStorageDirectory(), "Test"
							+ String.valueOf(i) + ".mp4"));
			byte data[] = new byte[1024];

			int count;
			while ((count = input.read(data)) != -1) {

				total += count;
				output.write(data, 0, count);

			}
			if (fileLength == total) {
				total = 0;
				Intent intent = new Intent(this, MyBroadcastReceiver.class);
				PendingIntent pendingIntent = PendingIntent.getBroadcast(
						this.getApplicationContext(), 234324243, intent, 0);
				AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
				alarmManager.set(AlarmManager.RTC_WAKEUP,
						System.currentTimeMillis() + (i * 1000), pendingIntent);
			}
		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
