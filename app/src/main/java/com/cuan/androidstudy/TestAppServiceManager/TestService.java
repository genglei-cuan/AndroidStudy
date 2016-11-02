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
        MLog.i("ServiceProvider","server pid: "+ Process.myPid());
        return "shajia";
    }

    @Override
    public int getAdd(int a, int b) throws RemoteException {
        MLog.i("ServiceProvider","server pid: "+ Process.myPid());
        return a+b;
    }
}
