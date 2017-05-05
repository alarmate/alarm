package com.alarmate.application;

import java.util.ArrayList;

/**
 * Created by seongjinlee on 2017. 5. 3..
 */

public interface DBAlarm {
    public int DBConnect();
    public int addAlarm(AlarmItem item);
    public int updateAlarm(AlarmItem item);
    public int removeAlarm(AlarmItem item);
    public ArrayList<AlarmItem> getAlarms();

}
