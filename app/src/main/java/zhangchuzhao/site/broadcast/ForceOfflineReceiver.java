package zhangchuzhao.site.broadcast;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.WindowManager;

import zhangchuzhao.site.layout.TableLayoutActivity;
import zhangchuzhao.site.skill.ActivityCollector;

/**
 * Created by Devin on 2016/11/14.
 */

public class ForceOfflineReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, Intent intent) {
        //收到强制下线广播，弹出对话框强制用户下线
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setTitle("Warning");
        dialogBuilder.setMessage("You are forced to be offline, for your account security, please try to login again");
        dialogBuilder.setCancelable(false);
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //销毁所有活动
                ActivityCollector.finishAll();
                Intent intent = new Intent(context, TableLayoutActivity.class);
                //Intent中加入FLAG_ACTIVITY_NEW_TASK标志，才能在广播接收器中启动
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                Log.d("forceoffline", "yes");
            }
        });
        AlertDialog alertDialog = dialogBuilder.create();
        //设置AlertDialog类型为TYPE_SYSTEM_ALERT，这样才能在广播接收器里弹出
        alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        alertDialog.show();
    }
}
