package com.cuan.androidstudy.TestAppServiceManager;

import android.os.Process;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cuan.androidstudy.R;
import com.cuan.appServiceManager.ServiceManager;
import com.cuan.tool.log.MLog;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        /**
         *
         */
        MLog.i("ServiceManagerProvider","client pid: "+ Process.myPid());
        ServiceManager.init(this);
        ITestService testService =  ITestService.Stub.asInterface(ServiceManager.getService("TestService"));
        try {
            MLog.i("ServiceManagerProvider","1 + 2 = "+testService.getAdd(1,2));
            MLog.i("ServiceManagerProvider","1 + 6 = "+testService.getAdd(1,6));
            MLog.i("ServiceManagerProvider","1 + 5 = "+testService.getAdd(1,5));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
