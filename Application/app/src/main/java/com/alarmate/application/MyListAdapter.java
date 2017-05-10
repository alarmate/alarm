package com.alarmate.application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by donghoon shim on 2017. 5. 9..
 */

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
        // 리스트 아이템 안의 enable 버튼 리스너 등록
        // 리스트 리스너들은 getView 안에서 등록해둔다. layout id를 얻기위해??
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
