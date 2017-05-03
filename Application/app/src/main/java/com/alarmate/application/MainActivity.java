package com.alarmate.application;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
//    private AlarmManager alarmManager;
    private ViewPager vp;
    public static int i= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vp = (ViewPager)findViewById(R.id.vp);

        final Button btn1 = (Button)findViewById(R.id.btn_tab1);
        final Button btn2 = (Button)findViewById(R.id.btn_tab2);
        final Button btn3 = (Button)findViewById(R.id.btn_tab3);

        vp.setAdapter(new PageAdapter(this.getSupportFragmentManager()));
        vp.setCurrentItem(0);
        View.OnClickListener movePageListener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int tag = (int)v.getTag();
                vp.setCurrentItem(tag);
                if(tag == 0){
                    btn1.setBackgroundColor(Color.BLUE);
                    btn2.setBackgroundColor(Color.WHITE);
                    btn3.setBackgroundColor(Color.WHITE);
                }
                if(tag == 1){
                    btn1.setBackgroundColor(Color.WHITE);
                    btn2.setBackgroundColor(Color.BLUE);
                    btn3.setBackgroundColor(Color.WHITE);
                }
                if(tag == 2){
                    btn1.setBackgroundColor(Color.WHITE);
                    btn2.setBackgroundColor(Color.WHITE);
                    btn3.setBackgroundColor(Color.BLUE);
                }
            }
        };

        btn1.setOnClickListener(movePageListener);
        btn2.setOnClickListener(movePageListener);
        btn3.setOnClickListener(movePageListener);
        btn1.setTag(0);
        btn2.setTag(1);
        btn3.setTag(2);

//        alarmManager = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
//        setAlarm(14,19,10,false);
//        setAlarm(14,19,40,false);
//        setAlarm(14,19,59,false);

    }
//
//
//    private boolean setAlarm(int hour, int min, int days, boolean rept){
//        Intent mAlarmIntent = new Intent("com.alarmate.application.ALARM_START");
//        PendingIntent mPendingIntent = PendingIntent.getBroadcast(
//                this,
//                i,
//                mAlarmIntent,
//                PendingIntent.FLAG_UPDATE_CURRENT
//
//        );
//        i++;
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.SECOND, days);
//
//        Log.i("myTag", "알람 세팅 : " +  calendar.getTimeInMillis());
//        alarmManager.set(
//                AlarmManager.RTC_WAKEUP,
//                calendar.getTimeInMillis(),
//                mPendingIntent
//        );
//        return true;
//    }
}
