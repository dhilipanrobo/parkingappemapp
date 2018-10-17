package com.skyparking.admin.parkingappemapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity implements View.OnClickListener {
    Button login;
    EditText ETname,ETpass;
    String STname,STpass,zid,cid,STlogusername,STlogpass,mz_id,mapk;
    CheckBox show_hidde;
    ProgressBar process;
    String[] error, mzone_status,mzid,mcid,k;
    String zonestatus="0";
    DBhelper db1=new DBhelper(this);
    DBhelper rd= new DBhelper(this);
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        ActivityCompat.requestPermissions(login.this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                1);


        setContentView ( R.layout.activity_login );
        ETname=findViewById ( R.id.editText8 );
        ETpass=findViewById ( R.id.editText9 );
        login = findViewById ( R.id.button9 );
        process=findViewById ( R.id.progressBar );
        process.setVisibility(View.GONE);//change here
        show_hidde=findViewById ( R.id.checkBox );
        ETpass.setOnClickListener ( this );
        login.setOnClickListener ( this );
        show_hidde.setOnClickListener ( this );


        SharedPreferences sharedPreferences = getSharedPreferences("userlog", Context.MODE_PRIVATE);
        zid=sharedPreferences.getString("zoenid","");
        cid=sharedPreferences.getString("zcid","");
        mapk=sharedPreferences.getString("key","");
        STlogusername=sharedPreferences.getString ( "username","" );
        STlogpass=sharedPreferences.getString ( "pass", "");
        if(STlogusername.isEmpty ())
        {

        }
        else {
            ETpass.setText ( STlogpass.toString () );
            ETname.setText ( STlogusername.toString () );
        }
        db1.Select_HEAD_FOOT ();


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        if(id== R.id.action_token){


            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Enter Password");

            LayoutInflater inflater = getLayoutInflater();
            View dialoglayout = inflater.inflate(R.layout.alb, null);
            final EditText userAnswer = (EditText) dialoglayout.findViewById(R.id.editText);
            builder.setNegativeButton("ok", new DialogInterface.OnClickListener() {


                @Override
                public void onClick(DialogInterface arg0, int arg1) {



                    String pass=userAnswer.getText().toString();
                    if("13579".equals(pass)){
                        d1();

                    }
                    else{//vibrator.vibrate(500);
                    }







                }
            });




            builder.setView(dialoglayout);

            builder.show();

        }


        else if(id== R.id.action_printer){


            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Enter Password");

            LayoutInflater inflater = getLayoutInflater();
            View dialoglayout = inflater.inflate(R.layout.alb, null);
            final EditText userAnswer = (EditText) dialoglayout.findViewById(R.id.editText);
            builder.setNegativeButton("ok", new DialogInterface.OnClickListener() {


                @Override
                public void onClick(DialogInterface arg0, int arg1) {



                    String pass=userAnswer.getText().toString();
                    if("13579".equals(pass)){
                        d2();

                    }
                    else{//vibrator.vibrate(500);
                    }







                }
            });




            builder.setView(dialoglayout);

            builder.show();

        }

        else if(id== R.id.action_syc_token){


            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Enter Password");

            LayoutInflater inflater = getLayoutInflater();
            View dialoglayout = inflater.inflate(R.layout.alb, null);
            final EditText userAnswer = (EditText) dialoglayout.findViewById(R.id.editText);
            builder.setNegativeButton("ok", new DialogInterface.OnClickListener() {


                @Override
                public void onClick(DialogInterface arg0, int arg1) {



                    String pass=userAnswer.getText().toString();
                    if("13579".equals(pass)){
                        d3();

                    }
                    else{//vibrator.vibrate(500);
                    }

                }
            });
            builder.setView(dialoglayout);
            builder.show();

        }

        return super.onOptionsItemSelected(item);
    }
    void d1(){
        Intent v=new Intent(this,Add_Token.class);
        startActivity(v);
    }

    void d2(){
        Intent v=new Intent(this,Add_printer.class);
        startActivity(v);
    }

    void d3(){
        rd.deletertoken ();
        tokensyz ();
        msgsyz ();

        // getJSONmsg (constant.URL_Msg+zid);
        process.setVisibility(View.VISIBLE);//change here

    }


    @Override
    public void onClick(View view) {
        if (show_hidde.isChecked ()){
            ETpass.setTransformationMethod ( null );

        }
        else {

            ETpass.setTransformationMethod ( new PasswordTransformationMethod () );
        }
        int id = view.getId();
        if(id== R.id.button9){

            STname=ETname.getText ().toString ();
            STpass=ETpass.getText ().toString ();

            if(STname.isEmpty ())
            {

                ETname.requestFocus();
            }
            else if(STpass.isEmpty ()) {



                ETpass.requestFocus();
            }
            else {

                log ();


            }

        }


    }









    private void check(String con,String mzstatus,String Z_id,String cid,String key){
        STpass=ETpass.getText ().toString ();
        STname=ETname.getText ().toString ();
        if(con.equals ( "false" )&& mzstatus.equals ( "1" )) {

            SharedPreferences sharedPreferences = getSharedPreferences("userlog", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            SharedPreferences.Editor logid=sharedPreferences.edit();
            editor.putString("key",key);
            editor.putString("username",STname);
            editor.putString("zoenid",Z_id);
            editor.putString("zcid",cid.toString ());
            logid.putString ( "pass",STpass );
            logid.putString("username",STname);
            editor.apply();
            logid.apply ();
            logid.commit ();

            editor.commit();




            Intent v=new Intent(this,Home_Page.class);
            startActivity(v);
        }
        else if(con.equals ( "true" )){
            Toast.makeText ( this, "UnAuthorized User", Toast.LENGTH_SHORT ).show ( );
        }
        else{

            Toast.makeText ( this, "Activated Zone", Toast.LENGTH_SHORT ).show ( );
        }

    }







    void log(){
        STpass=ETpass.getText ().toString ();
        STname=ETname.getText ().toString ();
        // Toast.makeText ( this, ""+constant.URL_log1, Toast.LENGTH_SHORT ).show ( );
        StringRequest request =new StringRequest ( StringRequest.Method.POST, constant.URL_log1, new Response.Listener<String> ( ) {
            @Override
            public void onResponse(String response) {
                // Toast.makeText ( login.this, "res: "+response, Toast.LENGTH_SHORT ).show ( );
                try {
                    loadIntoListViewlog ( "["+response+"]" );
                } catch (JSONException e) {
                    e.printStackTrace ( );
                }
            }
        } , new Response.ErrorListener ( ) {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        } ){



            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<> (  );
                params.put ( "name",STname);
                params.put ( "password",STpass );
                return params;
            }
        };
        Volley.newRequestQueue ( this ).add ( request );

    }

    void tokensyz(){

        //Toast.makeText ( this, "mm"+mapk, Toast.LENGTH_SHORT ).show ( );
        StringRequest request =new StringRequest ( StringRequest.Method.POST, constant.URL_Tokenget, new Response.Listener<String> ( ) {
            @Override
            public void onResponse(String response) {

                try {
                    loadIntoListViewtoken ( response );
                } catch (JSONException e) {
                    e.printStackTrace ( );
                }
            }
        } , new Response.ErrorListener ( ) {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        } ){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", mapk);
                return headers;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<> (  );


                return params;
            }
        };
        Volley.newRequestQueue ( this ).add ( request );

    }
    void msgsyz(){

        //    Toast.makeText ( this, ""+mapk, Toast.LENGTH_SHORT ).show ( );
        StringRequest request =new StringRequest ( StringRequest.Method.POST, constant.URL_msg, new Response.Listener<String> ( ) {
            @Override
            public void onResponse(String response) {
                // Toast.makeText ( login.this, "msg: "+response, Toast.LENGTH_SHORT ).show ( );
                try {
                    loadIntoListViewmsg1 ( "["+response+"]" );
                } catch (JSONException e) {
                    e.printStackTrace ( );
                }
            }
        } , new Response.ErrorListener ( ) {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        } ){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", mapk);
                return headers;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<> (  );


                return params;
            }
        };
        Volley.newRequestQueue ( this ).add ( request );

    }




    private void loadIntoListViewlog(String json) throws JSONException {

        JSONArray jsonArray = new JSONArray(json);

        k = new String[jsonArray.length()];
        error=new String[jsonArray.length ()];
        mzone_status=new String[jsonArray.length ()];
        mzid=new String[jsonArray.length ()];
        mcid=new String[jsonArray.length ()];




        String[] date = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            k[i] = obj.getString("api_key");
            error[i]=obj.getString ( "error" );
            mzone_status[i]=obj.getString ( "zone_status" );
            mzid[i]=obj.getString ( "z_id" );
            mcid[i]=obj.getString ( "c_id" );

        }
        for (int i = 0; i < jsonArray.length(); i++) {

            check ( error[i],mzone_status[i],mzid[i],mcid[i],k[i]);
            mz_id=mzid[i];


        }






    }

    private void loadIntoListViewmsg1(String json) throws JSONException {

        JSONArray jsonArray = new JSONArray(json);

        String[] head = new String[jsonArray.length()];
        String[] head_2 = new String[jsonArray.length()];
        String[] foot = new String[jsonArray.length()];
        String[] foot_2 = new String[jsonArray.length()];




        String[] date = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            head[i] = obj.getString("header1");
            head_2[i]=obj.getString("header2");
            foot[i]=obj.getString("footer1");
            foot_2[i]=obj.getString("footer2");

        }
        for (int i = 0; i < jsonArray.length(); i++) {
            // Toast.makeText ( this, ""+V_type[i].toString ()+Amount[i]+sub_hour[i]+sub_amount[i], Toast.LENGTH_SHORT ).show ( );

            db1.insert_head (head[i].toString (),head_2[i],foot[i],foot_2[i]);

        }






    }

    private void loadIntoListViewtoken(String json) throws JSONException {
        JSONObject jsonObj = new JSONObject(json);
        JSONArray pri = jsonObj.getJSONArray("price");

        //   JSONArray jsonArray = new JSONArray(emp);

        k = new String[pri.length()];
        String[] V_type = new String[pri.length()];
        String[] Validity = new String[pri.length()];
        String[] Amount = new String[pri.length()];
        String[] sub_hour = new String[pri.length()];
        String[] sub_amount = new String[pri.length()];




        for (int i = 0; i <pri.length(); i++) {
            JSONObject c = pri.getJSONObject(i);

            V_type[i] = c.getString("vehicle_type");
            Amount[i]=c.getString("p_amount");
            sub_hour[i]=c.getString("sub_sequent_hour");
            sub_amount[i]=c.getString("sub_sequent_price");
            Validity[i]=c.getString("p_hours");


        }
        for (int i = 0; i < pri.length(); i++) {
            db1.insertdb2(V_type[i].toString (),Validity[i],Amount[i],sub_hour[i],sub_amount[i]);

//


        }
        process.setVisibility(View.GONE);//change here
        Toast.makeText ( this, "Synchronized Successfully...", Toast.LENGTH_SHORT ).show ( );






    }



}


