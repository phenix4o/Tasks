package com.example.tutorial;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ListView;

public class MainActivity extends ListActivity {
	private String rootpath;
	private boolean[] flags;
	private File dir;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		rootpath = Environment.getExternalStorageDirectory().toString();
		File f = new File(rootpath);
		rootpath = f.getAbsolutePath().toString();
		if (getIntent().hasExtra("path")) {
			rootpath = getIntent().getStringExtra("path");
		}
		setTitle(rootpath);
		List<String> values = new ArrayList<String>();
		dir = new File(rootpath);
		if (!dir.canRead()) {
			setTitle(getTitle() + " (inaccessible)");
		}
		String[] list = dir.list();
		File[] files = dir.listFiles();
		checkFilesAndDirectories(files);
		if (list != null) {
			for (String file : list) {
				values.add(file);

			}
		}
		MyCustomAdapter adapter = new MyCustomAdapter(this,
				android.R.layout.simple_list_item_1, values, flags);
		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String filename = (String) getListAdapter().getItem(position);
		Log.d("s", filename);
		if (rootpath.endsWith(File.separator)) {
			filename = rootpath + filename;

		} else {
			filename = rootpath + File.separator + filename;
		}
		if (new File(filename).isDirectory()) {
			Intent intent1 = new Intent(this, MainActivity.class);
			intent1.putExtra("path", filename);
			startActivity(intent1);
		} else {
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_VIEW);
			MimeTypeMap map = MimeTypeMap.getSingleton();
			String ext = MimeTypeMap
					.getFileExtensionFromUrl(dir.listFiles()[position]
							.getName());
			String type = map.getMimeTypeFromExtension(ext);
			if (type == null) {
				type = "*/*";
			}
			Uri data = Uri.fromFile(dir.listFiles()[position]);
			Log.d("uri", filename.toString());
			intent.setDataAndType(data, type);
			startActivity(intent);
		}
	}

	public void checkFilesAndDirectories(File[] files) {
		this.flags = new boolean[files.length];
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				this.flags[i] = true;
				Log.d("gd", String.valueOf(files[i]));
			}
		}
	}
}
