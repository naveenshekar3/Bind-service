package com.example.root.bindservice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    private ServiceConnection mServiceConnection;
    private MyService mMyservice;
    private boolean isServiceConnected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mServiceConnection= new ServiceConnection() {// ServiceConnection is an interface, Overide all the methods below
            @Override
            public void onServiceConnected(ComponentName name, IBinder iBinder) {
                MyService.LocalBinder localBinder=(MyService.LocalBinder) iBinder;
                mMyservice = localBinder.getService();
                isServiceConnected=true;

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
    }

    public void bind(View view)
    {
        if (isServiceConnected)
        {
            Toast.makeText(mMyservice, "Service already Connected", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent=new Intent(MainActivity.this,MyService.class);
            bindService(intent,mServiceConnection,BIND_AUTO_CREATE);//using BIND_AUTO_CREATE,  this will create a new service
        }

    }

    public void unbind(View view)
    {
        if (isServiceConnected)
        {
            unbindService(mServiceConnection);
            isServiceConnected=false;
        }
        else
            {
                Toast.makeText(mMyservice, "Service Not Connected", Toast.LENGTH_SHORT).show();
            }
    }

    public void getResult(View view)
    {
        if (isServiceConnected)
        {
            double result=mMyservice.getSQRT(45);
            Toast.makeText(mMyservice, String.valueOf(result), Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(mMyservice, "Service Not Connected", Toast.LENGTH_SHORT).show();
        }
    }
}
