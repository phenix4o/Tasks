package com.example.expenselistsql;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ExpenseDbHelper db;
	List<Expenses> list;
	MyAdapter adapt;
	ListView listTask;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		db = new ExpenseDbHelper(this);
		list = db.getAllExpenses();
		adapt = new MyAdapter(MainActivity.this, R.layout.list_inner_view, list);
		listTask = (ListView) findViewById(R.id.listView1);
		listTask.setAdapter(adapt);
		
	}

	public void addExpNow(View v) {
		EditText label = (EditText) findViewById(R.id.editText1);
		EditText price = (EditText) findViewById(R.id.editText2);
		String l = label.getText().toString();
		String pr = price.getText().toString();

		if (l.equalsIgnoreCase("") || pr.equalsIgnoreCase("")) {
			Toast.makeText(this, "enter the task description first!!",
					Toast.LENGTH_LONG).show();
		} else {
			Expenses exp = new Expenses(l, Double.valueOf(pr));
			db.addExp(exp);
			
			Log.d("tasker", "data added");
			label.setText("");
			price.setText("");
			adapt.add(exp);
			update();
			
		}
	}
	public void update(){
		adapt = new MyAdapter(this, R.layout.list_inner_view, db.getAllExpenses());
		adapt.notifyDataSetChanged();
		listTask.setAdapter(adapt);
	}
	
}
