package com.example.geometry;



import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.geometry.databinding.AdapterItemBinding;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.StrokeViewHolder> {
    private List<Stroke> strokes = new ArrayList<>();

    Repository repository=Repository.newInstance();
    /*public MyAdapter(@NonNull Context context, Stroke[] arr) {
        super(context, R.layout.adapter_item,arr);
        notifyDataSetChanged();

    }*/

    public void setStrokeList(List<Stroke> strokes) {
        this.strokes = strokes;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public StrokeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item,
                parent, false);
        return new StrokeViewHolder(AdapterItemBinding.bind(view));
    }


    @Override
    public void onBindViewHolder(@NonNull StrokeViewHolder holder, int position) {
        Stroke stroke = strokes.get(position);
        holder.binding.textView.setText(stroke.getFunction()+" "+stroke.getAngle()+"= ");

        if(!repository.isNewFunctions()){
            holder.binding.editText.setText(repository.getRepository()[position]);
        }

        holder.binding.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                repository.get(s.toString(),position);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        if (stroke.getAnswer()==1){
            holder.binding.imageView.setImageResource(R.drawable.yes);
        }
        else if(stroke.getAnswer()==0) {
            holder.binding.imageView.setImageResource(R.drawable.no);
            if(repository.isNight()){
                holder.binding.imageCorrect.setImageResource(R.drawable.correct_dark);
            }
            else{
                holder.binding.imageCorrect.setImageResource(R.drawable.correct);
            }

            holder.binding.imageCorrect.setOnClickListener(v -> {
                holder.binding.textCorrect.setText(" "+repository.getCorrectAnswers()[position]);
            });
        }


    }

    @Override
    public int getItemCount() {
        return strokes.size();
    }

    static class StrokeViewHolder extends RecyclerView.ViewHolder {

        AdapterItemBinding binding;

        public StrokeViewHolder(AdapterItemBinding b) {
            super(b.getRoot());
            binding = b;
        }
    }


/*    @NonNull
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

    }*/


}
