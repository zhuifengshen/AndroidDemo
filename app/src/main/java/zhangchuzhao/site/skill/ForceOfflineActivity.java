package zhangchuzhao.site.skill;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import zhangchuzhao.site.demo.R;

public class ForceOfflineActivity extends Activity {

    //private final static int MY_PERMISSION_SYSTEM_ALERT_WINDOW = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_force_offline);

        Button forceOffline = (Button)findViewById(R.id.button_force_offline);
        forceOffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checkSystemAlertWindowPermission();
                sendForceOfflineBroadcast();
            }
        });
    }

    //public void checkSystemAlertWindowPermission(){
    //    if (ContextCompat.checkSelfPermission(this, Manifest.permission.SYSTEM_ALERT_WINDOW) != PackageManager.PERMISSION_GRANTED){
    //        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SYSTEM_ALERT_WINDOW}, MY_PERMISSION_SYSTEM_ALERT_WINDOW);
    //        return;
    //    }else {
    //        sendForceOfflineBroadcast();
    //    }
    //}

    public void sendForceOfflineBroadcast(){
        Intent intent = new Intent("site.zhangchuzhao.broadcast.FORCEOFFLINE");
        sendBroadcast(intent);
    }

    //@Override
    //public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
    //    switch (requestCode){
    //        case MY_PERMISSION_SYSTEM_ALERT_WINDOW:
    //            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
    //                sendForceOfflineBroadcast();
    //            }else {
    //                Util.showToastMessage(ForceOfflineActivity.this, "System Alert Window Permission Denied");
    //                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SYSTEM_ALERT_WINDOW}, MY_PERMISSION_SYSTEM_ALERT_WINDOW);
    //                return;
    //            }
    //            break;
    //        default:
    //            Util.showToastMessage(ForceOfflineActivity.this, "无对应权限返回码");
    //    }
    //}
}
