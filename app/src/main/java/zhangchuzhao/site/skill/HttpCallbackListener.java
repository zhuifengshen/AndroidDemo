package zhangchuzhao.site.skill;

/**
 * Created by Devin on 2016/11/16.
 */

public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
