package com.example.expenselistsql;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class MyAdapter extends ArrayAdapter<Expenses> {
	private MainActivity context;
	List<Expenses> expList = new ArrayList<Expenses>();
	int layoutResourceId;
	ExpenseDbHelper db;
	SQLiteDatabase database;

	public MyAdapter(Context context, int layoutResourceId,
			List<Expenses> objects) {
		super(context, layoutResourceId, objects);
		this.layoutResourceId = layoutResourceId;
		this.expList = objects;
		this.context = (MainActivity)context;
		db = new ExpenseDbHelper(context);
		database = db.getWritableDatabase();
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
	
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.list_inner_view, parent,
					false);
			TextView label = (TextView) convertView.findViewById(R.id.label);
			TextView price = (TextView) convertView.findViewById(R.id.price);
			Button btn = (Button) convertView.findViewById(R.id.delete);
			btn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					//database.delete(db.getTableExp(), null, null);
					db.deleteExp(expList.get(position));
					context.update();
				
				}
			});
			Expenses current = expList.get(position);
			label.setText(current.getLabel());
			price.setText(String.valueOf(current.getPrice()));
		}
		return convertView;
	}

}
