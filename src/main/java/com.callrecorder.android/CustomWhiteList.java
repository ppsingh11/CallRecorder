package com.callrecorder.android;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Prashant Pratap on 03-04-2018.
 */

public class CustomWhiteList extends BaseAdapter {

    SQLiteDatabase DB = WhiteList.DB;
    WhiteList List;
    Context mContext;


    ArrayList<String> contact_name = new ArrayList<String>();
    ArrayList<String> contact_number = new ArrayList<String>();

    TextView C_Name,C_Number;





    CustomWhiteList(WhiteList c,Context context) {
        List = c;
        mContext = context;

        DB = mContext.openOrCreateDatabase("Contacts", Context.MODE_PRIVATE, null);


        String query = "SELECT * FROM WhiteList";
        Cursor cursor = DB.rawQuery(query, null);


        if (cursor.moveToFirst()) {
            do {
                String CName = cursor.getString(1);
                String CNumber = cursor.getString(0);

                contact_name.add(CName);
                contact_number.add(CNumber);

            } while (cursor.moveToNext());
        }

        cursor.close();
        DB.close();
    }






        @Override
    public int getCount() {
        return contact_name.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inf = LayoutInflater.from(List);
        View v = inf.inflate(R.layout.custom_white_list,null);

        C_Name = (TextView)v.findViewById(R.id.contact_name);
        C_Number = (TextView)v.findViewById(R.id.contact_number);

        C_Name.setText(contact_name.get(position));
        C_Number.setText(contact_number.get(position));






        return v;
    }
}
