package com.example.root.bindservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = "MyService";
    public MyService() {
    }

    public double getSQRT(double i)
    {
        return Math.sqrt(i);
    }

    class LocalBinder extends Binder //creating sub class for IBinder, Binder is extended because Binder class is implementing IBinder
    {
        public MyService getService()
        {
            return MyService.this;
        }
    }

    IBinder iBinder=new LocalBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: ");
        return iBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind: ");
        return super.onUnbind(intent);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }
}
