package com.cuan.appservicemanager;

/**
 * Created by genglei-cuan on 16-10-31.
 */

import android.os.IBinder;

/**
 * 仿照 Android 的 ServiceManager的实现。
 *
 * 实际上是通过 ContentProvider 查询，增加和删除来实现 service 的获取，注册和删除。
 */
public class ServiceManager {

    private static final String TAG = "AppServiceManager";

    public static IBinder getService(String name){
      return null;
    }

    public static void addService(String name,IBinder binder){

    }

    /**
     * 应该很少使用
     * @param name
     */
    public static void removeService(String name){

    }

    public static String[] listServices(){
        return null;
    }
}
