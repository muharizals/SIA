package com.tumpi.sia.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by muharizals on 02/07/2016.
 */
public class UserUtils {
    private static UserUtils instance;
    private SharedPreferences preferences;

    private UserUtils(){

    }

    public static UserUtils getInstance(Context context){
        if(instance == null){
                instance = new UserUtils();
                instance.preferences = PreferenceManager.getDefaultSharedPreferences(context);
        }

        return instance;
    }

    public String getStringProperty(String key){
        return preferences.getString(key,"");
    }

    public void setStringProperty(String key,String value){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key,value);
    }

    public boolean getBoleanProperty(String key){
        return preferences.getBoolean(key,false);
    }

    public void setBoleanProperty(String key,boolean value){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key,value);
    }

    public int getIntProperty(String key){
        return preferences.getInt(key,0);
    }

    public void setIntProperty(String key,int value){
        SharedPreferences.Editor editor =preferences.edit();
        editor.putInt(key,value);
    }

    /**
     * clearing all saved prefecence, or use when user logg out.
     */

    public void reset(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear().apply();
    }

    /**
     * function fo function hehe
     */

    public void logOut(){
        reset();
    }

}
