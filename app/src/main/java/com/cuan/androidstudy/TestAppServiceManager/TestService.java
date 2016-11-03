package com.cuan.androidstudy.TestAppServiceManager;

import android.os.Process;
import android.os.RemoteException;

import com.cuan.tool.log.MLog;

/**
 * Created by genglei-cuan on 16-11-2.
 */

public class TestService extends ITestService.Stub {
    @Override
    public String getName() throws RemoteException {
        MLog.i("ServiceManagerProvider","server pid: "+ Process.myPid());
        MLog.i("ServiceManagerProvider","Thread id : "+ Process.myTid());
        return "shajia";
    }

    @Override
    public int getAdd(int a, int b) throws RemoteException {
        MLog.i("ServiceManagerProvider","server pid: "+ Process.myPid());
        MLog.i("ServiceManagerProvider","thread id : "+ Process.myTid());
        return a+b;
    }
}
