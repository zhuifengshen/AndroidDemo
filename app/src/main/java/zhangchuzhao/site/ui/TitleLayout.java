package zhangchuzhao.site.ui;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import zhangchuzhao.site.demo.R;
import zhangchuzhao.site.skill.Util;

/**
 * Created by Devin on 2016/11/9.
 */

public class TitleLayout extends LinearLayout {
    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.custom_title, this);

        Button titleBack = (Button)findViewById(R.id.title_back);
        titleBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)getContext()).finish();
            }
        });

        Button titleEdit = (Button)findViewById(R.id.title_edit);
        titleEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.showToastMessage(getContext(),"You click Edit Button");
            }
        });
    }
}
