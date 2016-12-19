package zhangchuzhao.site.provider;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import zhangchuzhao.site.demo.R;
import zhangchuzhao.site.skill.BaseActivity;
import zhangchuzhao.site.skill.LogUtil;

public class ContactActivity extends BaseActivity {

    private ListView contactListview;
    private ArrayAdapter<String> adapter;
    private List<String> contactList;// = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        contactListview = (ListView)findViewById(R.id.listview_contact);
        contactList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contactList);
        contactListview.setAdapter(adapter);
        readContacts();
    }

    private void readContacts(){
        Cursor cursor = null;
        try{
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
            while (cursor.moveToNext()){
                String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String contactPhone = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                contactList.add(contactName + "\n" + contactPhone);
                LogUtil.d(LOGTAG, contactName + ":" + contactPhone);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (cursor != null){
                cursor.close();
            }
        }
    }
}
