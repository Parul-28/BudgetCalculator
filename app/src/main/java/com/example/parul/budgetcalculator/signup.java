package com.example.parul.budgetcalculator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

public class signup extends AppCompatActivity {
    EditText u,p;
    database d;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        u = (EditText)findViewById(R.id.u);
        p = (EditText)findViewById(R.id.p);
        d = new database(this);
        SharedPreferences preferences = getSharedPreferences("pref",MODE_PRIVATE);
        boolean isFirstTime = preferences.getBoolean("isFirst",true);
        if (!isFirstTime){
            startActivity(new Intent(signup.this,login.class));
        }


    }


    public  void reg(View view){
        long l= d.addUser(u.getText().toString(),p.getText().toString());
        if(u.getText().toString().equals("")||p.getText().toString().equals("")){
            Toast.makeText(this,"Username and Password cannot be null",Toast.LENGTH_SHORT).show();
        }
        else {
            if (l > 0) {
                SharedPreferences preferences = getSharedPreferences("pref", MODE_PRIVATE);
                SharedPreferences.Editor edit = preferences.edit();
                edit.putBoolean("isFirst", false);
                edit.commit();
                Log.e("insert log", "user added");
                Intent intent = new Intent(signup.this, login.class);
                startActivity(intent);


            }
        }

    }

}