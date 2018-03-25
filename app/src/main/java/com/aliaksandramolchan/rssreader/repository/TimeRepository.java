package com.aliaksandramolchan.rssreader.repository;


import android.content.Context;
import android.content.SharedPreferences;

import com.aliaksandramolchan.rssreader.Extras;

import java.util.Calendar;

public class TimeRepository {

    private SharedPreferences.Editor editor;

    public void storeTime(Context context, String time) {
        SharedPreferences preferences =
                context.getSharedPreferences(Extras.PREF, Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.putString(Extras.PREF_KEY_TIME, time);
        editor.commit();
    }


    public void reset() {
        if (editor != null) {
            editor.clear();
            editor.commit();
        }
    }

    public String getCurrentTime() {
        return Calendar.getInstance().getTime().toString();
    }


}
