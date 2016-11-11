package zhangchuzhao.site.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import zhangchuzhao.site.demo.R;

public class ChatActivity extends Activity {
    private ListView msgListView;
    private EditText inputText;
    private Button sendButton;
    private MsgAdapter msgAdapter;
    private List<Msg> msgList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        msgList = new ArrayList<>();
        initMsgs();
        msgListView = (ListView)findViewById(R.id.msg_list_view);
        msgAdapter = new MsgAdapter(ChatActivity.this, R.layout.msg_item, msgList);
        msgListView.setAdapter(msgAdapter);

        inputText = (EditText)findViewById(R.id.input_text);
        sendButton = (Button)findViewById(R.id.send);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = (String)inputText.getText().toString();
                if (content != ""){
                    msgList.add(new Msg(content, Msg.TYPE_SEND));
                    //当有新消息时，刷新ListView中的显示
                    msgAdapter.notifyDataSetChanged();
                    //将ListView定位到最后一行
                    msgListView.setSelection(msgList.size());
                    //清空输入框中的内容
                    inputText.setText("");
                }
            }
        });
    }

    /**
     * 初始列表数据
     */
    private void initMsgs() {
        Msg msg1 = new Msg("Hello guy.", Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg("Hello. Who is that?", Msg.TYPE_SEND);
        msgList.add(msg2);
        Msg msg3 = new Msg("This is Kevin. Nice talking to you.", Msg.TYPE_RECEIVED);
        msgList.add(msg3);
    }
}
