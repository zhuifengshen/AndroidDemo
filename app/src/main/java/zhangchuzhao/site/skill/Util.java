package zhangchuzhao.site.skill;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

/**
 * Created by Devin on 2016/11/9.
 */

public class Util {
    /**
     * Toast 提示信息
     * @param context 调用者上下文环境
     * @param message 提示信息
     */
    public static void showToastMessage(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * AletDialog 提示信息
     * @param context 调用者上下文环境
     * @param message 信息内容
     */
    public static void showAlerDialog(final Context context, final String message){
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle("This is alert dialog");
        dialog.setMessage("Something Important.");
        dialog.setCancelable(false);//设置为false，则用户无法通过Back键取消
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(UIActivity.this, "Click OK", Toast.LENGTH_LONG).show();
                showToastMessage(context, message);
            }
        });
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(UIActivity.this, "Click OK", Toast.LENGTH_LONG).show();
                showToastMessage(context, "You Click Cancel Button");
            }
        });
        dialog.show();
    }

    /**
     * ProgressDialog 显示进度信息，表示当前操作比较耗时，请用户耐心等待
     * @param context 调用者上下文环境
     */
    public static void showProgressDialog(Context context){
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("This is ProgressDialog");
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(true);
        progressDialog.show();
    }
}
