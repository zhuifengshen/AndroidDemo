package zhangchuzhao.site.skill;

import android.app.Activity;
import android.os.Bundle;

public class BaseActivity extends Activity {

    public static String LOGTAG;//当前Activity类名，用于输出Log时使用

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LOGTAG = getClass().getSimpleName();//初始化当前Activity类名
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
