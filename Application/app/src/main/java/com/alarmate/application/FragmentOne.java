package com.alarmate.application;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by seongjinlee on 2017. 5. 1..
 * 기본 알람설정 페이지
 */

public class FragmentOne extends Fragment {
    public FragmentOne(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        CoordinatorLayout layout = (CoordinatorLayout)inflater.inflate(R.layout.fragment_one, container, false);
        FloatingActionButton addBtn = (FloatingActionButton)layout.findViewById(R.id.add_alarm);
        final Context context = this.getContext();
        View.OnClickListener addAlarmListener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Add Alarm", Toast.LENGTH_SHORT).show();
            }
        };

        addBtn.setOnClickListener(addAlarmListener);

        return layout;
    }
}
