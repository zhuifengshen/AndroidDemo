package zhangchuzhao.site.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import zhangchuzhao.site.demo.R;
import zhangchuzhao.site.skill.BaseActivity;
import zhangchuzhao.site.skill.LogUtil;
import zhangchuzhao.site.skill.Util;

public class DatabaseActivity extends BaseActivity implements View.OnClickListener{

    public final static String FILENAME = "userData";
    public final static String DBNAME = "BookStore.db";
    private String userData;
    private EditText userName;
    private Button saveData;
    private Button readData;
    private Button createDatabase;
    private Button addData;
    private Button updateData;
    private Button deleteData;
    private Button queryData;
    private Button replaceData;
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

        dbHelper = new MyDatabaseHelper(this, DBNAME, null, 2);

        createDatabase = (Button)findViewById(R.id.button_create_database);
        createDatabase.setOnClickListener(this);
        addData = (Button)findViewById(R.id.button_add_data);
        addData.setOnClickListener(this);
        updateData = (Button)findViewById(R.id.button_update_data);
        updateData.setOnClickListener(this);
        deleteData = (Button)findViewById(R.id.button_delete_data);
        deleteData.setOnClickListener(this);
        queryData = (Button)findViewById(R.id.button_query_data);
        queryData.setOnClickListener(this);
        replaceData = (Button)findViewById(R.id.button_replace_data);
        replaceData.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
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
                String myname = sharedPreferences.getString("name", "");
                int age = sharedPreferences.getInt("age", 0);
                boolean married = sharedPreferences.getBoolean("married", false);
                Util.showToastMessage(this, "name:" + myname + " age:" + age + " married:" + married);
                break;
            case R.id.button_create_database:
                dbHelper = new MyDatabaseHelper(this, DBNAME, null, 3);
                dbHelper.getWritableDatabase();
                break;
            case R.id.button_add_data:
                //db = dbHelper.getWritableDatabase();
                //values.put("name", "第一行代码");
                //values.put("author", "郭霖");
                //values.put("pages", 557);
                //values.put("price", 79.00);
                //db.insert("Book", null, values);
                //values.clear();
                //values.put("name", "第二行代码");
                //values.put("author", "DevinZhang");
                //values.put("pages", 579);
                //values.put("price", 79.00);
                //db.insert("Book", null, values);
                //values.clear();
                db.execSQL("insert into Book(name, author, pages, price) values(?, ?, ?, ?)", new String[]{"The Da Vinci Code", "Dan Brown", "454", "16.96"});
                db.execSQL("insert into Book(name, author, pages, price) values(?, ?, ?, ?)", new String[]{"The Lost Symbol", "Dan Brown", "510", "19.95"});
                Util.showToastMessage(this, "Data insert successful");
                break;
            case R.id.button_update_data:
                db.execSQL("update Book set price=? where name=?", new String[]{"10.99", "The Da Vinci Code"});
                //values.put("price", 59.99);
                //db.update("Book", values, "name=?", new String[]{"第一行代码"});
                //values.clear();
                Util.showToastMessage(this, "Data update successful");
                break;
            case R.id.button_delete_data:
                db.execSQL("delete from Book where pages<?", new String[]{"500"});
                //db.delete("Book", "pages>?", new String[]{"560"});
                Util.showToastMessage(this, "Data delete successful");
                break;
            case R.id.button_query_data:
                Cursor cursor = db.rawQuery("select * from Book", null);
                //Cursor cursor = db.query("Book", null, null, null, null, null, null);
                if (cursor.moveToFirst()){
                    do{
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        LogUtil.d(LOGTAG, "name: " + name);
                        LogUtil.d(LOGTAG, "author: " + author);
                        LogUtil.d(LOGTAG, "pages: " + pages);
                        LogUtil.d(LOGTAG, "price: " + price);
                    }while (cursor.moveToNext());
                }
                cursor.close();
                break;
            case R.id.button_replace_data:
                db.beginTransaction();
                try{
                    db.delete("Book", null, null);
                    if (true){
                        throw new NullPointerException("数据库空指针错误");
                    }
                    values.put("name", "Game of Thrones");
                    values.put("author", "George Martin");
                    values.put("pages", 720);
                    values.put("price", 20.00);
                    db.insert("Book", null, values);
                    values.clear();
                    db.setTransactionSuccessful();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    db.endTransaction();
                }
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
