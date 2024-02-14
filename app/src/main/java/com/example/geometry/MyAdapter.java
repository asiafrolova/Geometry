package com.example.geometry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyAdapter extends ArrayAdapter<Stroke> {
    Repository repository=Repository.newInstance();
    public MyAdapter(@NonNull Context context, Stroke[] arr) {
        super(context, R.layout.adapter_item,arr);
        notifyDataSetChanged();

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Stroke stroke = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_item, null);
        }
        ((TextView) convertView.findViewById(R.id.textView)).setText(stroke.getFunction()+"  "+stroke.getAngle()+" =");
        if (stroke.getAnswer()==1){
            ((ImageView) convertView.findViewById(R.id.imageView)).setImageResource(R.drawable.yes);
        }
        else if(stroke.getAnswer()==0)
            ((ImageView) convertView.findViewById(R.id.imageView)).setImageResource(R.drawable.no);

        return convertView;

    }


}
