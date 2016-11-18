package zhangchuzhao.site.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Date;

import static android.app.AlarmManager.ELAPSED_REALTIME_WAKEUP;

/**
 * Created by Devin on 2016/11/16.
 */
public class LongRunningService extends Service{
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        //启动子线程执行任务, 避免ANR
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("service", "executed at " + new Date().toString());
                //Util.showToastMessage(LongRunningService.this, "executed at " + new Date().toString());
            }
        }).start();

        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        int tenSecond = 10 * 1000;
        long triggerAtTime = SystemClock.elapsedRealtime() + tenSecond;
        Intent alarmReceiverIntent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, alarmReceiverIntent, 0);

        //AlarmManager的工作类型有四种:ELAPSED_REALTIME、ELAPSED_REALTIME_WAKEUP、RTC、RTC_WAKEUP
        //ELAPSED_REALTIME 表示让定时任务的触发时间从系统开机开始算起，但不会唤醒 CPU
        //ELAPSED_REALTIME_WAKEUP 同样表示让定时任务的触发时间从系统开机开始算起，但会唤醒 CPU
        //RTC表示让定时任务的触发时间从 1970年 1 月 1日 0点开始算起，但不会唤醒 CPU
        //RTC_WAKEUP 同样表示让定时任务的触发时间从 1970年 1月 1日 0点开始算起，但会唤醒 CPU
        //SystemClock.elapsedRealtime()方法可以获取到系统开机至今所经历时间的毫秒数
        //System.currentTimeMillis()方法可以获取到1970年1月1日0点至今所经历时间的毫秒数。
        //alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pendingIntent);
        alarmManager.set(ELAPSED_REALTIME_WAKEUP, triggerAtTime, pendingIntent);
        return super.onStartCommand(intent, flags, startId);
    }
}
