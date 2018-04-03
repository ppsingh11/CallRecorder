package com.callrecorder.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Prashant Pratap on 03-04-2018.
 */

public class RecordingFilters extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recording_filters);
    }

    public void SelectContact(View view)
    {
        Intent i = new Intent(this,WhiteList.class);
        startActivity(i);
    }
}
