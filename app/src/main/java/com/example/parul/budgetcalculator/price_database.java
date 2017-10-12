package com.example.parul.budgetcalculator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class price_database extends login {
    Button t,b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_database);
        t=(Button)findViewById(R.id.total);
        // b=(Button)findViewById(R.id.RemainingBalance);
    }
    public void total(View v){
        Log.e("Total Budget",budget);
    }
}

