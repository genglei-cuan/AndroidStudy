package com.cuan.tool.log;

/**
 * Created by genglei-cuan on 16-10-31.
 */

public class MLog {

    private static boolean mEnable = true;

    public static void i(String tag,String message,Object... args){
        if(mEnable){
            message = message == null ? "***** Log message is null*****" : String.format(message, args);
            android.util.Log.i(tag,message);
        }
    }

    public static void e(String tag,String message,Object... args){
        if(mEnable){
            message = message == null ? "***** Log message is null*****" : String.format(message, args);
            android.util.Log.e(tag,message);
        }
    }

    public static void i(String tag,String message,Throwable tr){
        if(mEnable){
            android.util.Log.i(tag,message,tr);
        }
    }

    public static void e(String tag,String message,Throwable tr){
        if(mEnable){
            android.util.Log.e(tag,message,tr);
        }
    }

}
