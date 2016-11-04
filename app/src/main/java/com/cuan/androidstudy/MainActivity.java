package com.cuan.androidstudy;

import android.content.Intent;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cuan.androidstudy.TestAppServiceManager.Main2Activity;
import com.cuan.androidstudy.TestAppServiceManager.TestService;
import com.cuan.appServiceManager.ServiceManager;
import com.cuan.tool.log.MLog;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MLog.i("ServiceManagerProvider","Main activity pid: "+ Process.myPid());
        /**
         * 初始化 ServiceManager
         */
        ServiceManager.init(this);
        /**
         * 注册service
         */
        ServiceManager.addService("TestService",new TestService());

        startActivity(new Intent(this, Main2Activity.class));
        startService(new Intent(this,MyService.class));
    }
}
