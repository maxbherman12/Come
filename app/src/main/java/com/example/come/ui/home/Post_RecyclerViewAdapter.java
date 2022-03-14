package com.example.come.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import com.example.come.R;
import androidx.annotation.NonNull;
import androidx.core.view.ViewParentCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;


import java.util.ArrayList;

public class Post_RecyclerViewAdapter extends RecyclerView.Adapter<Post_RecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<PostData> posts;
    
    public Post_RecyclerViewAdapter(Context context, ArrayList<PostData> posts){
        this.context=context;
        this.posts=posts;
    }



    @NonNull
    @Override
    public Post_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflating the layout (give look to each of the posts/rows)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_container, parent, false);

        return new Post_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Post_RecyclerViewAdapter.MyViewHolder holder, int position) {
        //assigning values to the views created int the recyclerview_container.xml
        //when they come back on screen, depends on position of the row of reyclerview
        holder.textViewCaption.setText(posts.get(position).getCaption());
        holder.horizontalScrollAdapter.setImageArray(posts.get(position).getImageArray());
        holder.viewPager.setAdapter(holder.horizontalScrollAdapter);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        //grabbing the views from the recyclerview_conteiner file and assigning them to variables
        //like the onCreate method
        ViewPager viewPager;
        TextView textViewCaption;
        HorizontalScrollAdapter horizontalScrollAdapter;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            viewPager = itemView.findViewById(R.id.myViewPager);
            textViewCaption = itemView.findViewById(R.id.captionField);
            horizontalScrollAdapter = new HorizontalScrollAdapter(viewPager.getContext());
        }
    }
}
