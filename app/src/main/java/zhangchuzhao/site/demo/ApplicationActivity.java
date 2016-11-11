package zhangchuzhao.site.demo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

public class ApplicationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);

        MyApplication myApp = (MyApplication)getApplication();//Application
        Log.d("context", "application: " + myApp);
        Context appContext = getApplicationContext();//Application
        Log.d("context", "application context: " + appContext);
        Context baseContext = getBaseContext();//ContextImpl
        Log.d("context", "base context: " + baseContext);
        //Context context = getContext();
    }
}
