package org.robotv.ui;

import android.app.Activity;
import android.util.Log;

import org.robotv.dataservice.DataService;
import org.robotv.dataservice.DataServiceClient;

public class DataServiceActivity extends Activity {

    final private static String TAG = "DataServiceActivity";

    private DataServiceClient dataClient;
    private DataService.Listener listener;

    protected void setServiceListener(DataService.Listener listener) {
        this.listener = listener;
    }

    @Override
    public void onResume() {
        super.onResume();

        // start data service
        dataClient = new DataServiceClient(this, listener);
        dataClient.bind();

        Log.d(TAG, "bind");
    }

    @Override
    public void onPause() {
        super.onPause();
        dataClient.unbind();

        Log.d(TAG, "unbind");
    }

    protected DataService getService() {
        if(dataClient == null) {
            return null;
        }

        return dataClient.getService();
    }
}
