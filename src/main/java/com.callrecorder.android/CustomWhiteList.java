package com.callrecorder.android;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    ImageView DeleteIcon;





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
        DeleteIcon = (ImageView)v.findViewById(R.id.delete_icon);


        ///////////////////////////////////////////////////


        DeleteIcon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                String ContactNum = contact_number.get(position);
                //  Toast.makeText(mContext," "+candidate_id+" "+VoterId,Toast.LENGTH_SHORT).show();


              //  Toast.makeText(List,ContactNum,Toast.LENGTH_SHORT).show();

                try {
                    DB = List.openOrCreateDatabase("Contacts", Context.MODE_PRIVATE, null);
                     DB.delete("WhiteList","ContactNumber"+"="+ContactNum,null);

                    DB.close();
                    Toast.makeText(List,"Contact Deleted Successfully",Toast.LENGTH_SHORT).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(List,e.toString(),Toast.LENGTH_SHORT).show();
                }

                /////////////////////////////////////////////////////////////////////////////////////
                Intent i = new Intent(List,WhiteList.class);
                List.startActivity(i);

                /////////////////////////////////////////////////////////////////////////////////

            }
        });



        ////////////////////////////////////////////////


        C_Name.setText(contact_name.get(position));
        C_Number.setText(contact_number.get(position));






        return v;
    }
}
