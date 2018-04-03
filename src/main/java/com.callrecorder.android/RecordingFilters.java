package com.callrecorder.android;

import android.app.Activity;
import android.content.Intent;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recording_filters);

        SelectContact = (Button) findViewById(R.id.select_contact);
    }


    public void ToggleButton(View view)
    {
        boolean on = ((ToggleButton) view).isChecked();

        if (on)
        {
           // Toast.makeText(this,"ON",Toast.LENGTH_SHORT).show();
            SelectContact.setClickable(false);
        }
        else

        {
              //  Toast.makeText(this,"OFF",Toast.LENGTH_SHORT).show();
            SelectContact.setClickable(true);
        }

    }

    public void SelectContact(View view)
    {
        Intent i = new Intent(this,WhiteList.class);
        startActivity(i);
    }


}
