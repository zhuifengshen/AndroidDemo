package zhangchuzhao.site.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import zhangchuzhao.site.skill.Util;

/**
 * Created by Devin on 2016/11/17.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context mContext;

    public static final String CREATE_BOOK = "create table Book ("
            + "id integer primary key autoincrement,"
            + "author text,"
            + "pages integer,"
            + "price real,"
            + "name text,"
            + "category_id integer)";

    public static final String CREATE_CATEGORY = "create table Category ("
            + "id integer primary key autoincrement,"
            + "category_name text,"
            + "category_code integer)";

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        //直接安装第二步的用户两个表都创建
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATEGORY);
        Util.showToastMessage(mContext, "Table Create successful");
    }

    /**
     * 更新数据库
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion){
            case 1://第一版用户更新至第二步,直接建表category就可以了
                db.execSQL(CREATE_CATEGORY);
            case 2:
                db.execSQL("alter table Book add column category_id integer");
            default:
        }
        Util.showToastMessage(mContext, "db update successful");
    }

}
