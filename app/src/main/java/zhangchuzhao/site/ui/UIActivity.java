package zhangchuzhao.site.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import zhangchuzhao.site.demo.R;
import zhangchuzhao.site.skill.Util;

public class UIActivity extends Activity implements View.OnClickListener{

    private Button button;
    private EditText editText;
    private ImageView imageView;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(this);
        editText = (EditText)findViewById(R.id.edit_text);
        imageView = (ImageView)findViewById(R.id.image_view);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                //String inputText = editText.getText().toString();
                //Toast.makeText(this, "your input text: " + inputText, Toast.LENGTH_LONG).show();

                //imageView.setImageResource(R.drawable.offerwall);

                //if (progressBar.getVisibility() == View.GONE){
                //    progressBar.setVisibility(View.VISIBLE);
                //}else {
                //    progressBar.setVisibility(View.GONE);
                //}

                //int progress = progressBar.getProgress();
                //progress += 10;
                //progressBar.setProgress(progress);
                //Util.showAlerDialog(UIActivity.this, "当前进度：" + Integer.toString(progress));

                Util.showProgressDialog(UIActivity.this);
                break;
            default:
                Toast.makeText(this, "you didn't click anything", Toast.LENGTH_LONG).show();
                break;
        }
    }


}
