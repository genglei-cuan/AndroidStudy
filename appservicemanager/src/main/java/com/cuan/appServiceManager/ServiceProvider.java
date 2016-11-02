package com.cuan.appServiceManager;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.cuan.tool.precondition.Preconditions;

import java.util.HashMap;

/**
 * Created by genglei-cuan on 16-11-1.
 */

public class ServiceProvider extends BaseProvider {

    private static String TAG = "ServiceProvider";


    public static final String AUTHORITIES = "com.cuan.appservicemanager.ServiceProvider";

    public static final String CODE = "code";
    public static final String BINDER = "Binder";

    public static final int GET_SERVICEMANAGER  = 100;
    public static final int ADD_SERVICE         = 101;
    public static final int GET_SERVICE         = 102;
    public static final int REMOVE_SERVICE      = 103;

    public static final String GetServiceManager = "GetServiceManager";
    public static final String AddService = "AddService";
    public static final String GetService = "GetService";
    public static final String RemoveService = "RemoveService";


    private ServiceManagerNative sServiceManager;
    @Override
    public boolean onCreate() {
        sServiceManager = new ServiceManagerNative();
        return false;
    }

    /**
     *  要保证线程安全
     * @param method
     * @param arg
     * @param extras
     * @return
     */
    @Nullable
    @Override
    public Bundle call(String method, String arg, Bundle extras) {
        Preconditions.checkNotNull(extras,"Bundle in ServiceProvider.call() is null.");
        int code = extras.getInt("code");
        Preconditions.check(method.equals(CodeToString(code)),"the method's code you call is err.");
        switch (code){
            case GET_SERVICEMANAGER:
                return getServiceManager();
            case ADD_SERVICE:
                return addService(method,extras);

        }
        return null;
    }

    private Bundle getServiceManager(){
        Bundle bundle = new Bundle();
        bundle.putBinder("value",sServiceManager);
        return bundle;
    }
    private Bundle addService(String methodName,Bundle extras){
        Bundle bundle = new Bundle();
        return bundle;
    }

    private String CodeToString(int code){
        return null;
    }
}
