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

import com.cuan.tool.log.MLog;
import com.cuan.tool.precondition.Preconditions;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;

/**
 * 仿照 Android 的 ServiceManager的实现。
 *
 * 保证每个进有且仅有一个该对象的实例；
 *
 */
public class ServiceManager {

    private static final String TAG = "AppServiceManager";

    public static final String SCHEME_CONTENT = "content";

    private static IServiceManager sServiceManager = null;
    private static boolean initSuccess  = false;
    public static final int RET_ERR                 = 0;
    public static final int RET_OK                  = 1;
    public static final int LOCAL_ALLREADY_ADDED    = 2;
    public static final int REMOTE_ALLREADY_ADDED   = 3;
    public static final int PARAM_IS_NULL = 4;
    public static final int SERVICE_IS_DEAD         = 5;


    public static boolean isInit(){
        return initSuccess;
    }
    public static void init(Context context){
        if(sServiceManager == null || (sServiceManager != null && !sServiceManager.asBinder().pingBinder())){
            Preconditions.checkNotNull(context,"context is null.");
            Uri uri = Uri.parse("content://"+ ServiceManagerProvider.AUTHORITIES);
            Bundle bundle = new Bundle();
            bundle.putInt(ServiceManagerProvider.CODE, ServiceManagerProvider.GET_SERVICEMANAGER);
            Bundle res = context.getContentResolver().call(uri, ServiceManagerProvider.GetServiceManager,null,bundle);
            IBinder binder = res.getBinder(ServiceManagerProvider.BINDER);
            Preconditions.checkNotNull(binder," binder is null");
            sServiceManager = IServiceManager.Stub.asInterface(binder);
            if(sServiceManager != null)
                initSuccess = true;
        }
    }

    private ServiceManager(){
        throw new UnsupportedOperationException("  Prohibit to create  ServiceManager object.");
    }

    /**
     * 缓存查询结果，提高查询效率
     */
    private static HashMap<String,IBinder> sCache = new HashMap<String,IBinder>();

    /**
     * 以 service name 来获取 service IBinder
     * @param name
     * @return
     */
    public static  IBinder getService(String name){
        Preconditions.check(initSuccess,"ServiceManager must be initialized before using,please exec ServiceManager.init(Context).");
        IBinder binder = sCache.get(name);
        if(binder != null && binder.isBinderAlive())
            return binder;
        if(initSuccess){
            try {
                binder = sServiceManager.getService(name);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }else{
            MLog.e(TAG,">>>>> ServiceManager is not init. <<<<<");
        }
      return binder;
    }

    /**
     * 添加，即注册 service
     * @param name
     * @param binder
     */
    public  static int addService(@NonNull String name,@NonNull IBinder binder){
        Preconditions.check(initSuccess,"ServiceManager must be initialized before using,please exec ServiceManager.init(Context).");
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
    public static int removeService(String name){
        return 0;
    }

    /**
     * 返回所有正在运行的 service name
     * @return
     */
    public static String[] listServices(){
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
