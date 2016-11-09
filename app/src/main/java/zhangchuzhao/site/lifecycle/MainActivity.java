package zhangchuzhao.site.lifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import zhangchuzhao.site.demo.R;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        Button normalActivityButton = (Button)findViewById(R.id.start_normal_activity);
        normalActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NormalActivity.class);
                startActivity(intent);
            }
        });

        Button dialogActivityButton = (Button)findViewById(R.id.start_dialog_activity);
        dialogActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DialogActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG, "onStart()");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG, "onResume()");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG, "onPause()");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d(TAG, "onStop()");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d(TAG, "onRestart");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestory()");
    }
}
