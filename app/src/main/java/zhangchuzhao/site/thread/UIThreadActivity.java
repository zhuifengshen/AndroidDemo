package zhangchuzhao.site.thread;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import zhangchuzhao.site.demo.R;

public class UIThreadActivity extends Activity {


    public final static int UPDATE_TEXT = 1;
    private TextView textView;
    private Button button;
    private Handler uiHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case UPDATE_TEXT:
                    textView.setText("Nice to meet you");
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uithread);

        textView = (TextView)findViewById(R.id.textview_ui);
        button = (Button)findViewById(R.id.button_change_text);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //textView.setText("Nice to meet you");

                //android.view.ViewRootImpl$CalledFromWrongThreadException: Only the original thread that created a view hierarchy can touch its views.
                //new Thread(new Runnable() {
                //    @Override
                //    public void run() {
                //        textView.setText("Nice to meet you");
                //    }
                //}).start();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = UPDATE_TEXT;
                        uiHandler.sendMessage(message);
                    }
                }).start();
            }
        });
    }
}
