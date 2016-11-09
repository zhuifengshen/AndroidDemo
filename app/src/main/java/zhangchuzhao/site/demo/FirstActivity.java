package zhangchuzhao.site.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_first);

        //获取活动由于内存不足被销毁时保存的数据
        if (savedInstanceState != null){
            String tempData = savedInstanceState.getString("data_key");
        }

        Button button1 = (Button)findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vew) {
                //Toast.makeText(FirstActivity.this, "Start", Toast.LENGTH_LONG).show();
                //finish();
                //Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                //startActivity(intent);
                String data = "Hello SecondActivity.";
                Intent intent = new Intent("zhangchuzhao.site.demo.ACTION_START");
                intent.putExtra("extra_data", data);
                intent.addCategory("zhangchuzhao.site.demo.MY_CATEGORY");
                //startActivity(intent);
                startActivityForResult(intent,1);
            }
        });
    }

    /**
     * 在onStop方法之前调用, 用于保存临时数据,防止由于内存不足活动被销毁导致临时数据丢失
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        String tempData = "Something you just typed";
        outState.putString("data_key", tempData);
    }
    /**
     * 上一个活动销毁后,回调本方法返回处理结果
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case 1:
                if (resultCode == RESULT_OK){
                    Toast.makeText(this, data.getStringExtra("data_return"), Toast.LENGTH_LONG).show();
                }else if (requestCode == RESULT_CANCELED){
                    Toast.makeText(this, "结果处理失败", Toast.LENGTH_LONG).show();
                }
                break;
            default: Toast.makeText(this, "不存在该请求码", Toast.LENGTH_LONG).show();
        }
    }
    /**
     * 创建菜单
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * 响应菜单操作
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.add_item:
                Toast.makeText(this, "You Clicked Add", Toast.LENGTH_LONG).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this, "You Clicked Remove", Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }
}
