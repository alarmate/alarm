package com.alarmate.application;

/**
 * Created by seongjinlee on 2017. 5. 3..
 */

public class DB implements DBAlarm {

    @Override
    public int DBConnect() {
        return 0;
    }

    @Override
    public int addAlarm(AlarmItem item) {
        return 0;
    }

    @Override
    public int updateAlarm(AlarmItem item) {
        return 0;
    }

    @Override
    public int removeAlarm(AlarmItem item) {
        return 0;
    }

    @Override
    public AlarmItem[] getAlarms() {
        return new AlarmItem[0];
    }
}
