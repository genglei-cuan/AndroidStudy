package com.cuan.appServiceManager;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;


/**
 *  利用 Android 系统本身的一个 Bug 让用户无感知的将 ServiceManager进程的
 *  优先级提升至前台 Service 的级别，其中oom_adj = 1；
 *
 *  原理：
 */
public class EmptyService extends Service {
    private final static int SERVICE_ID = 1001;
    public EmptyService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent innerIntent = new Intent(this, InnerService.class);
        startService(innerIntent);
        startForeground(SERVICE_ID, new Notification());
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    public static  class InnerService extends Service {
        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            startForeground(SERVICE_ID, new Notification());
            stopForeground(true);
            stopSelf();
            return super.onStartCommand(intent, flags, startId);
        }

        @Override
        public IBinder onBind(Intent intent) {
            // TODO: Return the communication channel to the service.
            throw new UnsupportedOperationException("Not yet implemented");
        }
    }
}
