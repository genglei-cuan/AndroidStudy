package com.cuan.appServiceManager;

import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by genglei-cuan on 16-11-2.
 */

public class ServiceInfo {

    private String name;
    private int pid;
    private IBinder ibinder;

    @STATUS
    private int status;

    public static final int NOT_RUNING = 1;
    public static final int RUNING     = 2;

    public ServiceInfo(@NonNull  String name, int pid, @NonNull IBinder ibinder, @STATUS int status) {
        this.name = name;
        this.pid = pid;
        this.ibinder = ibinder;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public IBinder getIbinder() {
        return ibinder;
    }

    public void setIbinder(IBinder ibinder) {
        this.ibinder = ibinder;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * 利用注解实现枚举（出于性能上的考虑，Android 不建议直接使用 enum）
     */
    @IntDef({NOT_RUNING, RUNING})
    @Retention(RetentionPolicy.SOURCE)
    public @interface STATUS {
    }
}

