package com.example.parul.budgetcalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class database extends SQLiteOpenHelper{
    String tableName="User";
    String userName="Name";
    String password="Password";
    public List<UserBean> ar;

    public database(Context context) {

        super(context, "db1", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table "+tableName+"("+userName+" text,"+password+" text)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long addUser( String name,String password){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(userName,name);
        values.put(this.password,password);
        return db.insert(tableName,null,values);
    }


    public List <UserBean> getUser(){
        SQLiteDatabase db=getReadableDatabase();
//        ar=new ArrayList<String>();
//        Cursor cr= db.rawQuery("select *from "+tableName, null );
//        // int i=0;
//        ar.add("USERNAME");
//        while(cr.moveToNext()){
//            ar.add(cr.getString(0));
//
//        }
        Cursor cr= db.rawQuery("select *from "+tableName, null );
        ar=new ArrayList<>();
        while(cr.moveToNext()) {
            UserBean u = new UserBean(cr.getString(0), cr.getString(1));
            ar.add(u);
        }
        return ar;
    }

    public int getcount(String name,String password) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cr = db.rawQuery("select *from " + tableName + " where "+userName+"=? and "+this.password+" = ?",new String[]{name,password});
        return cr.getCount();
    }



    public long update (String name,String password){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(this.password,password);
        return db.update(tableName,values,"name=?", new String[]{name});
    }

    public long deleteUser (String name){
        SQLiteDatabase db=getWritableDatabase();
        return db.delete(tableName,"name=?",new String[]{name});
    }
}
