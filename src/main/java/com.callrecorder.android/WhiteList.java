package com.callrecorder.android;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Prashant Pratap on 03-04-2018.
 */

public class WhiteList extends Activity {

    static final int PICK_CONTACT=1;
    String cNumber;
    String  CleanNumber;
    ListView Wlist;

    public static SQLiteDatabase DB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.white_list);



        DB = openOrCreateDatabase("Contacts", Context.MODE_PRIVATE,null);
        DB.execSQL("create table if not exists WhiteList (ContactNumber Varchar(20),Name Varchar(20), PRIMARY KEY(ContactNumber))");

        Wlist = (ListView)findViewById(R.id.contact_list);
        BaseAdapter adp = new CustomWhiteList(WhiteList.this,this);
       // Wlist.setAdapter(new CustomWhiteList(WhiteList.this,this));
        Wlist.setAdapter(adp);
        adp.notifyDataSetChanged();

    }


    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        switch (reqCode) {
            case (PICK_CONTACT) :
                if (resultCode == Activity.RESULT_OK) {

                    Uri contactData = data.getData();
                    Cursor c =  managedQuery(contactData, null, null, null, null);
                    if (c.moveToFirst()) {


                        String id =c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));

                        String hasPhone =c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

                        if (hasPhone.equalsIgnoreCase("1")) {
                            Cursor phones = getContentResolver().query(
                                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ id,
                                    null, null);
                            phones.moveToFirst();
                            cNumber = phones.getString(phones.getColumnIndex("data1"));
                              String CNumber = cNumber.replaceAll("[^0-9]", "");
                              CleanNumber = CNumber.replaceFirst("^0+(?!$)", "");


                           // Toast.makeText(this,cNumber,Toast.LENGTH_SHORT).show();
                        }
                        String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                      //  Toast.makeText(this,name,Toast.LENGTH_SHORT).show();
                         //////////////////////////Insert Name and Number to database //////////////////////

                        ContentValues values = new ContentValues();
                        values.put("ContactNumber",CleanNumber);
                        values.put("Name",name);

                        long clm = DB.insert("WhiteList",null,values);

                        if(clm==-1)
                        {
                            Toast.makeText(this,"This Contact is Already Added", Toast.LENGTH_SHORT).show();
                        }

                        else
                        {
                            Toast.makeText(this,"Contact Added Successfully", Toast.LENGTH_SHORT).show();
                        }

                        /////////////////////////////////////////////////////////////////////////////////////
                        Intent i = new Intent(this,WhiteList.class);
                        startActivity(i);
                        /////////////////////////////////////////////////////////////////////////////////

                    }
                }
                break;
        }
    }

    public void PickContact(View view)
    {

        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, PICK_CONTACT);
    }
}
