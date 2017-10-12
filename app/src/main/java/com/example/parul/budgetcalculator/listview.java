package com.example.parul.budgetcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class listview extends AppCompatActivity{
    ListView listView;
    TextView balance_text;
    public static String iname,price,date;
    public static Integer id;
    public Integer id_delete;
    public ArrayList<ItemBean> ar1=new ArrayList<ItemBean>();
    add_database ad;
    public static CustomeAdapter customeAdapter;
    // public String[] values;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        ad=new add_database(listview.this);
        balance_text=(TextView)findViewById(R.id.balance_text);
        balance_text.setText("Balance = "+ad.getRemainingBudget());

        listView = (ListView) findViewById(R.id.list);
        ad=new add_database(this);

        ar1= ad.getItemDetails();

        for(ItemBean i : ar1) {
            Log.e("ii", i.getIname() + "");
        }
        customeAdapter = new CustomeAdapter(this,ar1);
        customeAdapter.notifyDataSetChanged();

        listView.setAdapter(customeAdapter);

        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, view, menuInfo);
        menu.add("Edit");
        menu.add("Delete");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        Log.d("Menu Item ",ar1.get(info.position).getIname()+"  "+item.getTitle().toString());
        iname=ar1.get(info.position).getIname();
        price=ar1.get(info.position).getPrice();
        date=ar1.get(info.position).getDate();
        id=ar1.get(info.position).getId();

        if(item.getTitle().equals("Edit")){
            Intent intent = new Intent(listview.this, edit_fields.class);
            startActivity(intent);

        }
        else if(item.getTitle().equals("Delete")){
            iname=ar1.get(info.position).getIname();
            price=ar1.get(info.position).getPrice();
            date=ar1.get(info.position).getDate();
            Integer pr=Integer.parseInt(price);
            id_delete=ar1.get(info.position).getId();
            listview.customeAdapter.notifyDataSetChanged();
            long l=ad.deleteItem(id_delete);
            // listview.customeAdapter.notifyDataSetChanged();
            if(l>0){
                listview.customeAdapter.remove(ar1.get(info.position));
                listview.customeAdapter.notifyDataSetChanged();
                ad.updateRemainingBudget_add(pr);
                int bal=ad.getRemainingBudget();
                Log.e("!!!!"," "+bal);

            }


        }

        return true;
    }

    public void balance(View v){

        balance_text.setText("Balance = "+ad.getRemainingBudget());
        //Toast.makeText(this,"Balance is"+ad.getRemainingBudget(),Toast.LENGTH_SHORT).show();

    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(listview.this,money_used.class));
    }
}



