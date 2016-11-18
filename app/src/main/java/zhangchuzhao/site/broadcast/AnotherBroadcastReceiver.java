package zhangchuzhao.site.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import zhangchuzhao.site.skill.Util;

/**
 * Created by Devin on 2016/11/12.
 */

public class AnotherBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Util.showToastMessage(context, "receiver in AnotherBroadcastReceiver");
        abortBroadcast();//截断广播
    }
}
