package com.skyparking.admin.parkingappemapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by admin on 11/16/2017.
 */

public class DBhelper extends SQLiteOpenHelper {	public DBhelper(Context context) {

    super(context,DATABASE_NAME, null,DATABASE_VERSION);     }
    String vechicleno="vechicleno";
    private String TABLE_NAME ="sss";
    private String TABLE_NAME1 ="token2";
    private String TABLE_NAME10="printername";
    private String TABLE_NAME11="users";
    private String TABLE_NAME12="msg";
    //  private String TABLE_NAME2="stock";

    //  private String TABLE_NAME8="address";

    static final String DATABASE_NAME="tokendk.db";
    private static final int DATABASE_VERSION=25;//
    private String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+"(sno INTEGER PRIMARY KEY AUTOINCREMENT,vechicleno VARCHAR(20),datein  DATETIME default current_timestamp,dateout  DATETIME default current_timestamp,timein VARCHAR(20),timeout VARCHAR(20),amount VARCHAR(15),No_Days_Or_Hour VARCHAR(15),remark VARCHAR(15),dateoutt VARCHAR(15),dateinn VARCHAR(15),Total_Amount VARCHAR(15),vtype VARCHAR(15),bno VARCHAR(15),cid VARCHAR(15),zid VARCHAR(15))";
    private String CREATE_TABLE1="CREATE TABLE "+TABLE_NAME1+"(sno INTEGER PRIMARY KEY AUTOINCREMENT,vechicleno VARCHAR(20),validity VARCHAR(20),amount VARCHAR(20),sub_seq_hour VARCHAR(20),sub_seq_amount VARCHAR(20))";
    // private String CREATE_TABLE2="CREATE TABLE "+TABLE_NAME2+"(sno INTEGER PRIMARY KEY AUTOINCREMENT,item VARCHAR(20),stock VARCHAR(20))";
    private String CREATE_TABLE10="CREATE TABLE "+TABLE_NAME10+"(sno INTEGER PRIMARY KEY AUTOINCREMENT,printer  VARCHAR(20))";
    private String CREATE_TABLE11="CREATE TABLE "+TABLE_NAME11+"(sno INTEGER PRIMARY KEY AUTOINCREMENT,user VARCHAR(20))";
    private String CREATE_TABLE12="CREATE TABLE "+TABLE_NAME12+"(sno INTEGER PRIMARY KEY AUTOINCREMENT,head1 VARCHAR(20),head2 VARCHAR(20),foot1 VARCHAR(20),foot2 VARCHAR(20))";
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE1);
        db.execSQL(CREATE_TABLE10);
        db.execSQL(CREATE_TABLE11);
        db.execSQL(CREATE_TABLE12);
        // arg0.execSQL(CREATE_TABLE2);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        db.execSQL ("DROP TABLE IF EXISTS "+ TABLE_NAME);
        db.execSQL ("DROP TABLE IF EXISTS "+ TABLE_NAME1);
        db.execSQL ("DROP TABLE IF EXISTS "+ TABLE_NAME10);
        db.execSQL ("DROP TABLE IF EXISTS "+ TABLE_NAME11);
        db.execSQL ("DROP TABLE IF EXISTS "+ TABLE_NAME12);
        //  arg0.execSQL("DROP TABLE"+TABLE_NAME2);

    }

    public long insertdb1(String Vno, String Datein, String Dateout, String Timein, String Timeout, String Amount, String Days, String Remark, String Dateinn, String Dateoutt, String Tamount, String Vtype, String Bno,String Cid,String Zid)
    {



        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content=new ContentValues();
        content.put("vechicleno",Vno);
        content.put("datein",Datein);
        content.put("dateout",Dateout);
        content.put("timein",Timein);
        content.put("timeout",Timeout);
        content.put("amount",Amount);
        content.put("No_Days_Or_Hour",Days);
        content.put("remark",Remark);
        content.put("dateinn",Dateinn);
        content.put("dateoutt",Dateoutt);
        content.put("Total_Amount",Tamount);
        content.put("vtype",Vtype);
        content.put("bno",Bno);
        content.put("cid",Cid);
        content.put("zid",Zid);




        return db.insert(TABLE_NAME,null,content);
    }
    public long insert_head(String head, String head2, String foot1, String foot2)
    {



        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content=new ContentValues();
        content.put("head1",head);
        content.put("head2",head2);
        content.put("foot1",foot1);
        content.put("foot2",foot2);





        return db.insert(TABLE_NAME12,null,content);
    }

    public long insertdb2(String vtype,String validity,String  Amount,String s_hour,String s_amount)
    {



        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content=new ContentValues();
        content.put("vechicleno",vtype);
        content.put("amount",Amount);
        content.put("sub_seq_hour",s_hour);
        content.put("sub_seq_amount",s_amount);
        content.put("validity",validity);






        return db.insert(TABLE_NAME1,null,content);
    }
    public long updatesss(String Remark, String Dateout, String Timeout, String vno, String Dateoutt, Float Amount, int Day, long tom,int Bno,String userout)
    {
        SQLiteDatabase arg0=this.getWritableDatabase();
        ContentValues content=new ContentValues();
        content.put("remark",Remark);
        content.put("timeout",Timeout);
        content.put("dateout",Dateout);
        content.put("dateoutt",Dateoutt);
        content.put("amount",Amount);
        content.put("No_Days_Or_Hour",Day);
        content.put("Total_Amount",tom);
        content.put("bno",Bno);

        // content.put("Phno",e);

        return arg0.update(TABLE_NAME  ,content,"vechicleno=? AND remark= 'open'",new String[] {vno} );


    }


    public long updatessssno(String Remark, String Dateout, String Timeout, String vno, String Dateoutt, Float Amount, int Day, long tom,int Bno,String userout)
    {
        SQLiteDatabase arg0=this.getWritableDatabase();
        ContentValues content=new ContentValues();
        content.put("remark",Remark);
        content.put("timeout",Timeout);
        content.put("dateout",Dateout);
        content.put("dateoutt",Dateoutt);
        content.put("amount",Amount);
        content.put("No_Days_Or_Hour",Day);
        content.put("Total_Amount",tom);
        content.put("bno",Bno);

        // content.put("Phno",e);

        return arg0.update(TABLE_NAME  ,content,"sno=? AND remark= 'open' ",new String[] {vno} );


    }

    public long updatesss1(String vno,String Dateoutt)
    {
        SQLiteDatabase arg0=this.getWritableDatabase();
        ContentValues content=new ContentValues();

        content.put("dateoutt",Dateoutt);
        // content.put("Phno",e);

        return arg0.update(TABLE_NAME  ,content,"vechicleno=?",new String[] {vno} );


    }

    public long updatesss2(String vno,String Dateoutt)
    {
        SQLiteDatabase arg0=this.getWritableDatabase();
        ContentValues content=new ContentValues();

        content.put("dateoutt",Dateoutt);
        // content.put("Phno",e);

        return arg0.update(TABLE_NAME  ,content,"sno=?",new String[] {vno} );


    }

    public ArrayList Selectroute( String vnoo,String Vno)
    {
        SQLiteDatabase arg0=this.getReadableDatabase();
        ArrayList amap = new ArrayList();

        Cursor c=null;

        c=arg0.rawQuery("SELECT  ((strftime('%s',dateoutt) - strftime('%s',dateinn))/60/60) FROM sss where remark =\"open\"  AND  ( vechicleno = "+ "'"+vnoo+"'" + " OR sno = "+"'"+vnoo+"'"+")" ,null);
        //  day //c=arg0.rawQuery("SELECT  ((strftime('%s',dateoutt) - strftime('%s',dateinn))/3600/24) FROM sss where remark =\"open\"  AND  ( vechicleno = "+ "'"+vnoo+"'" + " OR sno = "+"'"+vnoo+"'"+")" ,null);
        if(c.moveToFirst())
        {
            do
            {
                HashMap<String,String> map=new HashMap<String,String>();
                //.Log.e("Status",c.getString(0));

                map.put("",c.getString(0));
                //Log.e("Map",map.get("Name"));
                amap.add(map);
            }while(c.moveToNext());
        }
        //Log.e("ArrayList",amap.get();
        return amap;
    }

    public ArrayList Selectamount( String vnoo)
    {
        SQLiteDatabase arg0=this.getReadableDatabase();
        ArrayList amap = new ArrayList();

        Cursor c=null;

        c=arg0.rawQuery("SELECT  amount FROM sss where remark =\"open\"  AND ( vechicleno = "+"'"+vnoo+"'" + " OR sno ="+"'"+vnoo+"'"+")" ,null);

        if(c.moveToFirst())
        {
            do
            {
                HashMap<String,String> map=new HashMap<String,String>();
                //.Log.e("Status",c.getString(0));

                map.put("",c.getString(0));
                //Log.e("Map",map.get("Name"));
                amap.add(map);
            }while(c.moveToNext());
        }
        //Log.e("ArrayList",amap.get();
        return amap;
    }


    public ArrayList Select_HEAD_FOOT( )
    {
        SQLiteDatabase arg0=this.getReadableDatabase();
        ArrayList amap = new ArrayList();

        Cursor cursor=null;

        cursor=arg0.rawQuery("SELECT  * FROM msg WHERE   sno = (SELECT MAX(sno)  FROM msg) " ,null);

        if(cursor.moveToFirst())
        {
            do
            {

                constant.head = cursor.getString(cursor.getColumnIndex("head1"));
                constant.head2 = cursor.getString(cursor.getColumnIndex("head2"));
                constant.foot = cursor.getString(cursor.getColumnIndex("foot1"));
                constant.foot1 = cursor.getString(cursor.getColumnIndex("foot2"));
            }while(cursor.moveToNext());
        }
        //Log.e("ArrayList",amap.get();
        return amap;
    }

    public ArrayList lastout_pri(String vnoo )
    {
        SQLiteDatabase arg0=this.getReadableDatabase();
        ArrayList amap = new ArrayList();

        Cursor cursor=null;

        cursor=arg0.rawQuery("SELECT * FROM sss WHERE  remark =\"close\"  AND ( vechicleno = "+"'"+vnoo+"'" + " OR sno ="+"'"+vnoo+"'"+")",null);

        if(cursor.moveToFirst())
        {
            do
            {

                constant.l_amount = cursor.getString(cursor.getColumnIndex("Total_Amount"));
                constant.l_day = cursor.getString(cursor.getColumnIndex("No_Days_Or_Hour"));
                constant.l_tno = cursor.getString(cursor.getColumnIndex("sno"));
                constant.lvno = cursor.getString(cursor.getColumnIndex("vechicleno"));
                constant.l_vtype = cursor.getString(cursor.getColumnIndex("vtype"));

            }while(cursor.moveToNext());
        }
        //Log.e("ArrayList",amap.get();
        return amap;
    }

    public ArrayList last()
    {
        SQLiteDatabase arg0=this.getReadableDatabase();
        ArrayList amap = new ArrayList();

        Cursor c=null;
        c=arg0.rawQuery("SELECT sno,vechicleno FROM sss WHERE   sno = (SELECT MAX(sno)  FROM sss)",null);
        //c=arg0.rawQuery("select * from "+TABLE_NAME,null);
        if(c.moveToFirst())
        {
            do
            {
                HashMap<String,String> map=new HashMap<String,String>();
                //.Log.e("Status",c.getString(0));
                map.put("",c.getString(0));




                //Log.e("Map",map.get("Name"));
                amap.add(map);
            }while(c.moveToNext());
        }
        //Log.e("ArrayList",amap.get();
        return amap;
    }

    public ArrayList lastbill()
    {
        SQLiteDatabase arg0=this.getReadableDatabase();
        ArrayList amap = new ArrayList();

        Cursor c=null;
        c=arg0.rawQuery("SELECT bno FROM sss WHERE   bno = (SELECT MAX(bno)  FROM sss)",null);
        //c=arg0.rawQuery("select * from "+TABLE_NAME,null);
        if(c.moveToFirst())
        {
            do
            {
                HashMap<String,String> map=new HashMap<String,String>();
                //.Log.e("Status",c.getString(0));
                map.put("",c.getString(0));




                //Log.e("Map",map.get("Name"));
                amap.add(map);
            }while(c.moveToNext());
        }
        //Log.e("ArrayList",amap.get();
        return amap;
    }


    public ArrayList lastbillsno()
    {
        SQLiteDatabase arg0=this.getReadableDatabase();
        ArrayList amap = new ArrayList();

        Cursor c=null;
        c=arg0.rawQuery("SELECT sno FROM sss WHERE   bno = (SELECT MAX(bno)  FROM sss)",null);
        //c=arg0.rawQuery("select * from "+TABLE_NAME,null);
        if(c.moveToFirst())
        {
            do
            {
                HashMap<String,String> map=new HashMap<String,String>();
                //.Log.e("Status",c.getString(0));
                map.put("",c.getString(0));




                //Log.e("Map",map.get("Name"));
                amap.add(map);
            }while(c.moveToNext());
        }
        //Log.e("ArrayList",amap.get();
        return amap;
    }

    public ArrayList lasttamo( String Sno)
    {
        SQLiteDatabase arg0=this.getReadableDatabase();
        ArrayList amap = new ArrayList();

        Cursor c=null;
        c=arg0.rawQuery("SELECT Total_Amount FROM sss WHERE   sno = "+"'"+Sno+"'" + " OR vechicleno = "+"'"+Sno+"'",null);
        //c=arg0.rawQuery("select * from "+TABLE_NAME,null);
        if(c.moveToFirst())
        {
            do
            {
                HashMap<String,String> map=new HashMap<String,String>();
                //.Log.e("Status",c.getString(0));
                map.put("",c.getString(0));




                //Log.e("Map",map.get("Name"));
                amap.add(map);
            }while(c.moveToNext());
        }
        //Log.e("ArrayList",amap.get();
        return amap;
    }

    public ArrayList last1( String vnoo)
    {
        SQLiteDatabase arg0=this.getReadableDatabase();
        ArrayList amap = new ArrayList();

        Cursor c=null;
        c=arg0.rawQuery("SELECT vechicleno FROM sss WHERE   remark =\"open\"   AND ( vechicleno = "+"'"+vnoo+"'" + " OR sno ="+"'"+vnoo+"'"+" )",null);

        if(c.moveToFirst())
        {
            do
            {
                HashMap<String,String> map=new HashMap<String,String>();
                //.Log.e("Status",c.getString(0));
                map.put("",c.getString(0));




                //Log.e("Map",map.get("Name"));
                amap.add(map);
            }while(c.moveToNext());
        }
        //Log.e("ArrayList",amap.get();
        return amap;
    }

    public ArrayList total_pendig_val(String DateFrom,String Dateto)
    {
        SQLiteDatabase arg0=this.getReadableDatabase();
        ArrayList amap = new ArrayList();

        Cursor c=null;
        c=arg0.rawQuery("SELECT count(*) FROM sss WHERE remark = 'open' AND dateinn BETWEEN '" + DateFrom + "' AND '" + Dateto+ "' ORDER BY  sno ASC",null);

        if(c.moveToFirst())
        {
            do
            {
                HashMap<String,String> map=new HashMap<String,String>();
                //.Log.e("Status",c.getString(0));
                map.put("",c.getString(0));




                //Log.e("Map",map.get("Name"));
                amap.add(map);
            }while(c.moveToNext());
        }
        //Log.e("ArrayList",amap.get();
        return amap;
    }

    public ArrayList last2( String vnoo)
    {
        SQLiteDatabase arg0=this.getReadableDatabase();
        ArrayList amap = new ArrayList();

        Cursor c=null;
        c=arg0.rawQuery("SELECT vtype FROM sss WHERE remark =\"open\"   AND ( vechicleno = "+"'"+vnoo+"'" + " OR sno ="+"'"+vnoo+"'"+" )",null);
        //c=arg0.rawQuery("select * from "+TABLE_NAME,null);
        if(c.moveToFirst())
        {
            do
            {
                HashMap<String,String> map=new HashMap<String,String>();
                //.Log.e("Status",c.getString(0));
                map.put("",c.getString(0));




                //Log.e("Map",map.get("Name"));
                amap.add(map);
            }while(c.moveToNext());
        }
        //Log.e("ArrayList",amap.get();
        return amap;
    }



    public ArrayList outdate( String vnoo)
    {
        SQLiteDatabase arg0=this.getReadableDatabase();
        ArrayList amap = new ArrayList();

        Cursor c=null;
        c=arg0.rawQuery("SELECT dateout FROM sss WHERE  remark =\"open\"   AND ( vechicleno = "+"'"+vnoo+"'" + " OR sno ="+"'"+vnoo+"'"+" )",null);
        //c=arg0.rawQuery("select * from "+TABLE_NAME,null);
        if(c.moveToFirst())
        {
            do
            {
                HashMap<String,String> map=new HashMap<String,String>();
                //.Log.e("Status",c.getString(0));
                map.put("",c.getString(0));




                //Log.e("Map",map.get("Name"));
                amap.add(map);
            }while(c.moveToNext());
        }
        //Log.e("ArrayList",amap.get();
        return amap;
    }

    public ArrayList outtime( String vnoo)
    {
        SQLiteDatabase arg0=this.getReadableDatabase();
        ArrayList amap = new ArrayList();

        Cursor c=null;
        c=arg0.rawQuery("SELECT timeout FROM sss WHERE  remark =\"open\"   AND ( vechicleno = "+"'"+vnoo+"'" + " OR sno ="+"'"+vnoo+"'"+" )",null);
        //c=arg0.rawQuery("select * from "+TABLE_NAME,null);
        if(c.moveToFirst())
        {
            do
            {
                HashMap<String,String> map=new HashMap<String,String>();
                //.Log.e("Status",c.getString(0));
                map.put("",c.getString(0));




                //Log.e("Map",map.get("Name"));
                amap.add(map);
            }while(c.moveToNext());
        }
        //Log.e("ArrayList",amap.get();
        return amap;
    }

    public ArrayList lastday( String vnoo)
    {
        SQLiteDatabase arg0=this.getReadableDatabase();
        ArrayList amap = new ArrayList();

        Cursor c=null;
        c=arg0.rawQuery("SELECT No_Days_Or_Hour FROM sss WHERE remark =\"open\"   AND ( vechicleno = "+"'"+vnoo+"'" + " OR sno ="+"'"+vnoo+"'"+" )",null);
        //c=arg0.rawQuery("select * from "+TABLE_NAME,null);
        if(c.moveToFirst())
        {
            do
            {
                HashMap<String,String> map=new HashMap<String,String>();
                //.Log.e("Status",c.getString(0));
                map.put("",c.getString(0));




                //Log.e("Map",map.get("Name"));
                amap.add(map);
            }while(c.moveToNext());
        }
        //Log.e("ArrayList",amap.get();
        return amap;
    }

    public ArrayList sumamount( String DateFrom,String Dateto)
    {
        SQLiteDatabase arg0=this.getReadableDatabase();
        ArrayList amap = new ArrayList();

        Cursor c=null;
        c=arg0.rawQuery("SELECT sum(Total_Amount) FROM sss WHERE remark = \"close\" AND dateoutt BETWEEN '" + DateFrom + "' AND '" + Dateto+ "' ORDER BY  sno ASC",null);
        //c=arg0.rawQuery("select * from "+TABLE_NAME,null);
        if(c.moveToFirst())
        {
            do
            {
                HashMap<String,String> map=new HashMap<String,String>();
                //.Log.e("Status",c.getString(0));
                map.put("",c.getString(0));




                //Log.e("Map",map.get("Name"));
                amap.add(map);
            }while(c.moveToNext());
        }
        //Log.e("ArrayList",amap.get();
        return amap;
    }

    public ArrayList novlin(String DateFrom,String Dateto)
    {
        SQLiteDatabase arg0=this.getReadableDatabase();
        ArrayList amap = new ArrayList();

        Cursor c=null;
        c=arg0.rawQuery("SELECT  count(*) FROM sss WHERE   dateinn BETWEEN '" + DateFrom + "' AND '" + Dateto+ "' ORDER BY sno ASC",null);
        //c=arg0.rawQuery("select * from "+TABLE_NAME,null);
        if(c.moveToFirst())
        {
            do
            {
                HashMap<String,String> map=new HashMap<String,String>();
                //.Log.e("Status",c.getString(0));
                map.put("",c.getString(0));




                //Log.e("Map",map.get("Name"));
                amap.add(map);
            }while(c.moveToNext());
        }
        //Log.e("ArrayList",amap.get();
        return amap;
    }

    public ArrayList novlout( String DateFrom,String Dateto)
    {
        SQLiteDatabase arg0=this.getReadableDatabase();
        ArrayList amap = new ArrayList();

        Cursor c=null;
        c=arg0.rawQuery("SELECT  count(*) FROM sss WHERE remark = \"close\" AND dateoutt BETWEEN '" + DateFrom + "' AND '" + Dateto+ "' ORDER BY sno ASC",null);
        //c=arg0.rawQuery("select * from "+TABLE_NAME,null);
        if(c.moveToFirst())
        {
            do
            {
                HashMap<String,String> map=new HashMap<String,String>();
                //.Log.e("Status",c.getString(0));
                map.put("",c.getString(0));




                //Log.e("Map",map.get("Name"));
                amap.add(map);
            }while(c.moveToNext());
        }
        //Log.e("ArrayList",amap.get();
        return amap;
    }



    public ArrayList lasttoken(String vnoo,String a)
    {
        SQLiteDatabase arg0=this.getReadableDatabase();
        ArrayList amap = new ArrayList();
        String op="'open'";
        Cursor c=null;
        c=arg0.rawQuery("SELECT sno FROM sss WHERE  remark ="+ op + "AND dateout="+"'"+a+"'"+" AND ( vechicleno = "+"'"+vnoo+"'" + " OR sno ="+"'"+vnoo+"'"+" )",null);
        //c=arg0.rawQuery("select * from "+TABLE_NAME,null);
        if(c.moveToFirst())
        {
            do
            {
                HashMap<String,String> map=new HashMap<String,String>();
                //.Log.e("Status",c.getString(0));
                map.put("",c.getString(0));




                //Log.e("Map",map.get("Name"));
                amap.add(map);
            }while(c.moveToNext());
        }
        //Log.e("ArrayList",amap.get();
        return amap;
    }


    public int delete( String vn) {
        SQLiteDatabase arg0=this.getReadableDatabase();
        return arg0.delete(TABLE_NAME1,"vechicleno ="+"'"+vn+"'",null);

    }

    public long insertstprienter(String Printer)
    {



        SQLiteDatabase arg0=this.getWritableDatabase();
        ContentValues content=new ContentValues();
        content.put("printer",Printer);




        return arg0.insert(TABLE_NAME10,null,content);
    }

    public long insertuser(String Printer)
    {



        SQLiteDatabase arg0=this.getWritableDatabase();
        ContentValues content=new ContentValues();
        content.put("user",Printer);




        return arg0.insert(TABLE_NAME11,null,content);
    }


    public ArrayList lastpri()
    {
        SQLiteDatabase arg0=this.getReadableDatabase();
        ArrayList amap = new ArrayList();

        Cursor c=null;
        c=arg0.rawQuery("SELECT printer FROM printername WHERE   sno = (SELECT MAX(sno)  FROM printername)",null);
        //c=arg0.rawQuery("select * from "+TABLE_NAME,null);
        if(c.moveToFirst())
        {
            do
            {
                HashMap<String,String> map=new HashMap<String,String>();
                //.Log.e("Status",c.getString(0));
                map.put("",c.getString(0));



                //Log.e("Map",map.get("Name"));
                amap.add(map);
            }while(c.moveToNext());
        }
        //Log.e("ArrayList",amap.get();
        return amap;
    }

    public ArrayList lastuser()
    {
        SQLiteDatabase arg0=this.getReadableDatabase();
        ArrayList amap = new ArrayList();

        Cursor c=null;
        c=arg0.rawQuery("SELECT user FROM users WHERE   sno = (SELECT MAX(sno)  FROM users)",null);
        //c=arg0.rawQuery("select * from "+TABLE_NAME,null);
        if(c.moveToFirst())
        {
            do
            {
                HashMap<String,String> map=new HashMap<String,String>();
                //.Log.e("Status",c.getString(0));
                map.put("",c.getString(0));



                //Log.e("Map",map.get("Name"));
                amap.add(map);
            }while(c.moveToNext());
        }
        //Log.e("ArrayList",amap.get();
        return amap;
    }



    public TokenData getSelectedCustomerDetailoff(String vechicletype) {

        TokenData categoryDao = new TokenData ();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM token2 where vechicleno ="+"'"+vechicletype+"'",null);

            if (null != cursor && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    categoryDao = new TokenData ();

                    categoryDao.mvechicleno = cursor.getString(cursor.getColumnIndex(vechicleno));
                    categoryDao.mvalidity = cursor.getString(cursor.getColumnIndex("validity"));
                    categoryDao.mamount = cursor.getString(cursor.getColumnIndex("amount"));
                    categoryDao.msub_sed_hour = cursor.getString(cursor.getColumnIndex("sub_seq_hour"));
                    categoryDao.msub_sed_amount = cursor.getString(cursor.getColumnIndex("sub_seq_amount"));


                }
            }
        } catch (Exception e) {

        } finally {
            if (null != cursor) {
                cursor.close();
            }
            db.close ();
        }
        return categoryDao;
    }


    public ArrayList vehiclereport(String Datefrom,String Dateto)
    {
        SQLiteDatabase arg0=this.getReadableDatabase();
        ArrayList amap = new ArrayList();

        Cursor c=null;
        c=arg0.rawQuery(" SELECT  sno,vechicleno,No_Days_Or_Hour,Total_Amount FROM sss where dateinn BETWEEN '" + Datefrom + "' AND '" + Dateto+ "' ORDER BY sno ASC",null);
        if(c.moveToFirst())
        {
            do
            {ArrayList<String> list =new ArrayList<String>();
                //HashMap<String,String> map=new HashMap<String,String>();
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                list.add(""+c.getString(0)); //iteam

                String ab;

                String ab1=""+c.getString(1);
                String ab2=""+c.getString(3);

                String am12=(c.getString(2));
                Float am3ss=Float.parseFloat(am12);
                // am12 = String.format("%.2f",am3ss);
                ab=am12;



                if(0==ab1.length()){
                    list.add(""+"           "+c.getString(1));//rate
                }
                if(1==ab1.length()){
                    list.add(""+"           "+c.getString(1));//rate
                }
                if(2==ab1.length()){
                    list.add(""+"          "+c.getString(1));//rate
                }
                if(3==ab1.length()){
                    list.add(""+"        "+c.getString(1));//rate
                }
                if(4==ab1.length()){
                    list.add(""+"       "+c.getString(1));//rate
                }
                if(5==ab1.length()){
                    list.add(""+"      "+c.getString(1));//rate
                }
                if(6==ab1.length()){
                    list.add(""+"     "+c.getString(1));//rate
                }
                if(7==ab1.length()){
                    list.add(""+"    "+c.getString(1));//rate
                }
                if(8==ab1.length()){
                    list.add(""+"   "+c.getString(1));//rate
                }
                if(9==ab1.length()){
                    list.add(""+"  "+c.getString(1));//rate
                }
                if(10==ab1.length()){
                    list.add(""+" "+c.getString(1));//rate
                }
                if(11==ab1.length()){
                    list.add(""+""+c.getString(1));//rate
                }



                if(1==ab.length()){
                    list.add(""+"  "+ab);//rate
                }
                if(2==ab.length()){
                    list.add(""+" "+ab);//rate
                }
                if(3==ab.length()){
                    list.add(""+""+ab);//rate
                }




                if(0==ab2.length()){
                    list.add(""+"Null"+c.getString(3));//rate
                }
                if(1==ab2.length()){
                    list.add(""+"     "+c.getString(3));//rate
                }
                if(2==ab2.length()){
                    list.add(""+"    "+c.getString(3));//rate
                }
                if(3==ab2.length()){
                    list.add(""+"   "+c.getString(3));//rate
                }
                if(4==ab2.length()){
                    list.add(""+"  "+c.getString(3));//rate
                }
                if(5==ab2.length()){
                    list.add(""+" "+c.getString(3));//rate
                }
                if(6==ab2.length()){
                    list.add(""+""+c.getString(3));//rate
                }





                amap.add(list);
            }while(c.moveToNext());
        }
        //Log.e("ArrayList",amap.get();
        return amap;
    }

    public ArrayList pendingreport(String Datefrom,String Dateto)
    {
        SQLiteDatabase arg0=this.getReadableDatabase();
        ArrayList amap = new ArrayList();

        Cursor c=null;

        c=arg0.rawQuery(" SELECT  sno,vechicleno,datein,timein FROM sss where remark='open' AND dateinn BETWEEN '" + Datefrom + "' AND '" + Dateto+ "' ORDER BY sno ASC",null);
        if(c.moveToFirst())
        {
            do
            {ArrayList<String> list =new ArrayList<String>();
                //HashMap<String,String> map=new HashMap<String,String>();
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                list.add(""+c.getString(0)); //iteam

                String ab;

                String ab1=""+c.getString(1);
                String ab2=""+c.getString(3);

                String am12=(c.getString(2));
                // Float am3ss=Float.parseFloat(am12);
                // am12 = String.format("%.2f",am3ss);
                ab=am12;



                if(0==ab1.length()){
                    list.add(""+"           "+c.getString(1));//rate
                }
                if(1==ab1.length()){
                    list.add(""+"           "+c.getString(1));//rate
                }
                if(2==ab1.length()){
                    list.add(""+"          "+c.getString(1));//rate
                }
                if(3==ab1.length()){
                    list.add(""+"        "+c.getString(1));//rate
                }
                if(4==ab1.length()){
                    list.add(""+"       "+c.getString(1));//rate
                }
                if(5==ab1.length()){
                    list.add(""+"      "+c.getString(1));//rate
                }
                if(6==ab1.length()){
                    list.add(""+"     "+c.getString(1));//rate
                }
                if(7==ab1.length()){
                    list.add(""+"    "+c.getString(1));//rate
                }
                if(8==ab1.length()){
                    list.add(""+"   "+c.getString(1));//rate
                }
                if(9==ab1.length()){
                    list.add(""+"  "+c.getString(1));//rate
                }
                if(10==ab1.length()){
                    list.add(""+" "+c.getString(1));//rate
                }
                if(11==ab1.length()){
                    list.add(""+""+c.getString(1));//rate
                }



                if(9==ab.length()){
                    list.add(""+"  "+ab);//rate
                }
                if(10==ab.length()){
                    list.add(""+" "+ab);//rate
                }
                if(11==ab.length()){
                    list.add(""+""+ab);//rate
                }




                if(0==ab2.length()){
                    list.add(""+"Null"+c.getString(3));//rate
                }
                if(5==ab2.length()){
                    list.add(""+""+c.getString(3));//rate
                }
                if(6==ab2.length()){
                    list.add(""+""+c.getString(3));//rate
                }
                if(7==ab2.length()){
                    list.add(""+""+c.getString(3));//rate
                }
                if(8==ab2.length()){
                    list.add(""+""+c.getString(3));//rate
                }
                if(5==ab2.length()){
                    list.add(""+""+c.getString(3));//rate
                }
                if(6==ab2.length()){
                    list.add(""+""+c.getString(3));//rate
                }





                amap.add(list);
            }while(c.moveToNext());
        }
        //Log.e("ArrayList",amap.get();
        return amap;
    }


    public  Cursor retrieve(String searchTerm)
    {

        SQLiteDatabase arg0=this.getReadableDatabase();
        String Vnop = null;
        String[] columns={vechicleno};
        Cursor c=null;

        if(searchTerm != null && searchTerm.length()>0)
        {
            String sql=" SELECT * FROM sss WHERE vechicleno LIKE '%"+searchTerm+"%'";


            c=arg0.rawQuery(sql,null);
            return c;

        }

        c=arg0.query(TABLE_NAME,columns,null,null,null,null,null);
        return c;
    }

    public int deletertoken() {
        SQLiteDatabase arg0=this.getReadableDatabase();
        return arg0.delete(TABLE_NAME1,null,null);

    }


    public ArrayList mlasttoken(String vnoo)
    {
        SQLiteDatabase arg0=this.getReadableDatabase();
        ArrayList amap = new ArrayList();

        Cursor c=null;
        c=arg0.rawQuery("SELECT sno FROM sss WHERE  remark =\"close\"  AND ( vechicleno = "+"'"+vnoo+"'" + " OR sno ="+"'"+vnoo+"'"+")",null);

        if(c.moveToFirst())
        {
            do
            {
                HashMap<String,String> map=new HashMap<String,String>();
                //.Log.e("Status",c.getString(0));
                map.put("",c.getString(0));




                //Log.e("Map",map.get("Name"));
                amap.add(map);
            }while(c.moveToNext());
        }
        //Log.e("ArrayList",amap.get();
        return amap;
    }


}

