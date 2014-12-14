package com.example.exenselsit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {
	private static final String DB_NAME = "Shopping list";
	public MySQLiteHelper(Context context) {
		super(context, DB_NAME, null, 1);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(
                "CREATE TABLE " + Items.TABLE_NAME + 
                        " ( " + Items.TABLE.ID + " integer auto_increment primary key, " 
                        + Items.TABLE.LABEL + " text, " +Items.TABLE.PRICE+" real "+ " )");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		 db.execSQL("DROP DATABASE IF EXIST " + DB_NAME);
         onCreate(db);
	}

}
