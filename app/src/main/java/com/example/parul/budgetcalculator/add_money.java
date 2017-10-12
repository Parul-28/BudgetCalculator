package com.example.parul.budgetcalculator;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static com.example.parul.budgetcalculator.R.id.addnew;
import static com.example.parul.budgetcalculator.R.id.balance_text;
import com.example.parul.budgetcalculator.money_used.*;

public class add_money extends AppCompatActivity{
    EditText i, p, d, u_name, pass;
    add_database ad;
    database db;
    int mYear, mMonth, mDay;
    Button details_add,addnew,cancel;
    TextView balance_text;
    money_used m;
    private DatePicker datePicker;
    private java.util.Calendar calendar;
    private TextView dateView;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_money);
        ad=new add_database(add_money.this);
        balance_text=(TextView)findViewById(R.id.balance_text);
        balance_text.setText("Balance = "+ad.getRemainingBudget());
        i = (EditText) findViewById(R.id.in);
        p = (EditText) findViewById(R.id.p);
        // d = (EditText) findViewById(R.id.d);
        u_name = (EditText) findViewById(R.id.user);
        pass = (EditText) findViewById(R.id.password);
        details_add=(Button)findViewById(R.id.details_add);
        addnew=(Button)findViewById(R.id.addnew);
        cancel=(Button)findViewById(R.id.cancel);
        balance_text=(TextView)findViewById(R.id.balance_text) ;
        ad = new add_database(this);
        db = new database(this);



        dateView = (TextView) findViewById(R.id.textView3);
        calendar = java.util.Calendar.getInstance();
        year = calendar.get(java.util.Calendar.YEAR);

        month = calendar.get(java.util.Calendar.MONTH);
        day = calendar.get(java.util.Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);
    }
    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        calendar = java.util.Calendar.getInstance();
        year = calendar.get(java.util.Calendar.YEAR);

        month = calendar.get(java.util.Calendar.MONTH);
        day = calendar.get(java.util.Calendar.DAY_OF_MONTH);
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
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }


    public void addnew(View v) {

        if (i.getText().toString().equals("") || p.getText().toString().equals("")){
            //  || dateView.getText().toString().equals("")) {
            Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
        }
        else {
            String s = p.getText().toString();
            int pr = Integer.parseInt(s);
            long l = ad.addItem(i.getText().toString(), pr, dateView.getText().toString());
            if (l > 0) {

                balance_text.setText("Balance = "+ad.getRemainingBudget());

                Log.e("insert log", "details added");

                p.setText("");
                i.setText("");
                calendar = java.util.Calendar.getInstance();
                year = calendar.get(java.util.Calendar.YEAR);

                month = calendar.get(java.util.Calendar.MONTH);
                day = calendar.get(java.util.Calendar.DAY_OF_MONTH);
                showDate(year, month+1, day);

//                DatePickerDialog.OnDateSetListener myDateListener = new
//                        DatePickerDialog.OnDateSetListener() {
//                            @Override
//                            public void onDateSet(DatePicker arg0,
//                                                  int year, int month, int day) {
//                                // TODO Auto-generated method stub
//                                // arg1 = year
//                                // arg2 = month
//                                // arg3 = day
//                                showDate(year, month+1, day);
//                            }
//                        };
//                //showDate(year, month+1, day);
            }
            ad.updateRemainingBudget(pr);

        }
    }


    public void details_add(View v){

        if (i.getText().toString().equals("") || p.getText().toString().equals("")
                || dateView.getText().toString().equals("")) {
            Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
        }
        else {
            String s = p.getText().toString();
            int pr = Integer.parseInt(s);
            long l = ad.addItem(i.getText().toString(), pr, dateView.getText().toString());
            if (l > 0) {
                balance_text.setText("Balance = "+ad.getRemainingBudget());
                Log.e("insert log", "details added");
                ad.updateRemainingBudget(pr);
                Intent intent = new Intent(add_money.this, money_used.class);
                // money_used.d.dismiss();
                startActivity(intent);

            }
        }

    }

    public void cancel(View v){
        Intent intent=new Intent(add_money.this,money_used.class);
        Log.e("msg","###");
        startActivity(intent);
    }

    public void balance(View v){

        balance_text.setText("Balance = "+ad.getRemainingBudget());
        //Toast.makeText(this,"Balance is"+ad.getRemainingBudget(),Toast.LENGTH_SHORT).show();

    }
}


