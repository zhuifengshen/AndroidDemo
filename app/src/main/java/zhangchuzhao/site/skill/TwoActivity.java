package zhangchuzhao.site.skill;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import zhangchuzhao.site.demo.R;

public class TwoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        Button button = (Button)findViewById(R.id.button_two);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TwoActivity.this, ThreeActivity.class));
            }
        });
    }

    /**
     * 定义启动本活动需要传递的数据,方便与其他开发协作
     * @param context 调用者自身上下文
     * @param data1 参数param1的数据内容
     * @param data2 参数param2的数据内容
     */
    public static void startActivity(Context context, String data1, String data2){
        Intent intent = new Intent(context, ThreeActivity.class);
        intent.putExtra("param1", data1);
        intent.putExtra("param2", data2);
        context.startActivity(intent);
    }
}
