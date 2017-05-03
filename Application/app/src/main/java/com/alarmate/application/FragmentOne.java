package com.alarmate.application;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SlidingPaneLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.Calendar;

/**
 * Created by seongjinlee on 2017. 5. 1..
 * 기본 알람설정 페이지
 */

public class FragmentOne extends Fragment implements View.OnClickListener{
    public static int i = 0;
    SlidingUpPanelLayout slidingLayout;
    MainActivity activity;
    AlarmManager alarmManager;
    EditText txt_hour, txt_min;
    Button btn_register;

    public FragmentOne(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.activity = (MainActivity) getActivity();
        CoordinatorLayout layout = (CoordinatorLayout)inflater.inflate(R.layout.fragment_one, container, false);
        FloatingActionButton addBtn = (FloatingActionButton)layout.findViewById(R.id.add_alarm);
        txt_hour = (EditText)layout.findViewById(R.id.add_hour);
        txt_min = (EditText)layout.findViewById(R.id.add_min);


        slidingLayout = (SlidingUpPanelLayout)layout.findViewById(R.id.sliding_layout);
        slidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
        final Context context = this.getContext();
        View.OnClickListener addAlarmListener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(slidingLayout.getPanelState() == SlidingUpPanelLayout.PanelState.HIDDEN)
                    slidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
                else slidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
//                Toast.makeText(context, "Add Alarm", Toast.LENGTH_SHORT).show();
            }
        };
        addBtn.setOnClickListener(addAlarmListener);

        btn_register = (Button)layout.findViewById(R.id.btn_register);
        btn_register.setOnClickListener(this);

        alarmManager = (AlarmManager)activity.getSystemService(Context.ALARM_SERVICE);
        return layout;
    }

    private boolean setAlarm(int hour, int min, int days, boolean rept){
        Intent mAlarmIntent = new Intent("com.alarmate.application.ALARM_START");
        PendingIntent mPendingIntent = PendingIntent.getBroadcast(
                activity,
                i,
                mAlarmIntent,
                PendingIntent.FLAG_UPDATE_CURRENT

        );
        i++;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, min);
        calendar.set(Calendar.SECOND, days);

        Log.i("myTag", "알람 세팅 : " +  calendar.getTimeInMillis() + ", idx : " + i);
        alarmManager.set(
                AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(),
                mPendingIntent
        );
        return true;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_register){
            if(txt_hour.getText() != null && txt_min.getText() != null){
                int hour = Integer.parseInt(txt_hour.getText().toString());
                int min = Integer.parseInt(txt_min.getText().toString());
                setAlarm(hour, min, 0, false);
            }
        }
    }
}
