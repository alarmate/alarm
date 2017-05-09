package com.alarmate.application;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.astuetz.PagerSlidingTabStrip;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    //    private AlarmManager alarmManager;
    private ViewPager vp;
    private LinearLayout ll;
    public static int i = 0;
    private PagerSlidingTabStrip tabStrip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vp = (ViewPager) findViewById(R.id.vp);
        ll = (LinearLayout) findViewById(R.id.ll);

        vp.setAdapter(new PageAdapter(this.getSupportFragmentManager()));
        vp.setCurrentItem(0);
        View.OnClickListener movePageListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tag = (int) v.getTag();
                int i = 0;
                while (i < 3) {
                    if (tag == i) ll.findViewWithTag(i).setSelected(true);
                    else ll.findViewWithTag(i).setSelected(false);
                    i++;
                }
                vp.setCurrentItem(tag);
            }

        };

        ViewPager.OnPageChangeListener vpChngListener = new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int i = 0;
                while (i < 3) {
                    if (position == i) {
                        ll.findViewWithTag(i).setSelected(true);
                    } else {
                        ll.findViewWithTag(i).setSelected(false);
                    }
                    i++;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

                tabStrip = (PagerSlidingTabStrip) findViewById(R.id.tab_strip);
                tabStrip.setViewPager(vp);
            }
        };
    }

    /* 2017-05-03 : onStart에서 디비 접속 */
    @Override
    protected void onStart() {
        super.onStart();


    }
}