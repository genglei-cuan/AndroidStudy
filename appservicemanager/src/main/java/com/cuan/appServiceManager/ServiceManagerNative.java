package com.cuan.appServiceManager;

import android.os.IBinder;
import android.os.RemoteException;

import java.util.HashMap;

/**
 * Created by genglei-cuan on 16-11-2.
 */

public class ServiceManagerNative extends IServiceManager.Stub{

    /**
     * 由于IPC请求导致该变量可能在不同的binder线程中被操作，所以对其的操作要考虑同步的问题
     */
    private HashMap<String,IBinder> mBinderList = new HashMap<String,IBinder>();

    @Override
    public IBinder getService(String name) throws RemoteException {
        return mBinderList.get(name);
    }

    @Override
    public IBinder checkService(String name) throws RemoteException {
        return null;
    }

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
    public int resoveService(String name) throws RemoteException {
        return 0;
    }

    @Override
    public String[] listServices() throws RemoteException {
        return new String[0];
    }


}
