package com.carstenscholz.robospicemoa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

/**
 * Created by Carsten on 9/10/2015.
 */
public class MoaActivity extends BaseMoaActivity implements AdapterView.OnItemClickListener {

    private MoaRequest moaRequest;

    ListView listView;

    MoaAdapter moaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Tell the activity which XML layout is right
        setContentView(R.layout.main);

        // Setup a RoboSpice Request where the object you expect to receive is a MoaObject
        moaRequest = new MoaRequest();

    }

    @Override
    protected void onStart(){
        super.onStart();

        //Get the RoboSpice Manager and store the request result for one minute in the Cache
        //Return the request results to the Listener; MoaRequestListener
        getSpiceManager().execute(moaRequest, "json", DurationInMillis.ONE_MINUTE, new MoaRequestListener());

        //ListView to display the data
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);

        //Set the adapter which directly handles the data for the listview
        moaAdapter = new MoaAdapter(this, getLayoutInflater());
        listView.setAdapter(moaAdapter);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Now that the user has chosen a MoaObject Object, get it's data.
        MoaObject moaObject = (MoaObject) moaAdapter.getItem(position);

        // User has clicked on the list item now grab the data for the item
        String method = moaObject.getMethod();
        String error = moaObject.getError();
        String paramName = moaObject.getParamName();
        String paramValue = moaObject.getParamValue();

        // create an Intent to take you over to a new DetailActivity
        Intent detailIntent = new Intent(this, MoaDetailActivity.class);

        // pack away the data about the Moa Object
        // into your intent before you start the activity
        detailIntent.putExtra("error",error);
        detailIntent.putExtra("method",method);
        detailIntent.putExtra("paramName",paramName);
        detailIntent.putExtra("paramValue",paramValue);

        // start the next Activity using your prepared Intent
        startActivity(detailIntent);
    }

    public final class MoaRequestListener implements RequestListener<MoaObject>{
        @Override
        public void onRequestFailure( SpiceException spiceException ){
            // Display a "Toast" message
            // to announce the failure
            Toast.makeText( MoaActivity.this,"failure", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRequestSuccess(final MoaObject result) {
            // Display a "Toast" message
            // to announce your success
            Toast.makeText( MoaActivity.this,"success", Toast.LENGTH_SHORT).show();
            // update the data in custom method moaAdapter
            moaAdapter.updateData(result);
        }
    }
}
