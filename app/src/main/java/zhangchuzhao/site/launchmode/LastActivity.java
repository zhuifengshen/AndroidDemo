package zhangchuzhao.site.launchmode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import zhangchuzhao.site.demo.R;

public class LastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);
        Log.d("LastActivity", "Task id is: " + getTaskId());
    }
}
