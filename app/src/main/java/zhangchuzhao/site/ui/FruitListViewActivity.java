package zhangchuzhao.site.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import zhangchuzhao.site.demo.R;
import zhangchuzhao.site.skill.Util;

public class FruitListViewActivity extends Activity {

    private List<Fruit> fruitList = new ArrayList<>();
    private String[] data = {"Apple", "Banana", "Orange", "Watermelon", "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango","apple", "Banana", "Orange", "Watermelon", "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_list_view);
        initFruits();
        FruitAdapter fruitAdapter = new FruitAdapter(FruitListViewActivity.this, R.layout.fruit_item, fruitList);
        ListView listView = (ListView)findViewById(R.id.fruit_list_view);
        listView.setAdapter(fruitAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit = fruitList.get(position);
                Util.showToastMessage(FruitListViewActivity.this, fruit.getName());
                Log.d("fruit", fruit.getName());
            }
        });
    }
    private void initFruits() {
        for (String fruitName : data) {
            String fruitImage = fruitName.toLowerCase() + "_pic";
            //fruitList.add(new Fruit(fruitName, Util.getResourceIdByResourceName(this, fruitImage)));
            //fruitList.add(new Fruit(fruitName, getResources().getIdentifier(fruitImage, "drawable", getPackageName())));
            //fruitList.add(new Fruit(fruitName, getResourceIdByResourceName(fruitImage, "drawable", getPackageName())));
            fruitList.add(new Fruit(fruitName, Util.getResourceIdByResourceName(FruitListViewActivity.this, fruitImage, "drawable", getPackageName())));
        }
    }

    //获取指定资源的id
    public int getResourceIdByResourceName(String name, String type, String packageName){
        return getResources().getIdentifier(name, type, packageName);
    }

}
