package com.alarmate.application;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by seongjinlee on 2017. 5. 9..
 */

public class AlarmRegisterActivity extends Activity implements View.OnClickListener{
    private Button btn_register;
    private EditText txt_hour, txt_min;
    public AlarmRegisterActivity() {


    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_alarm);
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.slide_hold, R.anim.slide_in_top);
    }

    @Override
    public void onClick(View v) {
//        if(v.getId()==R.id.btn_register){
//            if(txt_hour.getText() != null && txt_min.getText() != null){
//                int hour = Integer.parseInt(txt_hour.getText().toString());
//                int min = Integer.parseInt(txt_min.getText().toString());
//                setAlarm(hour, min, 0, false);
//            }
//        }

    }
}
