package com.skyparking.admin.parkingappemapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Myadapter extends BaseAdapter implements Filterable {
    Context c;
    ArrayList<velreplist>velreplists;
    ArrayList<velreplist> filterlist;
    CustomFilter filter;

    public Myadapter(Context ctx,ArrayList<velreplist>velreplists){
        this.c=ctx;
        this.velreplists=velreplists;
        this.filterlist=velreplists;

    }

    @Override
    public int getCount() {
        return velreplists.size ();
    }

    @Override
    public Object getItem(int position) {
        return velreplists.get ( position );
    }

    @Override
    public long getItemId(int position) {
        return velreplists.indexOf ( getItemId ( position ) );
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=(LayoutInflater) c.getSystemService ( Context.LAYOUT_INFLATER_SERVICE );
        if(convertView==null){
            convertView=inflater.inflate ( R.layout.listvw,null );
        }
        TextView textView13=convertView.findViewById(R.id.textView26);
        TextView textView14=convertView.findViewById(R.id.textView);
        TextView textView6=convertView.findViewById(R.id.textView6);
        TextView indate=convertView.findViewById(R.id.textView14);
        TextView intime=convertView.findViewById(R.id.textView);
        TextView outdate=convertView.findViewById(R.id.textView16);
        TextView outtime=convertView.findViewById(R.id.textView15);
        TextView mdays=convertView.findViewById(R.id.textView18);
        TextView mamount=convertView.findViewById(R.id.textView17);
        TextView mtoken=convertView.findViewById(R.id.textView19);
        textView13.setText( ( CharSequence ) velreplists.get ( position ).getV_NO () );
        textView6.setText ( ( CharSequence ) velreplists.get(position).getV_Type () );
        indate.setText ( ( CharSequence ) velreplists.get(position).getDate_in () );
        intime.setText ( ( CharSequence ) velreplists.get(position).getTime_in () );
        outdate.setText ( ( CharSequence ) velreplists.get(position).getDate_out () );
        outtime.setText ( ( CharSequence ) velreplists.get(position).getTime_Out () );
        mdays.setText ( ( CharSequence ) velreplists.get(position).getNo_Day () );
        mamount.setText ( ( CharSequence ) velreplists.get(position).getAmount () );
        mtoken.setText ( ( CharSequence ) velreplists.get ( position ).getTokenno () );

        final int pos=position;

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c,velreplists.get ( pos ).getV_NO (), Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    @Override
    public Filter getFilter() {

        if (filter ==null){
            filter= new CustomFilter ();
        }

        return filter;
    }
    class CustomFilter extends Filter{
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results=new FilterResults ();
            if (constraint !=null && constraint.length ()>0){
                constraint=constraint.toString ().toUpperCase ();
                ArrayList<velreplist> filters=new ArrayList<velreplist> (  );
                for (int i=0;i<filterlist.size ();i++){

                    if(filterlist.get ( i ).getV_NO ().toUpperCase ().contains ( constraint )){

                        velreplist v=new velreplist ( filterlist.get ( i ).getTokenno ());
                        filters.add ( v);
                    }
                    results .count=filters.size ();
                    results.values=filters;
                }

            }else{results .count=filterlist.size ();
                results.values=filterlist;}
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            velreplists=(ArrayList<velreplist>)results.values;
            notifyDataSetChanged ();


        }
    }
}
