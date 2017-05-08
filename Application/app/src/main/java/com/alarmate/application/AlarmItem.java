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
    private SimpleDateFormat aSdf;
    private String aTitle;
    private String aContent;
    private String aEnable;
    private String aRepeat;
    private String aRingtone;

    //알람 초기화
    public AlarmItem() {
        aId = 0;
        aStatus = 0;
        aCal = Calendar.getInstance();
        aTitle = "default title";
        aContent = "default contents";
        aSdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        aEnable = "N";
        aRepeat = "N";
        aRingtone = "DEFUALT RINGBELL";
    }



    public String showAInfo() {
        return   "id : " +aId
                + " title : " + aTitle
                + " status : " + aStatus
                + " time : " + aSdf.format(aCal.getTime())
                + " content : " + aContent
                + " enable : " + aEnable
                + " repeat : " + aRepeat
                + " ringtone : " + aRingtone ;

    }

    /* getter */
    public int getStatus() {
        return aStatus;
    }
    public long getTimeinMillis() {
        return aCal.getTimeInMillis();
    }
    public long getId() {
        return aId;
    }
    public String getTitle() { return aTitle;}
    public String getContent() { return aContent; }
    public String getRingtone() { return aRingtone ;}
    public String getEnable() { return aEnable ;}
    public String getRepeat() { return aRepeat ;}
    public String getTime(SimpleDateFormat sdf ) { return sdf.format(aCal.getTime());}
    public String getTime() { return aSdf.format(aCal.getTime());}

    /* setter */
    public AlarmItem setId(long id) {
        aId = id;
        return this;
    }
    public AlarmItem setStatus(int stat)
    {
        aStatus = stat;
        return this;
    }
    public AlarmItem setTitle(String t)
    {
        aTitle = t;
        return this;
    }
    public AlarmItem setContent(String c)
    {
        aContent = c;
        return this;
    }
    public AlarmItem setTime(long d)
    {
        aCal.setTimeInMillis(d);
        return this;
    }
    public AlarmItem setTime(int year, int month, int day, int hour, int minute)
    {
        aCal.clear();
        aCal.set(year, month, day, hour, minute, 0);
        return this;
    }
    public AlarmItem setRingtone( String r)
    {
        aRingtone = r;
        return this;
    }
    public AlarmItem setEnable ( String e )
    {
        aEnable = e;
        return this;
    }
    public AlarmItem setRepeat(String r)
    {
        aRepeat = r;
        return this;
    }





}
