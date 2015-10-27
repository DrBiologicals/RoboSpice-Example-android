package com.carstenscholz.robospicemoa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Carsten on 9/10/2015.
 */
public class MoaDetailActivity extends AppCompatActivity{

    TextView error;
    TextView method;
    TextView paramName;
    TextView paramValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Tell the activity which XML layout is right
        setContentView(R.layout.activity_detail);

        // Enable the "Up" button for more navigation options
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Access the textview from XML
        error = (TextView) findViewById(R.id.error);
        method = (TextView) findViewById(R.id.method);
        paramName = (TextView) findViewById(R.id.paramName);
        paramValue = (TextView) findViewById(R.id.paramValue);

        // unpack coverID from its trip inside your intent
        String str_error = this.getIntent().getExtras().getString("error");
        String str_method = this.getIntent().getExtras().getString("method");
        String str_paramValue = this.getIntent().getExtras().getString("paramValue");
        String str_paramName = this.getIntent().getExtras().getString("paramName");

        //Set the Textview to contain the data passed through the intent
        error.setText(str_error);
        method.setText(str_method);
        paramName.setText(str_paramName);
        paramValue.setText(str_paramValue);
    }
}
