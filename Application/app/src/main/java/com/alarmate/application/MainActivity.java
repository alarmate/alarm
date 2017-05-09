package com.alarmate.application;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import com.astuetz.PagerSlidingTabStrip;

public class MainActivity extends AppCompatActivity {
    //    private AlarmManager alarmManager;
    private ViewPager vp;
    public static int i = 0;
    private PagerSlidingTabStrip tabStrip;
    private FloatingActionButton addBtn;


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

        addBtn = (FloatingActionButton)findViewById(R.id.add_alarm);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 0){
                    addBtn.show();
                }else {
                    addBtn.hide();
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    /* 2017-05-03 : onStart에서 디비 접속 */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("tinyhhj" , "### life cycle : MainActivity onStart start ###");


    }


    /* 2017-05-09 : 이성진 : 프래그먼트에서 뒤로가기 키 구현 (인터페이스), FragmentOne에서 implement */
    public interface onKeyBackPressedListener {
        public void onBack();
    }
    private onKeyBackPressedListener mOnKeyBackPressedListener;

    public void setOnKeyBackPressedListener(onKeyBackPressedListener listener) {
        mOnKeyBackPressedListener = listener;
    }

    @Override
    public void onBackPressed() {
        if(mOnKeyBackPressedListener != null)
            mOnKeyBackPressedListener.onBack();
        else
            super.onBackPressed();
    }
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