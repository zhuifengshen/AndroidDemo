package zhangchuzhao.site.broadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Button;

import zhangchuzhao.site.demo.R;
import zhangchuzhao.site.skill.Util;

public class BroadcastActivity extends Activity {

    public String BROADCAST_ACTION = "site.zhangchuzhao.broadcast.MY_BROADCAST";

    private NetworkChangeReceiver networkChangeReceiver;
    private LocalReceiver localReceiver;
    private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
        //动态广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver, intentFilter);
        //静态广播
        Button broadcastButton = (Button)findViewById(R.id.button_broadcast);
        broadcastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BROADCAST_ACTION);
                //sendBroadcast(intent);
                sendOrderedBroadcast(intent, null);
            }
       });
        //本地广播
        IntentFilter locatIntentFilter = new IntentFilter();
        locatIntentFilter.addAction(BROADCAST_ACTION);
        localReceiver = new LocalReceiver();
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localBroadcastManager.registerReceiver(localReceiver, locatIntentFilter);
        Button localButton = (Button)findViewById(R.id.button_local_broadcast);
        localButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BROADCAST_ACTION);
                localBroadcastManager.sendBroadcast(intent);
            }
        });
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        //动态广播退出时需要记得取消注册
        unregisterReceiver(networkChangeReceiver);
        //本地广播注销
        localBroadcastManager.unregisterReceiver(localReceiver);
    }

    /**
     * 网络监听广播
     */
    private class NetworkChangeReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()){
                Util.showToastMessage(context, "network is available");
            }else{
                Util.showToastMessage(context, "network in unavailable");
            }
        }
    }
    /**
     * 本地广播接收器
     */
    private class LocalReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Util.showToastMessage(context, "received local broadcast");
        }
    }
}
