package zhangchuzhao.site.lifecycle;

import android.app.Activity;
import android.os.Bundle;

import zhangchuzhao.site.demo.R;

public class NormalActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
    }
}
