package com.example.parul.budgetcalculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class login extends AppCompatActivity {
    database db;
    EditText u_name,pass,e;
    TextView sign;
    Button login,ok;
    EditText e1 ;

    public static String budget;


    public List<String> ar=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        u_name=(EditText)findViewById(R.id.user);
        pass=(EditText)findViewById(R.id.password);
        login=(Button)findViewById(R.id.login) ;
        e=(EditText)findViewById(R.id.etext)  ;




        db=new database(this);
        SharedPreferences preferences = getSharedPreferences("pref",MODE_PRIVATE);

        String name= preferences.getString("name",u_name.getText().toString());
        String pass = preferences.getString("pass",this.pass.getText().toString());
        Log.e("name",name);
        Log.e("pass",pass);


    }

    public void login(View v) {
        int c = db.getcount(u_name.getText().toString(),pass.getText().toString());
        if(u_name.getText().toString().equals("")||pass.getText().toString().equals("")){
            Toast.makeText(this,"Username and Password cannot be null",Toast.LENGTH_SHORT).show();
        }
        else {
            if (c > 0) {
                Intent intent = new Intent(login.this, BudgetDialog.class);
                startActivity(intent);
//
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//              View view1 = inflater.inflate(R.layout.activity_budget_dialog,null);
//                e1= (EditText)view1.findViewById(R.id.activity_budget_dialog);
//                ok=(Button)view1.findViewById(R.id.ok);
//
//                ok.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent = new Intent(login.this, money_used.class);
//                            startActivity(intent);
//                            budget=e1.getText().toString();
//                    }
//                });
//
//                final AlertDialog.Builder builder = new AlertDialog.Builder(login.this);
//                builder.setView(view1).setCancelable(false);
//                builder.create().show();
//    }
//
//});
            }
            else{
                Toast.makeText(login.this,"Incorrect Username or Password",Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
