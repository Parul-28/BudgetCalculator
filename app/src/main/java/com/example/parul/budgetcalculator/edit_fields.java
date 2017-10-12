package com.example.parul.budgetcalculator;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

public class edit_fields extends AppCompatActivity {
    TextView category,expense,date;
    CustomeAdapter custom;
    private java.util.Calendar calendar;
    private int year, month, day;
    public String s1,s2,s3;
    public static String c,p,d;
    ListView listView;

    // String c1,e1,d1;
    TextView cedit,pedit,dedit,balance_text;
    Button update;
    add_database ad;
    Integer i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_fields);
        ad=new add_database(edit_fields.this);
        balance_text=(TextView)findViewById(R.id.balance_text);
        balance_text.setText("Balance = "+ad.getRemainingBudget());
        s1 = listview.iname;
        s2 = listview.price;
        s3 = listview.date;
        i=listview.id;
        Log.e("##", s3);
        // Log.e("##", s2);
        update=(Button)findViewById(R.id.update);
        cedit = (TextView) findViewById(R.id.category_edit);
        pedit = (TextView) findViewById(R.id.expense_edit);
        dedit = (TextView) findViewById(R.id.dedit);
        cedit.setText(s1);
        pedit.setText(s2);
        dedit.setText(s3);
        ad=new add_database(edit_fields.this);
        // dateView = (TextView) findViewById(R.id.textView3);
        calendar = java.util.Calendar.getInstance();
        year = calendar.get(java.util.Calendar.YEAR);

        month = calendar.get(java.util.Calendar.MONTH);
        day = calendar.get(java.util.Calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);
    }
    @SuppressWarnings("deprecation")
    public void setDate(View v) {
//        calendar = java.util.Calendar.getInstance();
//        year = calendar.get(java.util.Calendar.YEAR);
//
//        month = calendar.get(java.util.Calendar.MONTH);
//        day = calendar.get(java.util.Calendar.DAY_OF_MONTH);
        showDialog(999);
        // Toast.makeText(getApplicationContext(), "ca", Toast.LENGTH_SHORT).show();
    }

    @SuppressWarnings("deprecation")
    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        dedit.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    public void update(View v){
        c= cedit.getText().toString();
        p= pedit.getText().toString();
        d=dedit.getText().toString();


        long l=ad.updateItems(i,c,p,d);
        //  ad.updateRemainingBudget(p)m
        if(l>0){
            balance_text.setText("Balance = "+ad.getRemainingBudget());

            Integer price_old= Integer.parseInt(listview.price);
            Integer price_new=Integer.parseInt(p);
            Integer price_final=price_new-price_old;
            Integer new_balance=ad.getRemainingBudget()-price_final;
            ad.updateBalance_edit(new_balance);
            Log.e("!!!"," "+new_balance);
            listview.iname=c;
            listview.price=p;
            listview.date=d;
            Log.e("New details",listview.iname+" "+listview.price+" "+listview.date);
            startActivity(new Intent(edit_fields.this,listview.class));
            listview.customeAdapter.notifyDataSetChanged();
        }





//        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View v1 = inflater.inflate(R.layout.activity_listview,null);
//
//        listView = (ListView)v1.findViewById(R.id.list);
//        ArrayList<ItemBean> ar2;
//        ar2=ad.getItemDetails();
//        CustomeAdapter c1= new CustomeAdapter(this,ar2);
//        listView.setAdapter(c1);
//        registerForContextMenu(listView);




//        listview.iname=c;
//        listview.price=p;
//        listview.date=d;
    }
    public void balance(View v){

        balance_text.setText("Balance = "+ad.getRemainingBudget());
        //Toast.makeText(this,"Balance is"+ad.getRemainingBudget(),Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onBackPressed() {
        finish();
    }

}


