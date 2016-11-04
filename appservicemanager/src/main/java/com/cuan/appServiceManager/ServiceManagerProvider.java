package com.cuan.appServiceManager;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.cuan.tool.log.MLog;
import com.cuan.tool.precondition.Preconditions;


/**
 * Created by genglei-cuan on 16-11-1.
 */

/**
 * 充当 App 中进程获取 ServiceManager 的媒介。
 *
 * 同一个 App 中的进程都可以通过它获取 ServiceManager 的 IBinder 对象。
 */
public class ServiceManagerProvider extends BaseProvider {

    private static String TAG = "ServiceManagerProvider";


    public static final String AUTHORITIES = "com.cuan.appServiceManager.ServiceManagerProvider";

    public static final String CODE = "code";
    public static final String BINDER = "Binder";

    public static final int GET_SERVICEMANAGER  = 100;


    public static final String GetServiceManager = "GetServiceManager";



    private ServiceManagerNative sServiceManager;

    /**
     * Provider 创建完成之后， ServiceManager 就创建完毕，可以等待客户端请求了。
     * 这里可以添加运行在本进程中的 service ,类似 SystemServer进程。
     * @return
     */
    @Override
    public boolean onCreate() {
        sServiceManager = ServiceManagerNative.getInstance();
        AppServer.main();
        // 提高优先级
        getContext().startService(new Intent(getContext(),EmptyService.class));
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
        Preconditions.checkNotNull(extras,"Bundle in ServiceManagerProvider.call() is null.");
        int code = extras.getInt("code");
        Preconditions.check(method.equals(CodeToString(code)),"the method's code you call is err.");
        switch (code){
            case GET_SERVICEMANAGER:
                return getServiceManager();

        }
        return null;
    }

    private Bundle getServiceManager(){
        Bundle bundle = new Bundle();
        bundle.putBinder(BINDER,sServiceManager);
        if(sServiceManager == null)
            MLog.e(TAG,">>>>> Service Manager is not create. <<<<<");
        return bundle;
    }

    private Bundle startAppServer(){
        Bundle bundle = new Bundle();
        return bundle;
    }

    private String CodeToString(int code){
        switch (code){
            case GET_SERVICEMANAGER:
                return GetServiceManager;
        }
        return null;
    }
}
