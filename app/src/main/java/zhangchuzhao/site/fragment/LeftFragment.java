package zhangchuzhao.site.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import zhangchuzhao.site.demo.R;
import zhangchuzhao.site.skill.Util;


public class LeftFragment extends Fragment {
    public LeftFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup containter, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_left, containter, false);
    }

    public void helloLeft(){
        Util.showToastMessage((FragmentActivity)getActivity(), "I'm a left fragment");
        Log.d("fragment", "left");
    }
}
