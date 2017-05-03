package com.alarmate.application;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by seongjinlee on 2017. 5. 1..
 */


public class AlarmReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("myTag", "리시버 발동");
        // TODO Auto-generated method stub
        Intent mServiceIntent = new Intent(context, AlarmService.class);
        context.startService(mServiceIntent);
    }

}
