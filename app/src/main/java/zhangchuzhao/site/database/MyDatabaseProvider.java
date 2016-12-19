package zhangchuzhao.site.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import zhangchuzhao.site.skill.LogUtil;

import static zhangchuzhao.site.skill.BaseActivity.LOGTAG;

public class MyDatabaseProvider extends ContentProvider {

    private final static int BOOK_DIR = 0;
    private final static int BOOK_ITEM = 1;
    private final static int CATEGORY_DIR = 2;
    private final static int CATEGORY_ITEM = 3;
    private final static String AUTHORITY = "zhangchuzhao.site.database.provider";

    private MyDatabaseHelper dbHelper;
    private static UriMatcher uriMatcher;

    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        //添加URI:权限、路径（区分大小写,而数据库表名称不区分大小写）、自定义代码
        uriMatcher.addURI(AUTHORITY, "book", BOOK_DIR);
        uriMatcher.addURI(AUTHORITY, "book/#", BOOK_ITEM);
        uriMatcher.addURI(AUTHORITY, "category", CATEGORY_DIR);
        uriMatcher.addURI(AUTHORITY, "category/#", CATEGORY_ITEM);
    }

    public MyDatabaseProvider() {
    }

    @Override
    public boolean onCreate(){
        dbHelper = new MyDatabaseHelper(getContext(), "BookStore.db", null, 3);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        switch(uriMatcher.match(uri)){
            case BOOK_DIR:
                cursor = db.query("Book", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                cursor = db.query("Book", projection, "id=?", new String[]{bookId}, null, null, sortOrder);
                break;
            case CATEGORY_DIR:
                cursor = db.query("Category", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                cursor = db.query("Category", projection, "id=?", new String[]{categoryId}, null, null, sortOrder);
                break;
            default:
                LogUtil.i(LOGTAG, "query uriMatcher 匹配路径:" + uri.getPath() + "不存在");
                break;
        }
        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri uriReturn = null;
        switch(uriMatcher.match(uri)){
            case BOOK_DIR:
            case BOOK_ITEM:
                long newBookId = db.insert("Book", null, values);
                uriReturn = Uri.parse("content://" + AUTHORITY + "/book/" + newBookId);
                break;
            case CATEGORY_DIR:
            case CATEGORY_ITEM:
                long newCategoryId = db.insert("Category", null, values);
                uriReturn = Uri.parse("content://" + AUTHORITY + "/category/" + newCategoryId);
                break;
            default:
                LogUtil.i(LOGTAG, "insert uriMatcher 匹配路径:" + uri.getPath() + "不存在");
                break;
        }
        return uriReturn;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int updateRows = 0;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                updateRows = db.update("Book", values, selection, selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                updateRows = db.update("Book", values, "id=?", new String[]{bookId});
                break;
            case CATEGORY_DIR:
                updateRows = db.update("Category", values, selection, selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                updateRows = db.update("Category", values, "id=?", new String[]{categoryId});
                break;
            default:
                LogUtil.i(LOGTAG, "update uriMatcher 匹配路径:" + uri.getPath() + "不存在");
                break;

        }
        return updateRows;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int deleteRows = 0;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                deleteRows = db.delete("Book", selection, selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                deleteRows = db.delete("Book", "id=?", new String[]{bookId});
                break;
            case CATEGORY_DIR:
                deleteRows = db.delete("Category", selection, selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                deleteRows = db.delete("Category", "id=?", new String[]{categoryId});
                break;
            default:
                LogUtil.i(LOGTAG, "delete uriMatcher 匹配路径:" + uri.getPath() + "不存在");
                break;
        }
        return deleteRows;
    }

    @Override
    public String getType(Uri uri){
        String dir = "vnd.android.cursor.dir/vnd.";
        String item = "vnd.android.cursor.item/vnd.";
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                return dir + AUTHORITY + ".book";
            case BOOK_ITEM:
                return item + AUTHORITY + ".book";
            case CATEGORY_DIR:
                return dir + AUTHORITY + ".category";
            case CATEGORY_ITEM:
                return item + AUTHORITY + ".category";
        }
        return null;
    }
}
