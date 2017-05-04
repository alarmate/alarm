package com.alarmate.application;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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

import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;
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
    Animation fabClockWise, fabAntiClockWise;
    private boolean isOpen = true;
    FloatingActionButton addBtn;

    // 디비매니저 , 리스트 뷰 추가
    private DB dbManager;
    MyListAdapter alarmListAdapter;
    ListView alarmList;
    Switch alarm_enable;


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
        addBtn = (FloatingActionButton)layout.findViewById(R.id.add_alarm);
        fabClockWise = AnimationUtils.loadAnimation(activity.getApplicationContext(), R.anim.rotate_clockwise);
        fabAntiClockWise = AnimationUtils.loadAnimation(activity.getApplicationContext(), R.anim.rotate_anticlockwise);


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
                if(isOpen){
                    addBtn.startAnimation(fabClockWise);
                    isOpen = false;
                }
                else {
                    addBtn.startAnimation(fabAntiClockWise);
                    isOpen = true;
                }
//                Toast.makeText(context, "Add Alarm", Toast.LENGTH_SHORT).show();
            }
        };
        addBtn.setOnClickListener(addAlarmListener);

        btn_register = (Button)layout.findViewById(R.id.btn_register);
        btn_register.setOnClickListener(this);

        alarmManager = (AlarmManager)activity.getSystemService(Context.ALARM_SERVICE);


        dbManager = new DB(activity);
        alarmList = (ListView) layout.findViewById(R.id.alarm_list);
//        alarmList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                AlarmItem alarm = (AlarmItem) parent.getItemAtPosition(position);
//                Switch sw = (Switch) view.findViewById(R.id.alarm_enable_button);
//                alarm.setEnable(sw.isChecked()?"Y":"N");
//                Log.d("tinyhhj",alarm.getEnable());
//            }
//        });


        dbManager.DBConnect();

        ArrayList<AlarmItem> lists = dbManager.getAlarms();
//        for(int i = 0 ; i <lists.size() ; i++)
//            Log.d("tinyhhj","lists : " + lists.get(i).showAInfo());
        alarmListAdapter = new MyListAdapter(activity, lists);

        alarmList.setAdapter(alarmListAdapter);

        dbManager.DBClose();
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
    /*************************************************************/
    /* AlarmItem lists를 view로 보여주는 adapter*/
    /*************************************************************/
    public class MyListAdapter extends ArrayAdapter<AlarmItem>
    {
        ArrayList<AlarmItem> originArr;

        public MyListAdapter (Context context, ArrayList<AlarmItem> alarms)
        {
            super(context, 0 , alarms);
            originArr = alarms;
        }

        @Override
        public View getView(int position , View convertView , ViewGroup parent)
        {
            AlarmItem alarm = getItem(position);
            final int idx = position;
            if(convertView == null)
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item , parent , false);
            TextView lists_alarm_title = (TextView) convertView.findViewById(R.id.alarm_title);
            TextView lists_alarm_time  = (TextView) convertView.findViewById(R.id.alarm_time);
            Switch lists_alarm_enable_button = (Switch) convertView.findViewById(R.id.alarm_enable_button);
            lists_alarm_enable_button.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    originArr.get(idx).setEnable(isChecked?"Y":"N");
                }
            });

            lists_alarm_title.setText(alarm.getTitle());
            lists_alarm_time.setText(alarm.getTime());
            lists_alarm_enable_button.setChecked((alarm.getEnable().equals("Y"))?true:false);


            return convertView;
        }
        public ArrayList<AlarmItem> getModifyList()
        {
            return originArr;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        ArrayList<AlarmItem> ptr = alarmListAdapter.getModifyList();
        dbManager.DBConnect();
        for(int i = 0 ; i < ptr.size() ; i++)
            dbManager.updateAlarm(ptr.get(i));
        dbManager.DBClose();

    }
}
