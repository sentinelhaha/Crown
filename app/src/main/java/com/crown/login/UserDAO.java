package com.crown.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by zjj on 2018/11/26.
 */

public class UserDAO {
    public  int query(Context context){
        int count=0;
        //得到连接
        DatabaseHelper databaseHelper=new DatabaseHelper(context);
        //得到执行sql语句的对象
        SQLiteDatabase sqLiteDatabase=databaseHelper.getReadableDatabase();

        //定义sql
        String sql="select * from pd_user";
        String[] args=null;
        //cursor游标相当于resultSet
        Cursor cursor=sqLiteDatabase.rawQuery(sql,args);
        //判断结果
        if (cursor!=null)
        {
            count=cursor.getCount();
        }
        cursor.close();
        databaseHelper.close();
        //返回结果
        return  count;
    }

    public long insert(Context context, String username) {
        //得到连接
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        //得到写数据库对象
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        //把数据放到hashMap
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);

        //添加
        String tableName = "pd_user";
       long row= sqLiteDatabase.insert(tableName, null, contentValues);
        sqLiteDatabase.close();
        databaseHelper.close();
return row;
    }
}
