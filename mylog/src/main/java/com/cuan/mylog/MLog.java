package com.cuan.mylog;

/**
 * Created by genglei-cuan on 16-10-31.
 */

public class MLog {

    private static boolean mEnable = true;

    static void i(String tag,String format,Object... obj){
        if(mEnable){
            if(format != null && obj != null)
                format = String.format(format,obj);
            android.util.Log.i(tag,format);
        }
    }

    static void e(String tag,String format,Object... obj){
        if(mEnable){
            if(format != null && obj != null)
                format = String.format(format,obj);
            android.util.Log.e(tag,format);
        }
    }

    static void i(String tag,String format,Throwable tr){
        if(mEnable){
            android.util.Log.i(tag,format,tr);
        }
    }

    static void e(String tag,String format,Throwable tr){
        if(mEnable){
            android.util.Log.e(tag,format,tr);
        }
    }

}
