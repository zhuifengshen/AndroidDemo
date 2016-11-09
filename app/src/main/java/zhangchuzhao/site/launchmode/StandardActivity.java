package zhangchuzhao.site.launchmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import zhangchuzhao.site.demo.R;

public class StandardActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard);

        Log.d("StandardActivity", "onCreate()");
        Log.d("StandardActivtiy", "Task id is: " + getTaskId());

        Button button = (Button)findViewById(R.id.button_standard);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StandardActivity.this, OtherActivity.class));
            }
        });
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("StandardActivity", "onDestroy()");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d("StandardActivity", "onRestart()");
    }
}
