package zhangchuzhao.site.skill;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;

import zhangchuzhao.site.demo.MyApplication;
import zhangchuzhao.site.demo.R;

import static zhangchuzhao.site.skill.BaseActivity.LOGTAG;


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

    /**
     * 通过反射资源类获取其资源ID
     * @param contex 调用者上下文环境
     * @param resourceName 资源名
     * @return
     */
    public static int getResourceIdByResourceName(Context contex, String resourceName){
        int resourceId = 0;
        try {
            Field field = R.drawable.class.getField(resourceName);
            field.setAccessible(true);
            try {
                resourceId = field.getInt(null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return resourceId;
    }

    /**
     * 通过Resources类的getIdentifier方法获取指定资源名的ID
     * @param context 调用者上下文环境
     * @param name 资源名
     * @param type 资源类型
     * @param packageName 应用包名
     * @return 资源ID
     */
    public static int getResourceIdByResourceName(Context context, String name, String type, String packageName){
        return context.getResources().getIdentifier(name, type, packageName);
    }

    /**
     * 文件存储数据
     * @param context 调用者上下文环境
     * @param fileName 文件名
     * @param data 字符串数据
     * @param mode 存储模式
     */
    public static void saveFileData(Context context, String fileName, String data, int mode){
        FileOutputStream fileOutputStream = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileOutputStream = context.openFileOutput(fileName, mode);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            bufferedWriter.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (bufferedWriter != null){
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 加载指定文件中的数据
     * @param context 调用者的上下文环境
     * @param fileName 文件名
     * @return
     */
    public static String loadFileData(Context context, String fileName){
        FileInputStream fileInputStream = null;
        BufferedReader bufferedReader = null;
        StringBuilder content = new StringBuilder();
        try {
            fileInputStream = context.openFileInput(fileName);
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line = "";
            while ((line = bufferedReader.readLine()) != null){
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }

    /**
     * 判断当前网络是否正常
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null ? networkInfo.isAvailable() : false;
    }

    /**
     * 请求接口并返回结果
     * @param address 请求地址
     * @return
     */
    public static String sendHttpRequest(String address){
        HttpURLConnection connection = null;
        StringBuilder response = new StringBuilder();
        try {
            URL url = new URL(address);
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);
            //URL 连接可用于输入和/或输出。如果打算使用 URL 连接进行输入，则将 DoInput 标志设置为 true；如果不打算使用，则设置为 false。默认值为 true。
            //因为总是使用conn.getInputStream()获取服务端的响应，因此默认值是true。
            connection.setDoInput(true);
            //URL 连接可用于输入和/或输出。如果打算使用 URL 连接进行输出，则将 DoOutput 标志设置为 true；如果不打算使用，则设置为 false。默认值为 false。
            //get请求,用不到conn.getOutputStream()，因为参数直接追加在地址后面，因此默认是false。
            //而post请求需要往服务区传输大量的数据，这些数据是放在http的body里面的，因此需要在建立连接以后，往服务端写数据,所以如果是post请求,使用connection.setDoOutput(true)。
            connection.setDoOutput(false);
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null){
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (connection != null){
                connection.disconnect();
            }
        }
        return response.toString();
    }

    /**
     * 避免主线程阻塞，新建子线程请求接口
     * @param address
     * @param listener
     */
    public static void sendHttpRequest(final String address, final HttpCallbackListener listener){
        if (!isNetworkAvailable(MyApplication.getContex())){
            showToastMessage(MyApplication.getContex(), "network is unavailable");
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                StringBuilder result = new StringBuilder();
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream inputStream = connection.getInputStream();
                    LogUtil.i(LOGTAG, "响应状态码:" + connection.getResponseCode());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null){
                        result.append(line);
                    }
                    if (listener != null){
                        listener.onFinish(result.toString());
                    }
                } catch (IOException e) {
                    if (listener != null){
                        listener.onError(e);
                    }
                }finally {
                    if (connection != null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

}
