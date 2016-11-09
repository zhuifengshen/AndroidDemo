package zhangchuzhao.site.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        Toast.makeText(this, intent.getStringExtra("extra_data"), Toast.LENGTH_LONG).show();

        Button button2 = (Button)findViewById(R.id.button_2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(Intent.ACTION_VIEW);
                //intent.setData(Uri.parse("http://www.baidu.com"));
                //startActivity(intent);
                //startActivity(new Intent(SecondActivity.this, ThirdActivity.class));
                returnActivityResult();
            }
        });
    }

    @Override
    public void onBackPressed(){
        returnActivityResult();
    }

    private void returnActivityResult(){
        Intent intent = new Intent();
        intent.putExtra("data_return", "Hello FirstActivity");
        setResult(RESULT_OK, intent);
        finish();//本活动销毁后,回调上一个活动的onActivityForResult方法
    }
}
