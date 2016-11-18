package zhangchuzhao.site.thread;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import zhangchuzhao.site.demo.R;

public class ProgressActivity extends Activity {

    private ProgressBar progressBar;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        progressBar = (ProgressBar)findViewById(R.id.progressbar);
        button = (Button)findViewById(R.id.button_progress);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DownloadTask(ProgressActivity.this, progressBar).execute(); //启动异步任务
            }
        });
    }
}
