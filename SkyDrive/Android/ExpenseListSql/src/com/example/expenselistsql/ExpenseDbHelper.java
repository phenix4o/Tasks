package com.example.expenselistsql;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ExpenseDbHelper extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "Expenses database";
	// tasks table name
	private static final String TABLE_EXP = "Expenses";
	public static String getTableExp() {
		return TABLE_EXP;
	}

	// tasks Table Columns names
	private static final String KEY_ID = "ID";
	private static final String KEY_LABEL = "LABEL";
	private static final String KEY_PRICE = "PRICE";

	public ExpenseDbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_EXP + " ( " + KEY_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_LABEL + " TEXT, "
				+ KEY_PRICE + " REAL)";
		db.execSQL(sql);
		

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXP);
		onCreate(db);
	}

	public void addExp(Expenses exp) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_LABEL, exp.getLabel()); // task name
		// status of task- can be 0 for not done and 1 for done
		values.put(KEY_PRICE, exp.getPrice());

		// Inserting Row
		db.insert(TABLE_EXP, null, values);
		db.close(); // Closing database connection
	}

	public List<Expenses> getAllExpenses() {
		List<Expenses> expList = new ArrayList<Expenses>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_EXP;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Expenses exp = new Expenses();
				exp.setId(cursor.getInt(0));
				exp.setLabel(cursor.getString(1));
				exp.setPrice(cursor.getInt(2));
				// Adding contact to list
				expList.add(exp);
			} while (cursor.moveToNext());
		}
		// return task list
		return expList;
	}

	public void updateExp(Expenses exp) {
		// updating row
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_LABEL, exp.getLabel());
		values.put(KEY_PRICE, exp.getPrice());
		db.update(TABLE_EXP, values, KEY_ID + " = ?",
				new String[] { String.valueOf(exp.getId()) });
	}
	public void deleteExp(Expenses exp) {
		// updating row
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		db.delete(TABLE_EXP, KEY_LABEL+"=?", new String[]{exp.getLabel()});
	
	}

}
