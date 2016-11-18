package zhangchuzhao.site.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import zhangchuzhao.site.demo.R;

public class MyService extends Service {

    private DownloadBinder downloadBinder = new DownloadBinder();

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("service", "onBind");
        return downloadBinder;
    }

    //在服务第一次创建的时候调用
    @Override
    public void onCreate(){
        super.onCreate();
        Log.d("service", "onCreate");
        //前台服务
        final int NOTIFICATION_ID = 666;
        Intent intent = new Intent(this, ServiceActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        Notification.Builder notificationBuilder = new Notification.Builder(this);
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        notificationBuilder.setTicker("This is a greeting from Devin");
        notificationBuilder.setContentTitle("Devin's Greeting");
        notificationBuilder.setContentText("Best wishes to you");
        notificationBuilder.setContentIntent(pendingIntent);
        Notification notification = notificationBuilder.build();
        startForeground(NOTIFICATION_ID, notification);
    }

    //每次服务启动的时候调用
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Log.d("service", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    //服务销毁的时候调用
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d("service", "onDestroy");
    }

    /**
     * 任何一个服务在整个应用程序范围内都是通用的，即 MyService不仅可以和 MainActivity绑定，还可以和任何一个其他的活动进行绑定，而且在绑定完成后它们都 可以获取到相同的 DownloadBinder实例。
     */
    class DownloadBinder extends Binder{
        public void startDownload(){
            Log.d("service", "startDownload executed");
        }

        public int getProgress(){
            Log.d("service", "getProgress executed");
            return 0;
        }
    }
}
