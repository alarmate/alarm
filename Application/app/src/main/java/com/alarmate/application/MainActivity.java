package com.alarmate.application;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.aigestudio.wheelpicker.WheelPicker;
import com.astuetz.PagerSlidingTabStrip;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    private ViewPager vp;
    public static int i = 0;
    private PagerSlidingTabStrip tabStrip;
    private FloatingActionButton addBtn;
//    private Button btn_register;
//    private EditText txt_hour, txt_min;
    private Animation fabClockWise, fabAntiClockWise;
//    private SlidingUpPanelLayout slidingLayout;
//    private boolean openSlide;
    private AlarmManager alarmManager;
    private Toolbar toolbar;
    private TextView toolbar_text;

    // 디비매니저 , 리스트 뷰 추가
    private DB dbManager;
    MyListAdapter alarmListAdapter;
    ListView alarmList;
    Switch alarm_enable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("tinyhhj" , "### life cycle : MainActivity onCreate start ###");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vp = (ViewPager) findViewById(R.id.vp);

        vp.setAdapter(new PageAdapter(this.getSupportFragmentManager()));
        vp.setCurrentItem(0);

        tabStrip = (PagerSlidingTabStrip) findViewById(R.id.tab_strip);
        tabStrip.setViewPager(vp);

        toolbar = (Toolbar) findViewById(R.id.toolbar); //툴바설정
        toolbar_text = (TextView) findViewById(R.id.toolbar_text);
        toolbar.setTitle("");
        toolbar_text.setText("알람목록");
        setSupportActionBar(toolbar);//액션바와 같게 만들어줌

        addBtn = (FloatingActionButton)findViewById(R.id.add_alarm);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0 :
                        addBtn.show();
                        toolbar_text.setText("알람목록");
                        break;
                    case 1 :
                        toolbar_text.setText("탭2");
                        addBtn.hide();
                        break;
                    case 2 :
                        toolbar_text.setText("탭3");
                        addBtn.hide();
                        break;
                    default :
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        fabClockWise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_clockwise);
        fabAntiClockWise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_anticlockwise);


//        txt_hour = (EditText)findViewById(R.id.add_hour);
//        txt_min = (EditText)findViewById(R.id.add_min);

//        slidingLayout = (SlidingUpPanelLayout)findViewById(R.id.sliding_layout);
//        slidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
        View.OnClickListener addAlarmListener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AlarmRegisterActivity.class));
                overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_hold);
//
//                if(slidingLayout.getPanelState() == SlidingUpPanelLayout.PanelState.HIDDEN){
//                    addBtn.startAnimation(fabClockWise);
//                    slidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
//                    openSlide = true;
//                }
//                else {
//                    slidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
//                    addBtn.startAnimation(fabAntiClockWise);
//                    openSlide = false;
//                }
//                Toast.makeText(context, "Add Alarm", Toast.LENGTH_SHORT).show();
            }
        };

        addBtn.setOnClickListener(addAlarmListener);
//        slidingLayout.setTouchEnabled(false);


//        btn_register = (Button)findViewById(R.id.btn_register);
//        btn_register.setOnClickListener(this);
//        WheelPicker wheelPicker = (WheelPicker)findViewById(R.id.wheel);
//        List<Integer> hours = new ArrayList<Integer>();
//        for(int i=1; i<=12; i++)hours.add(i);
//        wheelPicker.setData(hours);
//        WheelPicker.OnItemSelectedListener ItemSelectlistener = new WheelPicker.OnItemSelectedListener()
//        {
//            @Override
//            public void onItemSelected(WheelPicker picker, Object data, int position) {
//                picker.setSelectedItemPosition(position);
//            }
//        };
//
//        wheelPicker.setOnItemSelectedListener(ItemSelectlistener);
        alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

    }


//    @Override
//    public void onClick(View v) {
//        if(v.getId()==R.id.btn_register){
//            if(txt_hour.getText() != null && txt_min.getText() != null){
//                int hour = Integer.parseInt(txt_hour.getText().toString());
//                int min = Integer.parseInt(txt_min.getText().toString());
//                setAlarm(hour, min, 0, false);
//            }
//        }
//
//    }

    private boolean setAlarm(int hour, int min, int days, boolean rept){
        Intent mAlarmIntent = new Intent("com.alarmate.application.ALARM_START");
        PendingIntent mPendingIntent = PendingIntent.getBroadcast(
                this,
                i,
                mAlarmIntent,
                PendingIntent.FLAG_UPDATE_CURRENT

        );
        i++;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, min);
        calendar.set(Calendar.SECOND, days);

        //2017-05-08 : 알람리스트에 새로운 알람 추가
        alarmListAdapter.add(new AlarmItem().setTime(calendar.getTimeInMillis()));

        Log.i("myTag", "알람 세팅 : " +  calendar.getTimeInMillis() + ", idx : " + i);
        alarmManager.set(
                AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(),
                mPendingIntent
        );
        return true;
    }


    /* 2017-05-03 : onStart에서 디비 접속 */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("tinyhhj" , "### life cycle : MainActivity onStart start ###");


    }


//    public interface onKeyBackPressedListener {
//        public void onBack();
//    }
//    private onKeyBackPressedListener mOnKeyBackPressedListener;
//
//    public void setOnKeyBackPressedListener(onKeyBackPressedListener listener) {
//        mOnKeyBackPressedListener = listener;
//    }


//    /* 2017-05-09 : 이성진 : 프래그먼트에서 뒤로가기 키 구현 (인터페이스), FragmentOne에서 implement */
//    @Override
//    public void onBackPressed() {
//        if(openSlide) {
//            slidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
//            addBtn.startAnimation(fabAntiClockWise);
//            openSlide = false;
//        }
//        else
//            super.onBackPressed();
//    }
}



//        ###PagerSlidingTabStrip 으로 변경하여 불필요###
//
//        View.OnClickListener movePageListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int tag = (int) v.getTag();
//                int i = 0;
//                while (i < 3) {
//                    if (tag == i) ll.findViewWithTag(i).setSelected(true);
//                    else ll.findViewWithTag(i).setSelected(false);
//                    i++;
//                }
//                vp.setCurrentItem(tag);
//            }
//
//        };
//
//        ViewPager.OnPageChangeListener vpChngListener = new ViewPager.OnPageChangeListener() {
//
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                int i = 0;
//                while (i < 3) {
//                    if (position == i) {
//                        ll.findViewWithTag(i).setSelected(true);
//                    } else {
//                        ll.findViewWithTag(i).setSelected(false);
//                    }
//                    i++;
//                }
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        };