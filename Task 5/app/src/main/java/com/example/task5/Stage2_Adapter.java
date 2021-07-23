package com.task5.stages;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;

import org.w3c.dom.Text;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Stage2_Adapter extends RecyclerView.Adapter<Stage2_Adapter.MyViewHolder> {
    private List<Stage2_Model> dataList;
    private Context context;
    public Stage2_Adapter(Context context,List<Stage2_Model> dataList)
    {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public Stage2_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.stage2_cardview,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.stage_text.setText(dataList.get(position).getText());
        holder.stage_image.setImageResource(dataList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView stage_text;
        CircleImageView stage_image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            stage_text = itemView.findViewById(R.id.stage2_text);
            stage_image = itemView.findViewById(R.id.stage2_circular_image);
        }
    }
}
