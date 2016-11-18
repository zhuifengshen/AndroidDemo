package zhangchuzhao.site.database;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import zhangchuzhao.site.demo.R;
import zhangchuzhao.site.skill.Util;

public class DatabaseActivity extends Activity implements View.OnClickListener{

    public final static String FILENAME = "userData";
    public final static String DBNAME = "BookStore.db";
    private String userData;
    private EditText userName;
    private Button saveData;
    private Button readData;
    private Button createDatabase;
    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        //如果文件不为空,自动加载填充数据
        userName = (EditText)findViewById(R.id.edittext_data);
        userData = Util.loadFileData(this, FILENAME);
        if (!TextUtils.isEmpty(userData)){
        //if (userData != null){
            userName.setText(userData);
            userName.setSelection(userData.length());
            Util.showToastMessage(this, "data restored successful");
        }

        saveData = (Button)findViewById(R.id.button_save);
        saveData.setOnClickListener(this);
        readData = (Button)findViewById(R.id.button_read);
        readData.setOnClickListener(this);
        createDatabase = (Button)findViewById(R.id.button_create_database);
        createDatabase.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_save:
                //getSharePreferences存储数据,默认存储路径为/data/data/zhangchuzhao.site.demo/shared_prefs
                SharedPreferences.Editor editor = getSharedPreferences("sharedPreferencesData", MODE_PRIVATE).edit();
                editor.putString("name", "Devin");
                editor.putInt("age", 22);
                editor.putBoolean("married", false);
                editor.commit();
                Util.showToastMessage(this, "Data save successfully");
                break;
            case R.id.button_read:
                SharedPreferences sharedPreferences = getSharedPreferences("sharedPreferencesData", MODE_PRIVATE);
                String name = sharedPreferences.getString("name", "");
                int age = sharedPreferences.getInt("age", 0);
                boolean married = sharedPreferences.getBoolean("married", false);
                Util.showToastMessage(this, "name:" + name + " age:" + age + " married:" + married);
                break;
            case R.id.button_create_database:
                dbHelper = new MyDatabaseHelper(this, DBNAME, null, 1);
                dbHelper.getWritableDatabase();
                break;

            default:
                break;
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        //在退出之前保存数据
        userData = userName.getText().toString();
        //if (userData != null && userData != ""){
        Util.saveFileData(this, FILENAME, userData, Context.MODE_PRIVATE);
    }
}
