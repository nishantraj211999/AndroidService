package com.example.serviceconcept;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/*
* This service exposes a public system method (getCurrentTimestamp) directly to the calling UI component.
* */
public class MyBoundService extends Service {
    private final IBinder binder= new LocalBinder();

    // Bound Setup: Expose the service instance to the Activity
    public class LocalBinder extends Binder {
        MyBoundService getService() {
            return MyBoundService.this;
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;// Returns the binder when UI connects
    }
    // Public method that the activity can explicitly call
    public String getCurrentTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }
}
