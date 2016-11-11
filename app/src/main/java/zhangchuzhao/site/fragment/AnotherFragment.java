package zhangchuzhao.site.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import zhangchuzhao.site.demo.R;
import zhangchuzhao.site.skill.Util;

/**
 * A simple {@link Fragment} subclass.
 */
public class AnotherFragment extends Fragment {


    public AnotherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_another, container, false);
    }

    public void helloAnther(){
        Util.showToastMessage((FragmentActivity)getActivity(), "I'm a another fragment");
        Log.d("fragment", "another");
    }
}
