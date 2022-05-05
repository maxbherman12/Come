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

public class Post_RecyclerViewAdapter_Fetched extends RecyclerView.Adapter<Post_RecyclerViewAdapter_Fetched.MyViewHolder> {
    Context context;
    ArrayList<PostDataUri> posts;

    public Post_RecyclerViewAdapter_Fetched(Context context, ArrayList<PostDataUri> posts){
        this.context=context;
        this.posts=posts;
    }



    @NonNull
    @Override
    public Post_RecyclerViewAdapter_Fetched.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflating the layout (give look to each of the posts/rows)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_container, parent, false);

        return new Post_RecyclerViewAdapter_Fetched.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Post_RecyclerViewAdapter_Fetched.MyViewHolder holder, int position) {
        //assigning values to the views created int the recyclerview_container.xml
        //when they come back on screen, depends on position of the row of reyclerview
        holder.textViewCaption.setText(posts.get(position).getCaption());
        holder.distanceText.setText(posts.get(position).getDistanceStr());
        holder.horizontalScrollAdapter_fetched.setUriArray(posts.get(position).getUrisOfPost());
        holder.nameText.setText(posts.get(position).getName());
        holder.viewPager.setAdapter(holder.horizontalScrollAdapter_fetched);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        //grabbing the views from the recyclerview_container file and assigning them to variables
        //like the onCreate method
        ViewPager viewPager;
        TextView textViewCaption;
        TextView distanceText;
        TextView nameText;
        HorizontalScrollAdapter_fetched horizontalScrollAdapter_fetched;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            viewPager = itemView.findViewById(R.id.myViewPager);
            textViewCaption = itemView.findViewById(R.id.captionField);
            distanceText = itemView.findViewById(R.id.distanceField);
            nameText = itemView.findViewById(R.id.nameField);
            horizontalScrollAdapter_fetched = new HorizontalScrollAdapter_fetched(viewPager.getContext());
        }
    }
}

