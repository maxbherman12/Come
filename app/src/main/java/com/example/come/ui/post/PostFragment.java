package com.example.come.ui.post;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.come.R;
import com.example.come.ui.home.HorizontalScrollAdapter;

public class PostFragment extends Fragment {

    private PostViewModel mViewModel;
    ViewPager viewPager_post;
    HorizontalScrollAdapter_Post horizontalScrollAdapter_post;
    int[] number_image_holders = new int[]{0,1,2,3,4,5,6};

    public static PostFragment newInstance() {
        return new PostFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.post_fragment, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager_post=view.findViewById(R.id.viewPager_post);
        horizontalScrollAdapter_post = new HorizontalScrollAdapter_Post(viewPager_post.getContext());
        horizontalScrollAdapter_post.setButtonArray(number_image_holders);
        viewPager_post.setAdapter(horizontalScrollAdapter_post);
    }






    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PostViewModel.class);
        // TODO: Use the ViewModel
    }



}