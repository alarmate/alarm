package com.alarmate.application;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by tinyhhj on 2017-05-03.
 */

public class AlarmItem {
    private long aId;
    private int aStatus;
    private Calendar aCal;
    private SimpleDateFormat sdf;

    //알람 초기화
    public AlarmItem() {
        aId = 0;
        aStatus = 0;
        aCal = Calendar.getInstance();
        sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    }

    public void setTime(long d) {
        aCal.setTimeInMillis(d);
    }

    public void setTime(int year, int month, int day, int hour, int minute) {
        aCal.clear();
        aCal.set(year, month, day, hour, minute, 0);
    }

    public String showAInfo() {
        return "id : " +aId + " status : " + aStatus + " time : " + sdf.format(aCal.getTime());
    }

    public int getStatus() {
        return aStatus;
    }

    public long getTimeinMillis() {
        return aCal.getTimeInMillis();
    }

    public long getId() {
        return aId;
    }

    public void setId(long id) {
        aId = id;
    }

    public void setStatus(int stat)
    {
        aStatus = stat;
    }




}
