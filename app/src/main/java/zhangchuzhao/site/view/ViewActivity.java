package zhangchuzhao.site.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import zhangchuzhao.site.demo.R;
import zhangchuzhao.site.skill.Util;

public class ViewActivity extends Activity {

    private LinearLayout viewLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        viewLayout = (LinearLayout)findViewById(R.id.activity_view);
        Log.d("parent", "嵌套外层类：" + viewLayout.getParent());

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View buttonLayout = layoutInflater.inflate(R.layout.button_layout, null);
        viewLayout.addView(buttonLayout);

        Button button = (Button)findViewById(R.id.button_view);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.showToastMessage(ViewActivity.this, "inflate button view");
            }
        });
    }
}
