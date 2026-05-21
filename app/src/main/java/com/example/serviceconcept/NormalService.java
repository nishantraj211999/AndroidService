package com.example.serviceconcept;

import android.app.Service;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class NormalService extends Service {
private static final String TAG="NormalService";
    @Override
    public void onCreate() {
        super.onCreate();
        //one time init we can do
        Log.i(TAG,"::onCreate::");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"::onStartCommand::");
        // Run execution logic on a separate background thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                    Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                    r.play();
                   Thread.sleep(2000); // Simulate brief background task and after 2s service stop via stop self
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // ALWAYS stop a background service yourself when finished
                stopSelf();
            }
        }).start();
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"::onDestroy::");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
