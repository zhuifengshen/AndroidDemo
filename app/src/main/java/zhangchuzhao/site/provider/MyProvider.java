package zhangchuzhao.site.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

public class MyProvider extends ContentProvider {

    public static final int TABLE1_DIR = 0;
    public static final int TABLE1_ITEM = 1;
    public static final int TABLE2_DIR = 2;
    public static final int TABLE2_ITEM = 3;
    private static UriMatcher uriMatcher;

    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("zhangchuzhao.site.provider", "table1", TABLE1_DIR);
        uriMatcher.addURI("zhangchuzhao.site.provider", "table1/#", TABLE1_ITEM);
        uriMatcher.addURI("zhangchuzhao.site.provider", "table2", TABLE2_DIR);
        uriMatcher.addURI("zhangchuzhao.site.provider", "table2/#", TABLE2_ITEM);
    }

    public MyProvider() {
    }

    @Override
    public boolean onCreate(){
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder){
        switch (uriMatcher.match(uri)){
            case TABLE1_DIR:
                break;
            case TABLE1_ITEM:
                break;
            case TABLE2_DIR:
                break;
            case TABLE2_ITEM:
                break;
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values){
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs){
        return 0;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs){
        return 0;
    }

    @Override
    public String getType(Uri uri){
        String dir = "vnd.android.cursor.dir/vnd.";
        String item = "vnd.android.cursor.item/vnd.";
        String authority = "zhangchuzhao.site.provider.";
        switch (uriMatcher.match(uri)){
            case TABLE1_DIR:
                return dir + authority + "table1";
            case TABLE1_ITEM:
                return item + authority + "table1";
            case TABLE2_DIR:
                return dir + authority + "table2";
            case TABLE2_ITEM:
                return item + authority + "table2";
            default:
                break;
        }
        return null;
    }
}
