package com.skyparking.admin.parkingappemapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static android.content.ContentValues.TAG;

public class Home_Page extends Activity implements View.OnClickListener,DialogInterface.OnClickListener {
    Button bt1,bt2,bt3,bt4,bt5,bt6;
    String am1,mbno;
    int effectivePrintWidth = 48;

    Button btnPrint, btnSetPrnType;
    ArrayList<String> printerList;
    String creditData;
    ProgressDialog m_WaitDialogue;

    int glbPrinterWidth;

    //private Context context;
    EditText editText,rfText;
    private PrintWriter printOut;
    private Socket socketConnection;
    private String txtIP="";
    Spinner spinner;
    String imgDecodableString;
    private static final Context This = null;
    long Delay = 4000;


    // will enable user to enter any text to be printed
    EditText myTextbox;

    int readBufferPosition;
    volatile boolean stopWorker;
    BluetoothAdapter mBluetoothAdapter;
    BluetoothSocket mmSocket;
    BluetoothDevice mmDevice;

    // needed for communication to bluetooth device / network
    OutputStream mmOutputStream;
    InputStream mmInputStream;
    Thread workerThread;

    byte[] format = { 12, 16, 0 };  //27  33 0
    byte[] arrayOfByte1 = { 12, 16, 0 };
    String no,pri,zid,cid,username,STbno,bno="0",bt="0",mapk;
    ProgressBar process;
    byte[] readBuffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        bt="1";
        bt1=(Button)findViewById(R.id.button3);
        bt1.setOnClickListener(this);
        bt2=(Button)findViewById(R.id.button);
        bt2.setOnClickListener(this);
        bt3=(Button)findViewById(R.id.button6);
        bt3.setOnClickListener(this);

        bt4=(Button)findViewById(R.id.button11);
        bt4.setOnClickListener(this);
        bt5=(Button)findViewById(R.id.button12);
        bt5.setOnClickListener(this);
        bt6=(Button)findViewById(R.id.button20);
        bt6.setOnClickListener(this);
        process=findViewById ( R.id.progressBar2 );
        process.setVisibility(View.GONE);//change here
        SharedPreferences sharedPreferences = getSharedPreferences("userlog", Context.MODE_PRIVATE);
        zid=sharedPreferences.getString("zoenid","");
        cid=sharedPreferences.getString("zcid","");
        username=sharedPreferences.getString("username","");
        mapk=sharedPreferences.getString("key","");
        bno();
        // Toast.makeText (this, "apk "+mapk, Toast.LENGTH_SHORT ).show ( );

