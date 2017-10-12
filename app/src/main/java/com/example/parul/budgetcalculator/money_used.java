package com.example.parul.budgetcalculator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class money_used extends AppCompatActivity {

    add_database ad;

    Button ok;
    EditText e1 ;
    TextView t1,balance_text,total_text;

    public static AlertDialog d;

    public static String budget;
    Button details_add,cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//
//        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                View view1 = inflater.inflate(R.layout.activity_budget_dialog,null);
//                e1= (EditText)view1.findViewById(R.id.etext);
//                ok=(Button)view1.findViewById(R.id.ok);
//                t1=(TextView)view1.findViewById(R.id.budget_text);
//
//
//        ad = new add_database(this);
//
//
//        SharedPreferences preferences = getSharedPreferences("pref",MODE_PRIVATE);
//        boolean isFirstTime = preferences.getBoolean("isFirst",true);
//        if (!isFirstTime){
//
//            final AlertDialog.Builder builder = new AlertDialog.Builder(money_used.this);
//            builder.setView(view1);
//
//           // final AlertDialog
//                    d=builder.create();
//
//            ok.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    budget = e1.getText().toString();
//
//                    if (budget.equals("")) {
//                        t1.setText("Budget is null. Enter a valid budget");
//                    }
//                    else
//                        {
//                        t1.setText("");
//                        int balance=Integer.parseInt(budget);
//                        long l= ad.addBudget(balance);
//
//                        if(l>0)
//                        {
//                           // Log.e("Balance","balance is "+ad.getRemainingBudget());
//
//                            balance_text=(TextView)findViewById(R.id.balance_text);
//                            balance_text.setText("Balance = "+ad.getRemainingBudget());
//
//                        }
//                        d.dismiss();
//
//                    }
//                }
//            });
//            d.setCancelable(false);
//            d.show();
//
//            SharedPreferences.Editor edit = preferences.edit();
//            edit.putBoolean("isFirst",false);
//            edit.commit();
//
//        }

        setContentView(R.layout.activity_money_used);
        ad=new add_database(money_used.this);
        balance_text=(TextView)findViewById(R.id.balance_text);

        total_text=(TextView)findViewById(R.id.total);
        balance_text.setText("Balance = "+ad.getRemainingBudget());
        total_text.setText("Total Budget = "+ad.getTotalBudget());
    }

//    @Override
//    public void onDestroy(){
//        d.dismiss();
//        super.onDestroy();
//       // balance_text.setText("Balance is "+ad.getRemainingBudget());
//
//    }

//    public void onResume(){
//        d.dismiss();
//        super.onResume();
//        // balance_text.setText("Balance is "+ad.getRemainingBudget());
//
//    }

    public void add(View view){
        Intent intent = new Intent(money_used.this, add_money.class);
        startActivity(intent);
    }

    public void show(View v){
        Intent intent = new Intent(money_used.this, listview.class);
        startActivity(intent);
    }
    public void total(View v){
        // Toast.makeText(this,"Budget is "+ad.getTotalBudget(),Toast.LENGTH_SHORT).show();;
        total_text.setText("Total Budget = "+ad.getTotalBudget());
    }

    public void balance(View v){

        balance_text.setText("Balance = "+ad.getRemainingBudget());
        //Toast.makeText(this,"Balance is"+ad.getRemainingBudget(),Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}

