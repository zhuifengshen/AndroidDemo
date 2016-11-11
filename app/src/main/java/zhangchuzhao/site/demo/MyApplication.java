package zhangchuzhao.site.demo;

import android.app.Application;
import android.util.Log;

/**
 * Created by Devin on 2016/11/10.
 */

public class MyApplication extends Application {
    //public MyApplication(){
    //    String packageName = getPackageName();//上下文环境未初始化，报错
    //    Log.d("context", "package name is: " + packageName);
    //}

    private static MyApplication app;

    @Override
    public void onCreate(){
        super.onCreate();
        app = this; //直接通过this赋值当前Application实例

        String packageName = getPackageName();//已经完成上下文环境初始化，不会报错
        Log.d("context", "package name is: " + packageName);
    }

    public static MyApplication getInstance(){
        return app;
    }
}
