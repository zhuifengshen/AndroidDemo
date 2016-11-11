package zhangchuzhao.site.fragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import zhangchuzhao.site.demo.R;

public class FragmentActivity extends Activity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        Button button = (Button)findViewById(R.id.button_left);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //FragmentManager manager = getFragmentManager();
        switch (v.getId()){
            case R.id.button_left:
                //1.创建碎片实例
                AnotherFragment fragment = new AnotherFragment();
                //2.获取FragmentManager
                FragmentManager fragmentManager = getFragmentManager();
                //3.开启一个事务
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                //4.向容器内加入碎片
                transaction.replace(R.id.right_fragment, fragment);
                transaction.addToBackStack(null);//将一个事务添加到返回栈中,可以接收一个名字用于描述返回栈的状态，一般传入 null即可
                //5.提交事务
                transaction.commit();
                break;
            //case R.id.button_left1:
            //    LeftFragment leftFragment = (LeftFragment)manager.findFragmentById(R.id.left_fragment);
            //    leftFragment.helloLeft();
            //    break;
            //case R.id.button_left2:
            //    RightFragment rightFragment = (RightFragment)manager.findFragmentById(R.id.right_fragment);
            //    rightFragment.helloRight();
            //    break;
            //case R.id.button_left3:
            //    AnotherFragment anotherFragment = (AnotherFragment)manager.findFragmentById(R.id.right_fragment);
            //    anotherFragment.helloAnther();
            //    break;
            default:
                break;
        }
    }
}
