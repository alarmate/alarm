package com.alarmate.application;

/**
 * Created by seongjinlee on 2017. 5. 3..
 */

public interface DBAlarm {
    public int DBConnect();
    public int addAlarm(AlarmItem item);
    public int updateAlarm(AlarmItem item);
    public int removeAlarm(AlarmItem item);
    public AlarmItem[] getAlarms();

}
