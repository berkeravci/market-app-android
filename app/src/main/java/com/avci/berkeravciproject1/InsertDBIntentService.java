package com.avci.berkeravciproject1;

import android.app.IntentService;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;

public class InsertDBIntentService extends IntentService {
    DatabaseHelper dbHelper;
    public InsertDBIntentService() {
        super("InsertDBIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        dbHelper = new DatabaseHelper(this);
        if (intent != null) {
            Bundle b = intent.getExtras();
            Product productItem = b.getParcelable("productItem");
            boolean res = ProductDB.insert(dbHelper, productItem);

            Intent broadcastIntent = new Intent();
            String msg="Insert cannot be done";
            if(res)
                msg="Insert done";

            broadcastIntent.putExtra("insertResult", msg);
            broadcastIntent.setAction("INSERT_PRO_ACTION");
            sendBroadcast(broadcastIntent);
        }
    }
}
