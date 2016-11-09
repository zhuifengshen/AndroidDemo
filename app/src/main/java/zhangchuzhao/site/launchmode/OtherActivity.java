package zhangchuzhao.site.launchmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import zhangchuzhao.site.demo.R;

public class OtherActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        Log.d("OtherActivity", "Task id is: " + getTaskId());

        Button button = (Button)findViewById(R.id.button_other);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OtherActivity.this, LastActivity.class));
            }
        });
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("OtherActivity", "onDestroy()");
    }

}
