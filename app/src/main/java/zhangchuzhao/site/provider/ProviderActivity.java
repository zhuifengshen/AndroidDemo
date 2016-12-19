package zhangchuzhao.site.provider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import zhangchuzhao.site.demo.R;
import zhangchuzhao.site.skill.BaseActivity;
import zhangchuzhao.site.skill.LogUtil;

public class ProviderActivity extends BaseActivity implements View.OnClickListener{

    private Button insertData;
    private Button updateData;
    private Button deleteData;
    private Button queryData;
    private String newId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);
        insertData = (Button)findViewById(R.id.insert_data);
        updateData = (Button)findViewById(R.id.update_data);
        deleteData = (Button)findViewById(R.id.delete_data);
        queryData = (Button)findViewById(R.id.query_data);
        insertData.setOnClickListener(this);
        updateData.setOnClickListener(this);
        deleteData.setOnClickListener(this);
        queryData.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String baseUri = "content://zhangchuzhao.site.database.provider/book";
        switch (view.getId()){
            case R.id.insert_data:
                ContentValues values = new ContentValues();
                values.put("name", "A Clash of Kings");
                values.put("author", "George Martin");
                values.put("pages", 1040);
                values.put("price", 22.22);
                values.put("category_id", 11);
                Uri newUri = getContentResolver().insert(Uri.parse(baseUri), values);
                LogUtil.d(LOGTAG, "insert result: " + newUri);
                if (newUri != null){
                    newId = newUri.getPathSegments().get(1);
                }

                break;

            case R.id.update_data:
                ContentValues updateValues = new ContentValues();
                updateValues.put("name", "A Storm of Swords");
                updateValues.put("pages", 1111);
                int updateResult = getContentResolver().update(Uri.parse(baseUri+ "/" + newId), updateValues, null, null);
                LogUtil.d(LOGTAG, "update result: " + updateResult);
                break;

            case R.id.delete_data:
                int deleteResult = getContentResolver().delete(Uri.parse(baseUri + "/" + newId), null, null);
                LogUtil.d(LOGTAG, "delete result:" + deleteResult);
                break;

            case R.id.query_data:
                Cursor cursor = getContentResolver().query(Uri.parse(baseUri), null, null, null, null);
                LogUtil.d(LOGTAG, "insert result: cursor=" + cursor);
                if (cursor != null){
                    while (cursor.moveToNext()){
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        int category_id = cursor.getInt(cursor.getColumnIndex("category_id"));
                        Log.d("provider", "书名:" + name);
                        Log.d("provider", "作者:" + author);
                        Log.d("provider", "页数:" + pages);
                        Log.d("provider", "类别:" + category_id);
                    }
                    cursor.close();
                }
                break;

            default:
                break;
        }
    }
}
