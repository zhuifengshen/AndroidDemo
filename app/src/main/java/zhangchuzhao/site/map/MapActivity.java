package zhangchuzhao.site.map;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.TextView;
import java.util.List;
import zhangchuzhao.site.demo.R;
import zhangchuzhao.site.skill.BaseActivity;
import zhangchuzhao.site.skill.LogUtil;
import zhangchuzhao.site.skill.Util;

public class MapActivity extends BaseActivity {

    private TextView position;
    private LocationManager locationManager;
    private String provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        LogUtil.d(LOGTAG, "onCreate");
        position = (TextView) findViewById(R.id.textview_position);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //获取所有可用的位置提供器
        List<String> providerList = locationManager.getProviders(true);
        if (providerList.contains(LocationManager.GPS_PROVIDER)) {
            LogUtil.d(LOGTAG, "GPS");
            provider = LocationManager.GPS_PROVIDER;
        } else if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
            LogUtil.d(LOGTAG, "Network");
            provider = LocationManager.NETWORK_PROVIDER;
        } else {
            Util.showToastMessage(this, "No location provider to user");
            LogUtil.d(LOGTAG, "No location provider to user");
            return;
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Util.showToastMessage(this, "Permission deny");
            LogUtil.d(LOGTAG, "Permission deny");
            return;
        }
        Location location = locationManager.getLastKnownLocation(provider);
        if (location != null) {
            LogUtil.d(LOGTAG, "showLocation");
            showLocation(location);
        }else {
            LogUtil.d(LOGTAG, "location is null");//Why 。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。
        }
        locationManager.requestLocationUpdates(provider, 5000, 1, locationListener);
    }

    //位置状态监听器
    LocationListener locationListener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            showLocation(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    //显示当前经纬度
    private void showLocation(Location location) {
        String currentPosition = "Latitude is: " + location.getLatitude() + "\n" + "Longitude is: " + location.getLongitude();
        LogUtil.d(LOGTAG, currentPosition);
        position.setText(currentPosition);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (locationManager != null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                Util.showToastMessage(this, "Permission deny");
                return;
            }
            //关闭程序时将监听器移除
            locationManager.removeUpdates(locationListener);
        }
    }
}
