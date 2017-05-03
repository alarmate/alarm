package com.alarmate.application;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.view.WindowManager;

/**
 * Created by seongjinlee on 2017. 5. 3..
 */

public class AlarmActivity extends Activity {
    PowerManager pm;
    PowerManager.WakeLock wl = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_layout);

        pm = (PowerManager)this.getSystemService(Context.POWER_SERVICE);
        if (!pm.isScreenOn()) {
            wl = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "AlarmMate");
            wl.acquire();
            this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
//										WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                    WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
                    WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        }
        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(500);

    }
}
