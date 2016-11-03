package com.cuan.appServiceManager;

import android.os.IBinder;
import android.os.RemoteException;

import java.util.HashMap;

/**
 * Created by genglei-cuan on 16-11-2.
 */

//TODO: 方法的返回值以及参数是否使用Bundle传入和传入更多的信息？

//TODO: 某些远端 service 进程被杀掉后，是否重启，以及如何重启？

public class ServiceManagerNative extends IServiceManager.Stub{

    /**
     * 由于IPC请求导致该变量可能在不同的binder线程中被操作，所以对其的操作要考虑同步的问题
     */
    private static HashMap<String,IBinder> mBinderList = new HashMap<String,IBinder>();

    private static ServiceManagerNative instance = null;
    public static ServiceManagerNative getInstance(){
        if(instance == null)
            instance = new ServiceManagerNative();
        return instance;
    }

    private ServiceManagerNative(){

    }
    @Override
    public IBinder getService(String name) throws RemoteException {
        IBinder binder = mBinderList.get(name);
        if(binder != null && binder.pingBinder())
            return binder;
        return null;
    }

    @Override
    public IBinder checkService(String name) throws RemoteException {
        return null;
    }

    /**
     * @param name
     * @param service
     * @return
     * @throws RemoteException
     */
    @ServiceManager.RET
    @Override
    public int addService(String name, IBinder service) throws RemoteException {
        if(service == null || name == null)
            return ServiceManager.PARAM_IS_NULL;
        if(!service.isBinderAlive())
            return ServiceManager.SERVICE_IS_DEAD;
        synchronized (mBinderList){
            if(mBinderList.get(name)!=null)
                return ServiceManager.REMOTE_ALLREADY_ADDED;
            mBinderList.put(name,service);
        }
        return ServiceManager.RET_OK;
    }

    @Override
    public int removeService(String name) throws RemoteException {
        return 0;
    }

    @Override
    public String[] listServices() throws RemoteException {
        return new String[0];
    }


}
