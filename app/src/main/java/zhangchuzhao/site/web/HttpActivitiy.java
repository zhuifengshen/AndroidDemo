package zhangchuzhao.site.web;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import zhangchuzhao.site.demo.R;
import zhangchuzhao.site.skill.BaseActivity;
import zhangchuzhao.site.skill.HttpCallbackListener;
import zhangchuzhao.site.skill.LogUtil;
import zhangchuzhao.site.skill.Util;

import static zhangchuzhao.site.skill.Util.sendHttpRequest;

public class HttpActivitiy extends BaseActivity implements View.OnClickListener{

    public static final int SHOW_PRSPONSE = 0;
    public static final int JSON = 1;
    public static final int XML =2;
    private Button sendRequest;
    private Button sendRequest2;
    private Button sendRequest3;
    private Button sendRequest4;
    private TextView responseText;
    private Handler uiHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            switch (msg.what){
                case SHOW_PRSPONSE:
                    String response = (String)msg.obj;
                    responseText.setText(response);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        //使用StrictMode允许APP在主线程中进行请求网络操作
        if (Build.VERSION.SDK_INT > 9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        sendRequest = (Button)findViewById(R.id.button_request);
        sendRequest2 = (Button)findViewById(R.id.button_request2);
        sendRequest3 = (Button)findViewById(R.id.button_request3);
        sendRequest4 = (Button)findViewById(R.id.button_request4);
        responseText = (TextView)findViewById(R.id.textview_response);
        sendRequest.setOnClickListener(this);
        sendRequest2.setOnClickListener(this);
        sendRequest3.setOnClickListener(this);
        sendRequest4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_request:
                String url1 = "http://jenkins-bucket.oss-cn-shenzhen.aliyuncs.com/test/data.xml";
                sendRequestWithHttpUrlConnection(url1, XML);
                break;
            case R.id.button_request2:
                String url2 = "http://jenkins-bucket.oss-cn-shenzhen.aliyuncs.com/test/data.json";
                sendRequestWithHttpUrlConnection(url2, JSON);
                break;
            case R.id.button_request3:
                String url3 = "http://jenkins-bucket.oss-cn-shenzhen.aliyuncs.com/test/data.json";
                requestAppInfo(url3);
                break;
            case R.id.button_request4:
                String url4 = "http://jenkins-bucket.oss-cn-shenzhen.aliyuncs.com/test/data.json";
                requestAppInfo2(url4);
                break;
            default:
                break;
        }
    }

    private void requestAppInfo2(String url4) {
        String appJsonInfo = Util.sendHttpRequest(url4);
        responseText.setText(parseJsonWithJSONObject(appJsonInfo));
    }

    /**
     * 请求APP信息
     * @param url3 APP信息接口
     */
    private void requestAppInfo(String url3) {
        sendHttpRequest(url3, new HttpCallbackListener() {
            @Override
           public void onFinish(String response) {
                LogUtil.d(LOGTAG, response);
                String appInfo = parseJsonWithJSONObject(response);
                Message message = new Message();
                message.what = SHOW_PRSPONSE;
                message.obj = appInfo;
                uiHandler.sendMessage(message);
                Looper.prepare();
                Util.showToastMessage(HttpActivitiy.this, "网络请求成功");//java.lang.RuntimeException: Can't create handler inside thread that has not called Looper.prepare()
                //Util.showToastMessage(MyApplication.getContex(), "网络请求成功app");
                Looper.loop();
            }

            @Override
            public void onError(Exception e) {
                //Util.showToastMessage(MyApplication.getContex(), "网络请求异常，请稍后再试“");
                e.printStackTrace();
            }
        });
    }

    /**
     * 请求指定接口并根据其数据类型进行解析
     * @param urlStr 指定接口字符串
     * @param type 返回数据类型
     */
    private void sendRequestWithHttpUrlConnection(final String urlStr, final int type) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(urlStr);
                    //设置HttpURLConnection请求相关属性
                    connection = (HttpURLConnection)url.openConnection();
                    //connection.setRequestMethod("POST");
                    //DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                    //out.writeBytes("username=admin&password=123456");
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    //获取输入流进行读取响应数据
                    InputStream inputStream = connection.getInputStream();
                    LogUtil.i(LOGTAG, "响应状态码:" + connection.getResponseCode());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder reponseContent = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null){
                        reponseContent.append(line);
                    }
                    //读取响应内容后通知Handler界面更新
                    Message message = new Message();
                    message.what = SHOW_PRSPONSE;
                    String result = "";
                    if (type == XML){
                        parseXMLWithSAX(reponseContent.toString());//SAX解析
                        result = parseXMLWithPull(reponseContent.toString());//PULL解析

                    }else if (type == JSON){
                        parseJsonWithGSON(reponseContent.toString());
                        result = parseJsonWithJSONObject(reponseContent.toString());
                    }
                    message.obj = result;
                    uiHandler.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if (connection != null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    /**
     * GSON解析
     * @param jsonData Json字符串
     */
    private void parseJsonWithGSON(String jsonData){
        Gson gson = new Gson();
        List<App> appList = gson.fromJson(jsonData, new TypeToken<List<App>>(){}.getType());
        for (App app : appList) {
            LogUtil.d(LOGTAG, "id: " + app.getId());
            LogUtil.d(LOGTAG, "name: " + app.getName());
            LogUtil.d(LOGTAG, "version: " + app.getVersion());
        }
    }

    /**
     * Android内置JSON解析
     * @param jsonData Json字符串
     * @return
     */
    private String parseJsonWithJSONObject(String jsonData){
        StringBuilder result = new StringBuilder();
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String version = jsonObject.getString("version");
                result.append("id: " + id + " name: " + name + " version: " + version);
                result.append("\n");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    /**
     * SAX解析xml文件
     * @param xmlData xml字符串
     */
    private void parseXMLWithSAX(String xmlData) {

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            XMLReader xmlReader = factory.newSAXParser().getXMLReader();
            SaxXmlHandler handler = new SaxXmlHandler();
            xmlReader.setContentHandler(handler);
            xmlReader.parse(new InputSource(new StringReader(xmlData)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Pull解析xml字符串
     * @param xmlData xml字符串
     * @return
     */
    private String parseXMLWithPull(String xmlData){
        StringBuilder result = new StringBuilder();//解析结果
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));

            String id = "";
            String name = "";
            String version = "";

            int eventType = xmlPullParser.getEventType();//获取当前解析事件
            while (eventType != XmlPullParser.END_DOCUMENT){
                String nodeName = xmlPullParser.getName();//获取当前节点的名字
                switch(eventType){
                    case XmlPullParser.START_TAG:
                        if ("id".equals(nodeName)){
                            id = xmlPullParser.nextText();//获取节点内具体内容
                        }else if ("name".equals(nodeName)){
                            name = xmlPullParser.nextText();
                        }else if ("version".equals(nodeName)){
                            version = xmlPullParser.nextText();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("app".equals(nodeName)){
                            result.append("id: " + id + " name: " + name + " version: " + version);
                            result.append("\n");
                        }
                        break;
                    default:
                        break;
                }
                eventType = xmlPullParser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
