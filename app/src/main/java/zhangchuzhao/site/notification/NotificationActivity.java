package zhangchuzhao.site.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import zhangchuzhao.site.demo.R;

public class NotificationActivity extends Activity implements View.OnClickListener{

    private Button sendNotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        sendNotice = (Button)findViewById(R.id.button_notification);
        sendNotice.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        final int NOTIFICATION_ID = 1;
        switch (view.getId()){
            case R.id.button_notification:
                Intent intent = new Intent(this, WishesActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                Notification.Builder notificationBuilder = new Notification.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setTicker("This is a greeting from Devin.") //状态栏一闪而过的提示消息
                        .setContentTitle("Devin's Greeting") //下拉系统状态栏，通知的标题
                        .setContentText("Wish all the best wishes for you!!!") //下拉系统状态栏，通知的正文
                        .setContentIntent(pendingIntent)//相应通知的点击跳转事件
                        .setAutoCancel(true) //点击通知时自动清除
                        .setWhen(System.currentTimeMillis());//默认显示时间
                Notification notification = notificationBuilder.build();//获取Notification实例
                notificationManager.notify(NOTIFICATION_ID, notification);//显示通知
                break;
            default:
                break;
        }
    }
}
