package com.skyparking.admin.parkingappemapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Add_Token extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener {
    String amount,vtype,STsh,STsa,STval,zid,cid;
    Button bt1,bt2;
    EditText et1,et2,etssh,etssa,etval;
    ListView lv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_token);
        et1=(EditText) findViewById(R.id.editText3);
        et2=(EditText) findViewById(R.id.editText4);
        etssh=(EditText) findViewById(R.id.editText5);
        etssa=(EditText) findViewById(R.id.editText6);
        etval=(EditText) findViewById(R.id.editText12);
        bt1=(Button) findViewById(R.id.button5);

        bt1.setOnClickListener(this);
        lv1=(ListView)findViewById(R.id.listview2);
        lv1.setOnItemClickListener(this);
        //   lisr();







    }

    @Override
    public void onClick(View v) {
        vtype=et1.getText().toString();
        amount=et2.getText().toString();
        STsh=etssh.getText().toString();
        STsa=etssa.getText().toString();
        STval=etval.getText ().toString ();


        int id = v.getId();
        if (id== R.id.button5){

            if(vtype.isEmpty())
            {
                Toast.makeText(this,"vehicle Type FIELD IS EMITY",Toast.LENGTH_LONG).show();

            }
            else{
                if(amount.isEmpty())
                {
                    Toast.makeText(this,"Amount FIELD IS EMITY",Toast.LENGTH_LONG).show();

                }
                else {


                    DBhelper db1=new DBhelper(this);
                    db1.insertdb2(vtype,STval,amount,STsh,STsa);
                    et1.setText("");
                    et2.setText("");
                    etssa.setText("");
                    etssh.setText("");
                    etval.setText("");
                }

                ;
            }




        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}

