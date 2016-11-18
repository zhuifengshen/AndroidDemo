package zhangchuzhao.site.thread;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import zhangchuzhao.site.skill.Util;

/**
 * Created by Devin on 2016/11/14.
 */

public class DownloadTask extends AsyncTask<Void, Integer, Boolean> {

    private ProgressBar progressBar;
    private Context context;

    public DownloadTask(Context context, ProgressBar progressBar){
        this.context = context;
        this.progressBar = progressBar;
    }

    //初始化操作
    @Override
    protected void onPreExecute() {
        Util.showToastMessage(context, "开始下载");
    }

    //子线程中运行耗时操作
    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            int i = 0;
            while (true){
                int downloadPercent = i++;
                publishProgress(downloadPercent);//反馈进度
                if (downloadPercent >= 100){
                    break;
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }catch (Exception e){
            return false;
        }
        return true;
    }

    //更新UI
    @Override
    protected void onProgressUpdate(Integer... values) {
        progressBar.setProgress(values[0]);
    }

    //处理返回结果，收尾工作
    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if (aBoolean){
            Util.showToastMessage(context, "Download Success");
        }else {
            Util.showToastMessage(context, "DownLoad failed");
        }
        progressBar.setVisibility(View.INVISIBLE);
    }
}
