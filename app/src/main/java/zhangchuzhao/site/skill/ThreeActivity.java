package zhangchuzhao.site.skill;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import zhangchuzhao.site.demo.R;

public class ThreeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        Button button = (Button)findViewById(R.id.button_three);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCollector.finishAll();
            }
        });

    }
}
