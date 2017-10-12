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

public class BudgetDialog extends AppCompatActivity {


    EditText e1;
    Button ok;
    TextView t1, balance_text;
    add_database ad;
    public static String budget;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.etext);

        preferences = getSharedPreferences("pref1", MODE_PRIVATE);
        boolean isFirstTime = preferences.getBoolean("isFTime", true);


        if (!isFirstTime) {
            startActivity(new Intent(BudgetDialog.this, money_used.class));
            finish();
        } else {
            preferences = getSharedPreferences("pref1", MODE_PRIVATE);
            SharedPreferences.Editor edit = preferences.edit();
            edit.putBoolean("isFTime", false);
            edit.commit();
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View view1 = inflater.inflate(R.layout.activity_budget_dialog, null);
            e1 = (EditText) view1.findViewById(R.id.etext);
            ok = (Button) view1.findViewById(R.id.ok);
            t1 = (TextView) view1.findViewById(R.id.budget_text);
            balance_text = (TextView)view1.findViewById(R.id.balance_text);

            final AlertDialog.Builder builder = new AlertDialog.Builder(BudgetDialog.this);
            builder.setView(view1);

            final AlertDialog d = builder.create();
            ad=new add_database(BudgetDialog.this);

            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    budget = e1.getText().toString();

                    if (budget.equals("")) {
                        t1.setText("Budget is null. Enter a valid budget");
                    } else {
                        t1.setText("");
                        int balance = Integer.parseInt(budget);
                        long l = ad.addBudget(balance);

                        if (l > 0) {
                            // Log.e("Balance","balance is "+ad.getRemainingBudget());
                            // balance_text.setText("Balance = " + ad.getRemainingBudget());
                            startActivity(new Intent(BudgetDialog.this,money_used.class));
                            finish();
                        }
                        d.dismiss();

                    }
                }
            });
            d.setCancelable(false);
            d.show();
        }
    }
}
