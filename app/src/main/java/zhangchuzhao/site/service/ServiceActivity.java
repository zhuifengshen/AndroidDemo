package zhangchuzhao.site.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import zhangchuzhao.site.demo.R;

public class ServiceActivity extends Activity implements View.OnClickListener{

    private Button startService;
    private Button stopService;
    private Button bindService;
    private Button unbindService;
    private Button intentService;
    private Button startAlarm;
    private MyService.DownloadBinder downloadBinder;
    //创建ServiceConnection实例
    private ServiceConnection serviceConnection = new ServiceConnection() {
        //服务与活动绑定时调用
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (MyService.DownloadBinder)service;//获得在MyService中自定义的DownloadBinder实例
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }
        //服务与活动解绑时调用
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("service", "on Service Disconnected");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        startService = (Button)findViewById(R.id.button_start_service);
        stopService = (Button)findViewById(R.id.button_stop_service);
        bindService = (Button)findViewById(R.id.button_bind_service);
        unbindService = (Button)findViewById(R.id.button_unbind_service);
        intentService = (Button)findViewById(R.id.button_intentservice);
        startAlarm = (Button)findViewById(R.id.button_alarm);
        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);
        bindService.setOnClickListener(this);
        unbindService.setOnClickListener(this);
        intentService.setOnClickListener(this);
        startAlarm.setOnClickListener(this);

    }

    @Override
    public void onClick(View view){
        Intent intent = new Intent(this, MyService.class);
        switch(view.getId()){
            case R.id.button_start_service:
                startService(intent);//开始服务，第一次执行onCreate()方法和onStartCommand()方法，之后只执行onStartCommand()方法
                break;
            case R.id.button_stop_service:
                stopService(intent);//停止服务
                break;
            case R.id.button_bind_service:
                bindService(intent, serviceConnection, BIND_AUTO_CREATE);//绑定服务，BIND_AUTO_CREATE表示在活动和服务进行绑定后自动创建服务。这使得MyService中的onCreate()方法得到执行，但onStartCommand()方法不会执行
                break;
            case R.id.button_unbind_service:
                unbindService(serviceConnection);//解绑服务
                break;
            case R.id.button_intentservice:
                Log.d("service", "ServiceActivity Thread id is: " + Thread.currentThread().getId());
                Intent myIntentService = new Intent(this, MyIntentService.class);
                startService(myIntentService);
                break;
            case R.id.button_alarm:
                Log.d("service", "start alarm");
                Intent startAlarmService = new Intent(this, LongRunningService.class);
                startService(startAlarmService);
            default:
                break;
        }
    }
}
