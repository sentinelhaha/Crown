package com.crown.login;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zjj on 2018/11/26.
 */
//连接数据库
public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context
                           ) {
        super(context, "pd.db", null, 1);
    }

    //创建表
    @Override
    public void onCreate(SQLiteDatabase db) {
        //SQLiteDatabase 执行sql语句
        String sql="create table pd_user(username varchar(50))";
        db.execSQL(sql);
    }

    //升级
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