        try{

            DBhelper rd= new DBhelper(this);
            ArrayList<HashMap<String, String>>amap =rd.lastpri();



            String ams,ams1;
            ams=amap.toString();


            String ams4=ams.replace("[{=","");
            pri=ams4.replace("}]","");
            ArrayList<HashMap<String, String>>amap1 =rd.lastuser();
            ams1=amap1.toString();
            String ams5=ams1.replace("[{=","");
            String user =ams5.replace("}]","");

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // Toast.makeText ( this, ""+constant.mDate, Toast.LENGTH_SHORT ).show ( );
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


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        if (id== R.id.button3){

            Intent v=new Intent(this,Check_in.class);
            v.putExtra("ref","bl");
            startActivity(v);
            finish();
        }


        else if(id== R.id.button){

            Intent v=new Intent(this,Check_out.class);
            v.putExtra("ref","bl");
            startActivity(v);
            finish();

        }

        else if(id== R.id.button6){



            Intent v=new Intent(this,Vehicle_report.class);
            v.putExtra("ref","bl");
            startActivity(v);


        }

        else if(id== R.id.button20){

            if (bt.equals ( "1" )){

                process.setVisibility(View.VISIBLE);//change here

                syz ();
                syzupdate ();


                bt="0";
            }}



        else if(id== R.id.button12){
            //    Toast.makeText ( this, ""+constant.mDate, Toast.LENGTH_SHORT ).show ( );

            try {
                findBT();
                openBT();
                sendData1();
            } catch (IOException e) {
                e.printStackTrace();
            }





            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        closeBT();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }



                }
            }, 1500);

        }

        else if(id== R.id.button11){

            try {
                findBT();
                openBT();
                sendData();
            } catch (IOException e) {
                e.printStackTrace();
            }



            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        closeBT();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }



                }
            }, 2000);
        }

    }



    void d1(){
        Intent v=new Intent(this,Add_Token.class);
        startActivity(v);
    }

    void d2(){
        Intent v=new Intent(this,Add_printer.class);
        startActivity(v);
    }

    void findBT() {

        try {
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

            if(mBluetoothAdapter == null) {
                Toast.makeText(this," Bluetooth Not Found",Toast.LENGTH_LONG).show();
            }

            if(!mBluetoothAdapter.isEnabled()) {
                Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBluetooth, 0);
            }

            Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

            if(pairedDevices.size() > 0) {
                for (BluetoothDevice device : pairedDevices) {

                    // RPP300 is the name of the bluetooth printer device
                    // we got this name from the list of paired devices
                    if (device.getName().equals(pri)) {

                        mmDevice = device;
                        break;
                        // Printer001#DC:0D:30:20:46:90
                        //"BTprinter8269
                    }
                }
            }

            // myLabel.setText("Bluetooth device found.");

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    void beginListenForData() {
        try {
            final Handler handler = new Handler();

            // this is the ASCII code for a newline character
            final byte delimiter = 10;

            boolean stopWorke = false;
            readBufferPosition = 0;
            readBuffer = new byte[1024];

            workerThread = new Thread(new Runnable() {
                public void run() {

                    while (!Thread.currentThread().isInterrupted() && !stopWorker) {

                        try {

                            int bytesAvailable = mmInputStream.available();

                            if (bytesAvailable > 0) {

                                byte[] packetBytes = new byte[bytesAvailable];
                                mmInputStream.read(packetBytes);

                                for (int i = 0; i < bytesAvailable; i++) {

                                    byte b = packetBytes[i];
                                    if (b == delimiter) {

                                        byte[] encodedBytes = new byte[readBufferPosition];
                                        System.arraycopy(
                                                readBuffer, 0,
                                                encodedBytes, 0,
                                                encodedBytes.length
                                        );

                                        // specify US-ASCII encoding
                                        final String data = new String(encodedBytes, "US-ASCII");
                                        readBufferPosition = 0;

                                        // tell the user data were sent to bluetooth printer device
                                        handler.post(new Runnable() {
                                            public void run() {
                                                //  myLabel.setText(data);
                                            }
                                        });

                                    } else {
                                        readBuffer[readBufferPosition++] = b;
                                    }
                                }
                            }

                        } catch (IOException ex) {
                            stopWorker = true;
                        }

                    }
                }
            });

            workerThread.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void sendData() throws IOException {
        try {
            DBhelper rd= new DBhelper(this);
            ArrayList<HashMap<String, String>> amap =rd.last();

            String sno1=amap.get(0).toString();



            String sno2=sno1.replace("{=","");
            String Sno=sno2.replace("}","");

            ArrayList<HashMap<String, String>>amap1 =rd.last1(Sno);
            String vno1=amap1.get(0).toString();
            String vno2=vno1.replace("{=","");
            String Vno=vno2.replace("}","");


            ArrayList<HashMap<String, String>>vty =rd.last2(Sno);
            String vty1=vty.get(0).toString();
            String vty2=vty1.replace("{=","");
            String    Vty=vty2.replace("}","");


            // Toast.makeText(this,"open "+Sno+Vno,Toast.LENGTH_SHORT).show();
            //   String msg = myTextbox.getText().toString();
            Calendar cal= Calendar.getInstance();
            String y= String.format("%02d",cal.get(Calendar.YEAR));
            String m= String.format("%02d",cal.get(Calendar.MONTH)+1);
            String d= String.format("%02d",cal.get(Calendar.DATE));
            String h= String.format("%02d",cal.get(Calendar.HOUR));
            String hh= String.format("%02d",cal.get(Calendar.HOUR_OF_DAY));
            String mm= String.format("%02d",cal.get(Calendar.MINUTE));
            int am=cal.get(Calendar.AM_PM);




            if (am==0){ am1="am";}
            else{am1="pm";}

            String out="dd-MM-yy";
            SimpleDateFormat out1=new SimpleDateFormat(out);

            String msg="       "+constant.head+"\n" ;
            msg +="       "+constant.head2+"\n";
            msg += "----------------------------------";
            msg += "\n";
            msg +="         >>Park In<<\n";
            msg += "----------------------------------";
            msg += "\n";

            msg +="Date  :"+d+"/"+m+"/"+y+" "+"Time:"+""+h  +":"+mm+":"+am1+"\n";
            msg +="Token No    :"+Sno+"\n";
            msg +="Vehicle No  :"+Vno+"\n";
            msg +="Vehicle Type:"+Vty+"\n";


          /*   msg += "--------------------------------";
            msg += "\n";
            msg += "Item         GST Qty Rate Amount"+"\n";
            msg += "--------------------------------\n";
            for (int i=0;i<amap1.size();i++){
                String ams;
                ams=amap1.get(i).toString();


                String ams4=ams.replace("[","");
                String ams5=ams4.replace("]","");
                msg +=""+ams5+"\n\n";

                //    mmOutputStream.write(msg.getBytes());

            }
            msg += "--------------------------------";
            msg += "\n";

            msg +="                    Amount:" +am6+"\n";
            msg +="                      SGST:"+s+"\n";
            msg +="                      CGST:"+s+"\n";
            msg += "--------------------------------";
            msg +="                NETAmount:" +e+"\n";


            msg += "--------------------------------";
            msg += "\n";
            msg += "           GST Smmary\n";
            msg += "GST%       SGST      CGST   AMOUNT";*/



          /*  for (int i=0;i<amap2.size();i++){

                String ams;
                ams=amap2.get(i).toString();


                String ams4=ams.replace("[","");
                String ams5=ams4.replace("]","");
                msg +=""+ams5+"\n\n";
                msg +=""+amap2.get(i)+"\n";

                //     mmOutputStream.write(msg.getBytes());

            }*/
            msg += "\n";
            msg += "          Have Nice Day  \n\n\n      ";
            msg += "\n\n\n\n";
            format[2] =((byte)(0x6 | arrayOfByte1[2]));
            // format[2] =((byte)(0x10 | arrayOfByte1[2]));
            mmOutputStream.write(format);
            mmOutputStream.write(msg.getBytes());






        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void closeBT() throws IOException {
        try {
            stopWorker = true;
            mmOutputStream.close();
            mmInputStream.close();
            mmSocket.close();
            //   bluetoothadapter.disable();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    String openBT() throws IOException {
        try {

            // Standard SerialPortService ID
            UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
            mmSocket = mmDevice.createRfcommSocketToServiceRecord(uuid);
            mmSocket.connect();

            mmOutputStream = mmSocket.getOutputStream();
            Thread.sleep(1000);
            mmInputStream = mmSocket.getInputStream();

            beginListenForData();



        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    void sendData1() throws IOException {
        try {



            DBhelper rd= new DBhelper(this);
            ArrayList<HashMap<String, String>>amap0 =rd.lastbillsno();


            ArrayList<HashMap<String, String>>billno =rd.lastbill();

            String bno1=billno.get(0).toString();



            String bno2=bno1.replace("{=","");
            String bno3 =bno2.replace("}","");

            String bsno1=amap0.get(0).toString();



            String bsno2=bsno1.replace("{=","");
            no=bsno2.replace("}","");



            rd.lastout_pri(no);


            // Toast.makeText(this,"open "+Sno+Vno,Toast.LENGTH_SHORT).show();
            //   String msg = myTextbox.getText().toString();
            Calendar cal= Calendar.getInstance();
            int y=cal.get(Calendar.YEAR);
            int m=cal.get(Calendar.MONTH)+1;
            int d=cal.get(Calendar.DATE);
            int h=cal.get(Calendar.HOUR);
            int mm=cal.get(Calendar.MINUTE);
            int am=cal.get(Calendar.AM_PM);




            if (am==0){ am1="am";}
            else{am1="pm";}

            String out="dd-MM-yy";
            SimpleDateFormat out1=new SimpleDateFormat(out);

            String msg="          "+constant.head+"\n";
            msg +="       "+constant.head2+"\n";
            msg += "----------------------------------";
            msg += "\n";
            msg +="         <<Parkout>>\n";
            msg += "----------------------------------";
            msg += "\n";

            msg +="Date  :"+d+"/"+m+"/"+y+" "+"Time:"+""+h+":"+mm+":"+am1+"\n";
            msg +="Token No    :"+constant.l_tno+"    S:No:"+bno3+"\n";
            msg +="Vehicle No  :"+constant.lvno+"\n";
            msg +="No Of days  :"+constant.l_day+"\n";
            msg +="Vehicle Type:"+constant.l_vtype+"\n"+"Amount      :"+constant.l_amount+"\n";



            msg += "            "+constant.foot+"  \n\n\n      ";
            msg += "\n\n\n\n";
            format[2] =((byte)(0x6 | arrayOfByte1[2]));
            // format[2] =((byte)(0x10 | arrayOfByte1[2]));
            mmOutputStream.write(format);
            mmOutputStream.write(msg.getBytes());






        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    void syzupdate(){
        //  Toast.makeText ( this, "dd"+mbno, Toast.LENGTH_SHORT ).show ( );
        DBhelper rd= new DBhelper(this);
        SQLiteDatabase arg0=rd.getReadableDatabase();



        Cursor c=null;
        c=arg0.rawQuery(" SELECT   vechicleno,vtype,datein,timein,dateoutt,timeout,No_Days_Or_Hour,Total_Amount,sno,cid,zid,remark FROM sss where remark='close' AND  sno <=" +mbno,null);


        if(c.moveToFirst())
        {
            do
            {




                String vno=(c.getString(0));
                String  v_type= (c.getString(1)  );
                String in_date=( c.getString(2) );
                String  in_time= ( c.getString(3) );
                String out_date= ( c.getString(4) );
                String out_time= ( c.getString(5) );
                String no_days=( c.getString(6) );
                String tamount= ( c.getString(7) );
                String tokenno= ( c.getString(8) );
                String cid= ( c.getString(9) );
                String zid= ( c.getString(10) );
                String remark= ( c.getString(11) );
                regupdate(tokenno,v_type,in_date,out_date,in_time,out_time,tamount,tamount,no_days,zid,remark,vno,cid,zid );

            }while(c.moveToNext());
            // Toast.makeText ( this, "Synchronized Successfully...", Toast.LENGTH_SHORT ).show ( );


        }
        else {  process.setVisibility(View.GONE);
            finish ();}



    }

    private void regupdate(final String tno, final String vtype, final String datein, final String dateout,
                           final String timein, final String timeout, final String amount, final String tamount,
                           final String nodays, final String zoinid, final String remark, final String vno, final String Cid, final String Zid){



        //  Toast.makeText(this,"ii"+tno+"",Toast.LENGTH_LONG).show();

        Log.e(TAG,"log::"+tno);
        StringRequest stringRequest = new StringRequest( Request.Method.POST,constant.URL_Datasyn_update,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Toast.makeText ( Home_Page.this, "sys"+response, Toast.LENGTH_SHORT ).show ( );

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {;

            }
        }){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", mapk);
                return headers;
            }
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("sno","null");
                params.put("tno",tno);
                params.put("vtype",vtype);
                params.put("datein",datein);
                params.put("dateout",dateout);
                params.put("timein",timein);
                params.put("timeout",timeout);
                params.put("tamount",tamount);
                params.put("No_days",nodays);
                params.put("zoinid",zoinid);
                params.put("remark",remark);
                params.put("V_no",vno);
                params.put("amount",amount);

                params.put("attendeby",username);
                params.put("cid",Cid);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        process.setVisibility(View.GONE);//change here






    }


    void syz(){

        DBhelper rd= new DBhelper(this);
        SQLiteDatabase arg0=rd.getReadableDatabase();



        Cursor c=null;
        c=arg0.rawQuery(" SELECT   vechicleno,vtype,dateinn,timein,dateoutt,timeout,No_Days_Or_Hour,Total_Amount,sno,cid,zid,remark FROM sss where sno >" +mbno,null);


        if(c.moveToFirst())
        {
            do
            {

                String vno=(c.getString(0));



                String  v_type= (c.getString(1)  );
                String in_date=( c.getString(2) );
                String  in_time= ( c.getString(3) );
                String out_date= ( c.getString(4) );
                String out_time= ( c.getString(5) );
                String no_days=( c.getString(6) );
                String tamount= ( c.getString(7) );
                String tokenno= ( c.getString(8) );
                String cid= ( c.getString(9) );
                String zid= ( c.getString(10) );
                String remark= ( c.getString(11) );
                reg (tokenno,v_type,in_date,out_date,in_time,out_time,tamount,tamount,no_days,zid,remark,vno,cid,zid );

            }while(c.moveToNext());
            Toast.makeText ( this, "Synchronized Successfully...", Toast.LENGTH_SHORT ).show ( );
            if (mbno.equals ( "0" )){finish ();}

        }
        else { Toast.makeText ( this, "Synchronized Successfully...!", Toast.LENGTH_SHORT ).show ( );
            process.setVisibility(View.GONE);}



    }

    private void reg(final String tno, final String vtype, final String datein, final String dateout,
                     final String timein, final String timeout, final String amount, final String tamount,
                     final String nodays, final String zoinid, final String remark, final String vno, final String Cid, final String Zid){



        // Toast.makeText(this,""+tno+"",Toast.LENGTH_LONG).show();



        StringRequest stringRequest = new StringRequest( Request.Method.POST,constant.URL_Datasyn,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Toast.makeText ( Home_Page.this, "s2"+response, Toast.LENGTH_SHORT ).show ( );

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {;

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", mapk);
                return headers;
            }


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("sno","null");
                params.put("tno",tno);
                params.put("vtype",vtype);
                params.put("datein",datein);
                params.put("dateout",dateout);
                params.put("timein",timein);
                params.put("timeout",timeout);
                params.put("tamount",tamount);
                params.put("No_days",nodays);
                params.put("zoinid",zoinid);
                params.put("remark",remark);
                params.put("V_no",vno);
                params.put("amount",amount);

                params.put("attendeby",username);
                params.put("cid",Cid);
                // params.put("zid",Zid);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        process.setVisibility(View.GONE);//change here






    }
    void toast(){Toast.makeText ( this, "Synchronized Successfully...", Toast.LENGTH_SHORT ).show ( );}

    private void loadIntoListView(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        String[] STbno1 = new String[jsonArray.length()];
        String[] STzoenidar = new String[jsonArray.length()];
        String[] STstatusar = new String[jsonArray.length()];
        String[] STcid = new String[jsonArray.length()];

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            STbno1[i] = obj.getString("bno");



        }



        int no=0;
        try {


            bno=STbno1[0];
            if (bno.equals ( "null" )){bno="0";}
            Toast.makeText ( this, "bnow:"+bno, Toast.LENGTH_SHORT ).show ( );

        }catch (Exception e) {



        }
    }









    void bno(){

        //  Toast.makeText ( this, ""+mapk, Toast.LENGTH_SHORT ).show ( );
        StringRequest request =new StringRequest ( StringRequest.Method.POST, constant.URL_T_bno, new Response.Listener<String> ( ) {
            @Override
            public void onResponse(String response) {

                try {
                    loadIntoListView_bno ( "["+response+"]" );
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

    private void loadIntoListView_bno(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);

        String[] Bno = new String[jsonArray.length()];






        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            Bno[i] = obj.getString("billno");


        }
        for (int i = 0; i < jsonArray.length(); i++) {

            mbno=Bno[i];
            if (mbno.equals ( "null" )){mbno="0";}
            //Toast.makeText ( this, "bno"+Bno[i], Toast.LENGTH_LONG ).show ( );

        }





    }


}



