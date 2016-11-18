package zhangchuzhao.site.news;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import zhangchuzhao.site.demo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsContentFragment extends Fragment {

    private View view;

    public NewsContentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_news_content, container, false);
        return view;
    }

    /**
     * 这个方法用于更新内容布局的新闻标题和内容显示
     * @param newsTitle 新闻标题
     * @param newsContent 新闻内容
     */
    public void refresh(String newsTitle, String newsContent){
        View visibilityLayout = view.findViewById(R.id.visibility_layout);
        visibilityLayout.setVisibility(View.VISIBLE);
        TextView newsTitleText = (TextView)view.findViewById(R.id.news_title);
        newsTitleText.setText(newsTitle);
        TextView newsContentText = (TextView)view.findViewById(R.id.news_content);
        newsContentText.setText(newsContent);
    }

}
