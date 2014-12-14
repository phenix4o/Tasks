package com.example.androidlistviewsadapters;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class MyAdapter  extends BaseAdapter{
	List<String> items;
	@Override
	public int getCount() {
		// broq na itemite
		items.size();
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// obekt ot tip expense label i price
		items.get(position);
		return null;
	}

	@Override
	public long getItemId(int position) {
		//vryshta idto na obekta
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// vryshta kak da izglejda samiqt red
		//convertView e recyclenoto view
		
		//Viewgroup e parent na konvertview
		return null;
	}

}
