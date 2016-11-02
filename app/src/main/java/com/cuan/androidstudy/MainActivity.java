package com.cuan.androidstudy;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cuan.appServiceManager.ServiceProvider;
import com.cuan.tool.log.MLog;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MLog.i("shajia","测试一下！！！");

        Uri uri = Uri.parse("content://"+ ServiceProvider.AUTHORITIES);
        Bundle bundle = new Bundle();
        MLog.i("ServiceProvider","---before call--------");
        this.getContentResolver().call(uri,"test","test",bundle);
        this.getContentResolver().call(uri,"test","test",bundle);
        MLog.i("ServiceProvider","---after call--------");

    }
}
