package com.alarmate.application;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vp = (ViewPager)findViewById(R.id.vp);

        Button btn1 = (Button)findViewById(R.id.btn_tab1);
        Button btn2 = (Button)findViewById(R.id.btn_tab2);
        Button btn3 = (Button)findViewById(R.id.btn_tab3);



        vp.setAdapter(new PageAdapter(this.getSupportFragmentManager()));
        vp.setCurrentItem(0);

        View.OnClickListener movePageListener = new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                int tag = (int)v.getTag();
                vp.setCurrentItem(tag);
            }
        };

        btn1.setOnClickListener(movePageListener);
        btn2.setOnClickListener(movePageListener);
        btn3.setOnClickListener(movePageListener);
        btn1.setTag(0);
        btn2.setTag(1);
        btn3.setTag(2);



        Log.i("myTag", "hello");
        
    }
}
