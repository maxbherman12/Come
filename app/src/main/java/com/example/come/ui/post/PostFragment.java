package com.example.come.ui.post;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.come.R;

import java.io.FileNotFoundException;
import java.io.IOException;

public class PostFragment extends Fragment {
    //public Uri uri;
    private PostViewModel mViewModel;
    ViewPager viewPager_post;
    HorizontalScrollAdapter_Post horizontalScrollAdapter_post;
    int[] number_image_holders = new int[]{1,2,3,4,5,6,7};
    Button mybutton;

    /*
    public static PostFragment newInstance() {
        return new PostFragment();
    }*/

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.post_fragment, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mybutton = view.findViewById(R.id.button2);
        viewPager_post=view.findViewById(R.id.viewPager_post);
        horizontalScrollAdapter_post = new HorizontalScrollAdapter_Post(viewPager_post.getContext());
        horizontalScrollAdapter_post.setButtonArray(number_image_holders);
        viewPager_post.setAdapter(horizontalScrollAdapter_post);

    }



    public void getIntent(Intent data) {
        Uri uri = data.getData();
        int position = horizontalScrollAdapter_post.getCurrentPosition();
        try {
            Bitmap selectedImage = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
            ImageView myView =(ImageView) viewPager_post.findViewWithTag(position);
            myView.setImageBitmap(selectedImage);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PostViewModel.class);
        // TODO: Use the ViewModel
    }




}