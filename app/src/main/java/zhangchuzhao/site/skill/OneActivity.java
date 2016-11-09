package zhangchuzhao.site.skill;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import zhangchuzhao.site.demo.R;

public class OneActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        Button button = (Button)findViewById(R.id.button_one);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(OneActivity.this, TwoActivity.class));
                TwoActivity.startActivity(OneActivity.this, "data1", "data2");
            }
        });
    }
}
