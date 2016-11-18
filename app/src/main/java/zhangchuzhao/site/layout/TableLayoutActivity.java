package zhangchuzhao.site.layout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import zhangchuzhao.site.demo.R;
import zhangchuzhao.site.skill.BaseActivity;
import zhangchuzhao.site.skill.ForceOfflineActivity;
import zhangchuzhao.site.skill.Util;

public class TableLayoutActivity extends BaseActivity {

    private EditText account;
    private EditText password;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_layout);

        account = (EditText)findViewById(R.id.account);
        password = (EditText)findViewById(R.id.password);
        login = (Button)findViewById(R.id.button_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (account.getText().toString().equals("123456") && password.getText().toString().equals("123456")){
                    Intent intent = new Intent(TableLayoutActivity.this, ForceOfflineActivity.class);
                    startActivity(intent);
                    finish();//登录成功，销毁当前活动
                    Log.d("forceoffline", "action");
                }else {
                    Util.showToastMessage(TableLayoutActivity.this, "account or password is invalid");
                }
            }
        });
    }
}
