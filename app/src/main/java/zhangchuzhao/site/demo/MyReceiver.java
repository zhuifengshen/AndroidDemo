package zhangchuzhao.site.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Devin on 2016/11/10.
 */

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        MyApplication myApp = (MyApplication)context.getApplicationContext();
        Log.d("context", "myReceiver's myApp: " + myApp);
    }
}
