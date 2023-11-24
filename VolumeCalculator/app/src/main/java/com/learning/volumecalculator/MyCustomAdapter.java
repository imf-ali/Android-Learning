package com.learning.volumecalculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyCustomAdapter extends ArrayAdapter<Shape> {

    private final ArrayList<Shape> shapeArrayList;
    Context context;

    public MyCustomAdapter(ArrayList<Shape> shapeArrayList, Context context) {
        super(context, R.layout.grid_item_layout, shapeArrayList);
        this.shapeArrayList = shapeArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Shape shape = getItem(position);
        MyViewHolder myViewHolder;
        if(convertView == null){
            myViewHolder = new MyViewHolder();
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.grid_item_layout, parent, false);
            myViewHolder.textView = (TextView) convertView.findViewById(R.id.textView);
            myViewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }
        assert shape != null;
        myViewHolder.textView.setText(shape.getShapeName());
        myViewHolder.imageView.setImageResource(shape.getShape());

        return convertView;
    }

    static class MyViewHolder {
        TextView textView;
        ImageView imageView;
    }
}
