package com.alarmate.application;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by seongjinlee on 2017. 5. 3..
 */

public class AlarmService extends Service {

    public AlarmService(){

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(this, "알람이 울립니다.", Toast.LENGTH_SHORT).show();
        Log.i("myTag", "알람서비스 실행");
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
