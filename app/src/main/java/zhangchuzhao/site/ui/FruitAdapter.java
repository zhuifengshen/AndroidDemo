package zhangchuzhao.site.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import zhangchuzhao.site.demo.R;

/**
 * Created by Devin on 2016/11/9.
 */

public class FruitAdapter extends ArrayAdapter<Fruit> {

    private int resourceId;

    public FruitAdapter(Context context, int resource, List<Fruit> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Fruit fruit = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        ImageView fruitImage = (ImageView)view.findViewById(R.id.fruit_image);
        fruitImage.setImageResource(fruit.getImageId());
        TextView fruitName = (TextView)view.findViewById(R.id.fruit_name);
        fruitName.setText(fruit.getName());
        return view;
    }
}
