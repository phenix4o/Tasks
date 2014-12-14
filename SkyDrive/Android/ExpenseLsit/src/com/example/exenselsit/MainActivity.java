package com.example.exenselsit;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

@SuppressLint("ClickableViewAccessibility")
public class MainActivity extends Activity {
	private MyAdapter baseAdapter;
	private EditText label, price;
	private static final int id = R.drawable.delete;
	private SQLiteDatabase database;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expenseslist);
		MySQLiteHelper helper = new MySQLiteHelper(this);
		database = helper.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(Items.TABLE.LABEL.toString(), "gosho");
		contentValues.put(Items.TABLE.PRICE.toString(), "12.5");
		database.insert(Items.TABLE_NAME, null, contentValues);
		
		String[] tableColumns = new String[] {
			   "LABEL", "PRICE"
			};
		
		Cursor c = database.query(Items.TABLE_NAME,tableColumns,null,null,null,null,null);
		c.moveToFirst();
		Log.d("new",String.valueOf(c.getString(0)));
	
		
		baseAdapter = new MyAdapter();

		ListView listView = (ListView) findViewById(R.id.list_view);
		listView.setAdapter(baseAdapter);
		label = (EditText) findViewById(R.id.label);
		price = (EditText) findViewById(R.id.price);
		ImageView img = (ImageView) findViewById(R.id.add);

		price.setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {

				if ((actionId == EditorInfo.IME_ACTION_DONE)
						|| ((event.isShiftPressed() == false)
								&& (event.getKeyCode() == KeyEvent.KEYCODE_ENTER) && (event
								.getAction() == KeyEvent.ACTION_DOWN))) {

					if (label.getText().toString().trim().length() != 0
							&& price.getText().toString().trim().length() != 0) {
						baseAdapter.add(new Expense(label.getText().toString(),
								price.getText().toString(), id));
						baseAdapter.notifyDataSetChanged();
						label.setText("");
						price.setText("");
					} else {
						Toast.makeText(getApplicationContext(),
								"Please enter label and price",
								Toast.LENGTH_SHORT).show();
					}

					return true;
				}

				return false;
			}
		});

		img.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (label.getText().toString().trim().length() != 0
						&& price.getText().toString().trim().length() != 0) {
					baseAdapter.add(new Expense(label.getText().toString(),
							price.getText().toString(), id));
					baseAdapter.notifyDataSetChanged();
					label.setText("");
					price.setText("");
				} else {
					Toast.makeText(getApplicationContext(),
							"Please enter label and price", Toast.LENGTH_SHORT)
							.show();
				}
				return false;
			}
		});
	}

}
