package com.skyparking.admin.parkingappemapp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class PendingVehicle extends AppCompatActivity implements AdapterView.OnItemClickListener,View.OnClickListener {
    DBhelper rd= new DBhelper(this);
    BluetoothAdapter mBluetoothAdapter;
    BluetoothSocket mmSocket;
    BluetoothDevice mmDevice;
    long Delay = 4000;
    OutputStream mmOutputStream;
    InputStream mmInputStream;
    Thread workerThread;
    Button bt1;
    byte[] format = { 27, 33, 0 };
    byte[] arrayOfByte1 = { 27, 33, 0 };

    byte[] readBuffer;
    int readBufferPosition;
    volatile boolean stopWorker;

    ListView lv;
    SearchView sv;
    String Datefrom,Dateto,pri,tv;
    int no_list;
    Button Btfilter,Btexcel,Btprint;
    ArrayList<String> list =new ArrayList<String>();
    ArrayList<String> v_type =new ArrayList<String>();
    ArrayList<String> in_date =new ArrayList<String>();
    ArrayList<String> in_time =new ArrayList<String>();
    ArrayList<String> out_date =new ArrayList<String>();
    ArrayList<String> out_time =new ArrayList<String>();
    ArrayList<String> no_days =new ArrayList<String>();
    ArrayList<String> amount =new ArrayList<String>();
    ArrayList<String> tokenno =new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate ( savedInstanceState );
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView ( R.layout.activity_pending_vehicle );
        Btfilter=findViewById ( R.id.button19 );
        Btfilter.setOnClickListener ( this );
        Btexcel=findViewById ( R.id.button21);
        Btexcel.setOnClickListener ( this );
        Btprint=findViewById ( R.id.button23);
        Btprint.setOnClickListener ( this );

        lv=(ListView)findViewById(R.id.listview);
        lv.setOnItemClickListener(this);
        sv=findViewById ( R.id.searchview );
        Datefrom=getIntent().getExtras().getString("Date_from");
        Dateto=getIntent().getExtras().getString("Date_to");

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                lisr1 ( newText );

                return false;
            }
        });

        ArrayList<HashMap<String, String>>pending_vehical =rd.total_pendig_val (Datefrom,Dateto  );
        String ams11a;
        ams11a=pending_vehical.get ( 0 ).toString ();

        String ams41=ams11a.replace("{=","");
        tv=ams41.replace("}","");

        lisr();

        ArrayList<HashMap<String, String>>amap =rd.lastpri();



        try{
            String ams;
            ams=amap.toString();

            String ams4=ams.replace("[{=","");
            pri=ams4.replace("}]","");

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
    void lisr(){

        DBhelper rd= new DBhelper(this);
        SQLiteDatabase arg0=rd.getReadableDatabase();



        Cursor c=null;
        c=arg0.rawQuery(" SELECT   vechicleno,vtype,datein,timein,dateout,timeout,No_Days_Or_Hour,Total_Amount,sno FROM sss where remark='open'  AND dateinn BETWEEN '" + Datefrom + "' AND '" + Dateto+ "' ORDER BY sno ASC",null);


        if(c.moveToFirst())
        {
            do
            {

                list.add("V_NO:"+c.getString(0));
                v_type.add ("V_Type:"+c.getString(1)  );
                in_date.add ( "IN :"+c.getString(2) );
                in_time.add ( c.getString(3) );
                tokenno.add ( "Token NO : "+c.getString(8) );
            }while(c.moveToNext());
            no_list=list.size ();
        }
        if(list.isEmpty ()){
            Toast.makeText ( this, "Null Data  !!", Toast.LENGTH_SHORT ).show ( );
        }
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String> (this,R.layout.rep,list);


        AppointmentAdapter adapter = new AppointmentAdapter ();
        lv.setAdapter(adapter);


    }

    class AppointmentAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return no_list;
        }

        @Override
        public Object getItem(int position) {
            return no_list;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = null;

            if (convertView == null) {
                try{
                    convertView= getLayoutInflater().inflate(R.layout.listvw,null);

                    TextView textView13=convertView.findViewById(R.id.textView26);
                    TextView textView14=convertView.findViewById(R.id.textView);
                    TextView textView6=convertView.findViewById(R.id.textView6);
                    TextView indate=convertView.findViewById(R.id.textView14);
                    TextView intime=convertView.findViewById(R.id.textView);

                    TextView mtoken=convertView.findViewById(R.id.textView19);

                    textView13.setText( list.get ( position ) );
                    textView6.setText (v_type.get(position) );
                    indate.setText (in_date.get(position) );
                    intime.setText (in_time.get(position) );

                    mtoken.setText ( tokenno.get ( position ) );}catch (Exception e){
                    Toast.makeText ( PendingVehicle.this, "Null Data...!", Toast.LENGTH_SHORT ).show ( );
                };
            }
            return convertView;
        }
    }



    @Override
    public void onClick(View view) {


        int id = view.getId();
        if (id== R.id.button19){

            Intent v=new Intent(this,Date_select.class);

            v.putExtra("report","pr");//cash report
            startActivity(v);
            fileList ();
        }


        else if(id== R.id.button21){

            report_excel ();

        }

        else if(id== R.id.button23){
            findBT();
            Toast.makeText(this," Print . . . . ",Toast.LENGTH_SHORT).show();


            try {
                openBT();
                // Toast.makeText(this,"open ",Toast.LENGTH_SHORT).show();
                sendData();


            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return;
            }




            Timer RunSplash = new Timer();

            TimerTask ShowSplash = new TimerTask() {
                @Override
                public void run() {
                    try {
                        closeBT();

                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }




                }
            };
            RunSplash.schedule(ShowSplash, Delay);


        }
    }

    void report_excel(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Enter The File Name");

        LayoutInflater inflater = getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.alt, null);
        final EditText userAnswer = (EditText) dialoglayout.findViewById(R.id.editText);

        builder.setNegativeButton("ok", new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                String filename=userAnswer.getText().toString();


                if (filename.isEmpty ())
                {
                    err ();
                }
                else
                {



                    report3 ( filename,Datefrom,Dateto );


                }


            }
        });




        builder.setView(dialoglayout);

        builder.show();

    }

    void report3(String file_name,String date_from,String date_to){
        Toast.makeText ( this, ""+file_name+".csv Saved", Toast.LENGTH_SHORT ).show ( );
        Calendar cal= Calendar.getInstance();
        int y=cal.get(Calendar.YEAR);
        int m=cal.get(Calendar.MONTH)+1;
        int d=cal.get(Calendar.DATE);
        String out="dd-MM-yy";
        SimpleDateFormat out1=new SimpleDateFormat(out);


        DBhelper rd= new DBhelper(this);
        SQLiteDatabase arg0=rd.getReadableDatabase();

        final ArrayList<String> list =new ArrayList<String>();
        Cursor c=null;
        //c=arg0.rawQuery("SELECT* FROM reg1 WHERE   sno = (SELECT MAX(sno)  FROM reg1)",null);
        c=arg0.rawQuery(" SELECT  DISTINCT vechicleno,timein,datein FROM sss where remark='open'  AND datein BETWEEN '" + date_from + "' AND '" + date_to+ "' ORDER BY datein ASC",null);


        try{
            int rowcount = 0;
            int colcount = 0;

            File sdCardDir = Environment.getExternalStorageDirectory();

            String filee=file_name.toString ()+".csv";
            String filename = filee;

            // the name of the file to export with

            File saveFile = new File(sdCardDir, filename);

            FileWriter fw = new FileWriter(saveFile);


            BufferedWriter bw = new BufferedWriter(fw);
            rowcount = c.getCount();

            colcount = c.getColumnCount();

            if (rowcount > 0) {

                c.moveToFirst();



                for (int i = 0; i < colcount; i++) {

                    if (i != colcount - 1) {



                        bw.write(c.getColumnName(i) + ",");



                    } else {



                        bw.write(c.getColumnName(i));



                    }

                }

                bw.newLine();



                for (int i = 0; i < rowcount; i++) {

                    c.moveToPosition(i);



                    for (int j = 0; j < colcount; j++) {

                        if (j != colcount - 1)

                            bw.write(c.getString(j) + ",");

                        else

                            bw.write(c.getString(j));

                    }

                    bw.newLine();

                }

                bw.flush();

            }

        } catch(Exception ex) {





        } finally{}

        if(c.moveToFirst())
        {
            do
            {


                list.add(""+c.getString(0)+"Rs:"+c.getString(1));




            }while(c.moveToNext());
        }
        //Log.e("ArrayList",amap.get();


    }
    void err(){   Toast.makeText ( this, "From Date Field Is Empty", Toast.LENGTH_SHORT ).show ( );}

    void sendData() throws IOException {
        try {






            //   String msg = myTextbox.getText().toString();
            Calendar cal= Calendar.getInstance();
            int y=cal.get(Calendar.YEAR);
            int m=cal.get(Calendar.MONTH)+1;
            int d=cal.get(Calendar.DATE);
            int h=cal.get(Calendar.HOUR);
            int mm=cal.get(Calendar.MINUTE);




            String out="dd-MM-yy";
            SimpleDateFormat out1=new SimpleDateFormat(out);

            String msg="       "+constant.head+"\n" ;
            msg +="       "+constant.head2+"\n";
            msg += "\n";
            //msg +="                    Palani";
            msg += "\n";
            msg +="\t\t\t\tPending Report\n\n";
            msg += "----------------------------------"+"\n";

            msg +="Date  :"+d+"/"+m+"/"+y+" Tim "+h+":"+mm+"\n"+"\n";
            msg +="Total Vehicles  :"+tv+"\n"+"\n";
            msg += "----------------------------------";
            msg += "\n";
            msg += "T_NO     V_NO   Date&Time"+"\n";
            msg += "----------------------------------"+"\n";

            ArrayList<HashMap<String, String>>amap1 =rd.pendingreport(Datefrom,Dateto);


            for (int i=0;i<amap1.size();i++){
                String ams="";
                ams +=amap1.get(i);
                String ams4=ams.replace("[","");
                String ams5=ams4.replace("]","");
                String ams6=ams5.replace(",","");
                msg +=""+ams6+"\n\n";
                //   msg +=""+amap1.get(i)+"\n\n";

                //    mmOutputStream.write(msg.getBytes());

            }
            msg += "----------------------------------"+"\n";
            //  msg += "                      Total Amount   :"+amount33+"\n";


            msg += "\n\n\n\n";
            format[2] =((byte)(0x6 | arrayOfByte1[2]));
            // format[2] =((byte)(0x10 | arrayOfByte1[2]));
            mmOutputStream.write(format);
            mmOutputStream.write(msg.getBytes());



            // Toast.makeText(this,"send",Toast.LENGTH_SHORT).show();



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
    void openBT() throws IOException {
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
    }


    void findBT() {

        try {
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

            if(mBluetoothAdapter == null) {

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

            stopWorker = false;
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


    void lisr1(String searchTerm){
        list.clear ();v_type.clear ();in_time.clear ();in_date.clear ();out_date.clear ();out_time.clear ();no_days.clear ();
        amount.clear ();tokenno.clear ();

        DBhelper rd= new DBhelper(this);
        SQLiteDatabase arg0=rd.getReadableDatabase();



        Cursor c=null;
        c=arg0.rawQuery(" SELECT  DISTINCT vechicleno,vtype,datein,timein,dateout,timeout,No_Days_Or_Hour,Total_Amount,sno FROM sss where vechicleno Like"+"'%"+searchTerm+"%'" ,null);


        if(c.moveToFirst())
        {
            do
            {
                list.add("V_NO:"+c.getString(0));
                v_type.add ("V_Type:"+c.getString(1)  );
                in_date.add ( "IN :"+c.getString(2) );
                in_time.add ( c.getString(3) );
                tokenno.add ( "Token NO : "+c.getString(8) );





            }while(c.moveToNext());

            no_list=list.size ();
        }


        PendingVehicle.AppointmentAdapter adapter = new PendingVehicle.AppointmentAdapter ();
        lv.setAdapter(adapter);


    }


}



