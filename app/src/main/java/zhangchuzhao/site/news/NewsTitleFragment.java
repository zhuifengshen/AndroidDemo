package zhangchuzhao.site.news;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import zhangchuzhao.site.demo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsTitleFragment extends Fragment implements AdapterView.OnItemClickListener{

    private ListView newsTitleListView;
    private List<News> newsList;
    private NewsAdapter newsAdapter;
    private boolean isTwoPane;

    public NewsTitleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //初始化新闻数据
        newsList = getNews();
        newsAdapter = new NewsAdapter(activity, R.layout.news_item, newsList);
    }

    /**
     * 初始化新闻数据方法
     * @return
     */
    private List<News> getNews() {
        List<News> newsList = new ArrayList<News>();
        News news1 = new News();
        news1.setTitle("Succeed in College as a Learning Disabled Student");
        news1.setContent("College freshmen will soon learn to live with a    roommate, adjust to a new social scene and survive less-than-stellar    dining hall food. Students with learning disabilities will face these    transitions while also grappling with a few more hurdles.");
        newsList.add(news1);
        News news2 = new News();
        news2.setTitle("Google Android exec poached by China's Xiaomi");
        news2.setContent("China's Xiaomi has poached a key Google executive    involved in the tech giant's Android phones, in a move seen as a coup    for the rapidly growing Chinese smartphone maker.");
        newsList.add(news2);
        return newsList;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news_title, container, false);
        //初始化并设置新闻标题列表
        ListView newsTitleListView = (ListView)view.findViewById(R.id.news_title_list_view);
        newsTitleListView.setAdapter(newsAdapter);
        newsTitleListView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        //判断当前设备是否显示新闻内容布局
        if (getActivity().findViewById(R.id.news_content_fragment) == null){
            isTwoPane = false;
        }else {
            isTwoPane = true;
        }
    }

    //响应新闻标题列表点击事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        News news = newsList.get(position);
        if (isTwoPane){
            NewsContentFragment newsContentFragment = (NewsContentFragment)getFragmentManager().findFragmentById(R.id.news_content_fragment);
            newsContentFragment.refresh(news.getTitle(), news.getContent());
        }else{
            NewsContentActivity.actionStart(getActivity(), news.getTitle(), news.getContent());
        }
    }
}
