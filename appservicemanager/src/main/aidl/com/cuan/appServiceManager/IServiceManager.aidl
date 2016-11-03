// IServiceManager.aidl
package com.cuan.appServiceManager;

// Declare any non-default types here with import statements

interface IServiceManager {

    IBinder getService(String name);

    IBinder checkService(String name);

    int addService(String name, IBinder service);

    int removeService(String name);

    String[] listServices();

}
