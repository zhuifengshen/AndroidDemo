package zhangchuzhao.site.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import zhangchuzhao.site.demo.R;

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
    }

    private void initFruits() {
        for (String fruitName : data) {
            String fruitImage = fruitName.toLowerCase() + "_pic";
            fruitList.add(new Fruit(fruitName,getResources().getIdentifier(fruitImage, "drawable", getPackageName())));
            //fruitList.add(new Fruit(fruitName, getResourceIdByResourceName(this, fruitImage)));
            //fruitList.add(new Fruit(fruitName, getResourceIdByResourceName(this, fruitName, "drawable", getPackageName())));
        }
    }


    public static int getResourceIdByResourceName(Context contex, String resourceName){
        int resourceId = 0;
        try {
            Field field = R.drawable.class.getField(resourceName);
            field.setAccessible(true);
            try {
                resourceId = field.getInt(null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return resourceId;
    }

    //public static int getResourceIdByResourceName(Context context, String name, String type, String packageName){
    //    return context.getResources().getIdentifier(name, type, packageName);
    //}
}
