package com.alarmate.application;

/**
 * Created by seongjinlee on 2017. 5. 3..
 */

public interface DBAlarm {
    public int DBConnect();
    public int addAlarm();
    public int updateAlarm();
    public int removeAlarm();
}
