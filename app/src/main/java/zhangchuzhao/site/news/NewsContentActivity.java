package zhangchuzhao.site.news;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import zhangchuzhao.site.demo.R;

/**
 * 手机中,点击新闻标题,新建本活动显示新闻内容
 */
public class NewsContentActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);
        //获取传递过来的新闻标题和内容
        String newsTitle = getIntent().getStringExtra("news_title");
        String newsContent = getIntent().getStringExtra("news_content");
        //获取活动中的fragment并更新其内容信息
        NewsContentFragment newsContentFragment = (NewsContentFragment)getFragmentManager().findFragmentById(R.id.news_content_fragment);
        newsContentFragment.refresh(newsTitle, newsContent);
    }

    /**
     * 启动本活动是调用的方法, 包括其中需要传递的数据
     * @param context 调用者的上下文环境
     * @param newsTitles 新闻标题
     * @param newsContent 新闻内容
     */
    public static void actionStart(Context context, String newsTitles, String newsContent){
        Intent intent = new Intent(context, NewsContentActivity.class);
        intent.putExtra("news_title", newsTitles);
        intent.putExtra("news_content", newsContent);
        context.startActivity(intent);
    }
}
