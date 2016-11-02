package com.cuan.appServiceManager;

/**
 * Created by genglei-cuan on 16-10-31.
 */

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;

import com.cuan.tool.precondition.Preconditions;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;

/**
 * 仿照 Android 的 ServiceManager的实现。
 *
 * 实际上是让一个 ContentProvider 充当 ServiceManager 的角色
 */
public class ServiceManager {

    private static final String TAG = "AppServiceManager";

    public static final String SCHEME_CONTENT = "content";

    private static IServiceManager sServiceManager = null;
    private static ServiceManager instance = null;

    public static final int RET_ERR                 = 0;
    public static final int RET_OK                  = 1;
    public static final int LOCAL_ALLREADY_ADDED    = 2;
    public static final int REMOTE_ALLREADY_ADDED   = 3;
    public static final int PARAM_IS_NULL = 4;
    public static final int SERVICE_IS_DEAD         = 5;

    public static boolean isInit(){
        return sServiceManager == null ? false : true;
    }
    public static ServiceManager initAndGetInstance(Context context){
        if(sServiceManager == null || (sServiceManager != null && !sServiceManager.asBinder().isBinderAlive())){
            Preconditions.checkNotNull(context,"context is null.");
            Uri uri = Uri.parse("content://"+ ServiceProvider.AUTHORITIES);
            Bundle bundle = new Bundle();
            bundle.putInt(ServiceProvider.CODE,ServiceProvider.GET_SERVICEMANAGER);
            Bundle res = context.getContentResolver().call(uri,ServiceProvider.GetServiceManager,null,bundle);
            sServiceManager = IServiceManager.Stub.asInterface(res.getBinder(ServiceProvider.BINDER));
            instance = new ServiceManager();
        }
        return instance;
    }

    private ServiceManager(){}

    /**
     * 缓存查询结果，提高查询效率
     */
    private static HashMap<String,IBinder> sCache = new HashMap<String,IBinder>();

    /**
     * 以 service name 来获取 service IBinder
     * @param name
     * @return
     */
    public  IBinder getService(String name){
      return null;
    }

    /**
     * 添加，即注册 service
     * @param name
     * @param binder
     */
    public  int addService(@NonNull String name,@NonNull IBinder binder){
        if(sCache.get(name)!=null && sCache.get(name).isBinderAlive())
            return LOCAL_ALLREADY_ADDED;
        sCache.put(name,binder);
        int ret = RET_ERR;
        try {
            ret = sServiceManager.addService(name,binder);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 移除 service
     * 实际情况中，应该很少使用
     * @param name
     */
    public  int removeService(String name){
        return 0;
    }

    /**
     * 返回所有正在运行的 service name
     * @return
     */
    public  String[] listServices(){
        return null;
    }



    /**
     * 利用注解实现枚举（出于性能上的考虑，Android 不建议直接使用 enum）
     */
    @IntDef({RET_ERR,RET_OK, LOCAL_ALLREADY_ADDED,REMOTE_ALLREADY_ADDED, PARAM_IS_NULL,SERVICE_IS_DEAD})
    @Retention(RetentionPolicy.SOURCE)
    public @interface RET {
    }
}
