package com.callrecorder.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

/**
 * Created by Prashant Pratap on 03-04-2018.
 */

public class RecordingFilters extends Activity {

    Button SelectContact;
    SharedPreferences spf;
    ToggleButton tg;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recording_filters);

        tg = (ToggleButton) findViewById(R.id.toggle_btn);

        SelectContact = (Button) findViewById(R.id.select_contact);
        spf = getSharedPreferences("myshared", Context.MODE_PRIVATE);

        if(spf.getBoolean("Flag",true))
        {

            tg.setChecked(true);
            SelectContact.setClickable(false);

        }
        else
        {
            tg.setChecked(false);
            SelectContact.setClickable(true);
        }
    }


    public void ToggleButton(View view)
    {
        boolean on = ((ToggleButton) view).isChecked();

        SharedPreferences.Editor edt = spf.edit();

        if (on)
        {
           // Toast.makeText(this,"ON",Toast.LENGTH_SHORT).show();
            SelectContact.setClickable(false);
            edt.putBoolean("Flag",true);
            edt.commit();
        }
        else

        {
              //  Toast.makeText(this,"OFF",Toast.LENGTH_SHORT).show();
            SelectContact.setClickable(true);
            edt.putBoolean("Flag",false);
            edt.commit();
        }

    }

    public void SelectContact(View view)
    {
        Intent i = new Intent(this,WhiteList.class);
        startActivity(i);
    }


}
