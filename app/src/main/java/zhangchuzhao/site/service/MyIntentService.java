package zhangchuzhao.site.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * 集开启线程和完成后自动停止功能
 */
public class MyIntentService extends IntentService {
    //提供无参构造函数
    public MyIntentService() {
        super("MyIntentService");
    }
    //处理具体的逻辑,在子线程中运行
    @Override
    public void onHandleIntent(Intent intent){
        Log.d("service", "Thread id is: " + Thread.currentThread().getId());
    }

    @Override
    public void onDestroy(){
        Log.d("service", "MyIntentService onDestroy executed");
    }

}
