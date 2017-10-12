package com.example.parul.budgetcalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class add_database extends SQLiteOpenHelper {
    String tname = "Items";
    String Item_name = "ItemName";
    String price = "Price";
    String date = "Date";
    String id= "ID";

    String budget_table ="budget";
    String totalBudget="Total_Budget";
    String RemainingBalance ="remainingBalance";



    public add_database(Context context) {

        super(context, "db3", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + tname + " ("+ id  + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Item_name + " text," + price + " int," + date + " text)";
        db.execSQL(sql);
        String sql1= "create table " + budget_table + " (" + totalBudget + " int," + RemainingBalance + " int)";
        db.execSQL(sql1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long addItem(String iname, int price, String date) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(this.price, price);
        values.put(this.date, date);
        values.put(Item_name, iname);

        return db.insert(tname, null, values);
    }

    public long addBudget(int balance) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values1 = new ContentValues();
        values1.put(totalBudget, balance);
        values1.put(RemainingBalance, balance);

        return db.insert(budget_table, null, values1);
    }

    public int getRemainingBudget(){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        int bal=0;
        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+budget_table,null);
        if (cursor.moveToNext()){
            bal= cursor.getInt(1);
        }
        return bal;
    }

    public int getTotalBudget(){
        SQLiteDatabase sqLiteDatabase =getWritableDatabase();
        int bud=0;
        Cursor cursor= sqLiteDatabase.rawQuery("select "+ totalBudget + " from "+ budget_table,null);
        if (cursor.moveToNext()){
            bud= cursor.getInt(0);
        }
        return bud;
    }



    public int updateRemainingBudget(int price){
        SQLiteDatabase db = getWritableDatabase();
        int remainingBudget=getRemainingBudget();
        int updatePrice = remainingBudget - price;

        ContentValues values = new ContentValues();
        values.put(RemainingBalance,updatePrice);
        return db.update(budget_table,values,null, null);
    }

    public int updateRemainingBudget_add(int price){
        SQLiteDatabase db = getWritableDatabase();
        int remainingBudget=getRemainingBudget();
        int updatePrice = remainingBudget + price;

        ContentValues values = new ContentValues();
        values.put(RemainingBalance,updatePrice);
        return db.update(budget_table,values,null, null);
    }

    public int updateBalance_edit(int price){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(RemainingBalance,price);
        return db.update(budget_table,values,null, null);
    }

    public ArrayList<ItemBean> getItemDetails() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cr = db.rawQuery("select *from " + tname, null);

        ArrayList<ItemBean> ar1=new ArrayList<ItemBean>();

        while (cr.moveToNext()) {
            //Log.e(" table"," "+id);
            ItemBean itemBean=new ItemBean(cr.getInt(0),cr.getString(1),cr.getString(2),cr.getString(3));
            ar1.add(itemBean);


        }
        return ar1;
    }

    public long updateItems (Integer id,String iname,String price,String date){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(this.Item_name,iname);
        values.put(this.price,price);
        values.put(this.date,date);
        return db.update(tname,values,"ID =?", new String[]{id.toString()});
    }

    public long deleteItem (Integer id){
        SQLiteDatabase db=getWritableDatabase();
        return db.delete(tname,"ID=?",new String[]{id.toString()});
    }

}

