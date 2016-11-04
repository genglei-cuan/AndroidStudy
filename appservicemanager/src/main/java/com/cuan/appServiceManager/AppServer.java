package com.cuan.appServiceManager;

import android.content.Context;

import com.cuan.tool.log.MLog;

/**
 * Created by genglei-cuan on 16-11-3.
 */

public class AppServer {

    private static final String TAG = "AppServer";
    private boolean mOnlyCore;
    private boolean mFirstBoot;

    public static void main(){
        new AppServer().run();
    }

    private  void run(){
        MLog.i(TAG,">>>>>> AppServer is stating... <<<<<< ");
        long currentTime = System.currentTimeMillis();
        startBootstrapServices();
        startCoreServices();
        startOtherServices();
        long time = System.currentTimeMillis();
        MLog.i(TAG,">>>>>> the AppServer started time-consuming: " + (time-currentTime)+"ms <<<<<<");
    }
    /**
     * 启动引导 Service
     *
     * TODO: 各个 service 启动阶段，如果有前后依赖怎么？ 需要添加不同执行阶段？
     */
    protected void startBootstrapServices() {

    }

    /**
     * 启动核心 service
     */
    protected void startCoreServices(){

    }

    /**
     * 启动其他 service
     */
    protected void startOtherServices(){

    }

    public static String getCurrentProcessName(Context context){
        return null;
    }
}
