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

public class Stage3_Adapter extends RecyclerView.Adapter<Stage3_Adapter.MyViewHolder> {
    private List<Stage3_Model> dataList;
    private Context context;
    public Stage3_Adapter(Context context,List<Stage3_Model> dataList)
    {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public Stage3_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.stage3_cardview,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.title.setText(dataList.get(position).getTitle());
        holder.desc.setText(dataList.get(position).getDesc());
        holder.followers.setText(dataList.get(position).getFollowers());
        holder.posts.setText(dataList.get(position).getPosts());
        holder.following.setText(dataList.get(position).getFollowing());
        holder.stage_image.setImageResource(dataList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView title,desc,followers,posts,following;
        CircleImageView stage_image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.desc);
            followers = itemView.findViewById(R.id.followers);
            posts = itemView.findViewById(R.id.posts);
            following = itemView.findViewById(R.id.following);
            stage_image = itemView.findViewById(R.id.stage3_circular_image);
        }
    }
}