package com.example.tutorial;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyCustomAdapter extends ArrayAdapter<String>{
	private boolean [] flags;
	public MyCustomAdapter(Context context, int resource, List<String> objects,boolean [] flags) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		this.flags = flags;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = super.getView(position, convertView, parent);
        TextView text = (TextView) view.findViewById(android.R.id.text1);
        if(this.flags[position]==true){
        	text.setTextColor(Color.YELLOW);
        }
        else{
        	text.setTextColor(Color.WHITE);
        }
		return text;
		
	}
	

}
