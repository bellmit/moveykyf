package com.cyrj.pharmacymessage.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBSQLOpenHelper extends SQLiteOpenHelper {

	// private Context mContext;

	public DBSQLOpenHelper(Context context, String name, CursorFactory factory,
                           int version) {
		super(context, name, factory, version);
		// this.mContext = context;

	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL("DROP TABLE IF EXISTS " + "t_user");
		// 用户表
		db.execSQL("CREATE TABLE  t_user" + "("
				+ " _id INTEGER PRIMARY KEY AUTOINCREMENT ,"
				+ "remote_id TEXT NOT NULL," + "agent_id TEXT NOT NULL,"
				+ "user_name TEXT  ," + "password TEXT " + " );");

		db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS remote_agent_key ON t_user (remote_id,agent_id)");

		db.execSQL("INSERT INTO t_user(user_name,password,remote_id,agent_id) VALUES('root','sa',-1,-1)");

		// alertAtVs2(db);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		switch (newVersion) {
		case 2:
			switch (oldVersion) {
			case 1:
				alertAtVs2(db);
				break;
			}
			break;
		}
	}

	private void alertAtVs2(SQLiteDatabase db) {

		// db.execSQL("CREATE TABLE  t_push_msg" + "("
		// + " _id INTEGER PRIMARY KEY AUTOINCREMENT ,"
		// + "topic TEXT NOT NULL ," + "message_id INTEGER NOT NULL"
		// + " );");
	}
}
