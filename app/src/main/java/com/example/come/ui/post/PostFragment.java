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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.come.R;

import java.io.FileNotFoundException;
import java.io.IOException;

public class PostFragment extends Fragment {

   // private PostViewModel mViewModel;
    EditText captionField;
    ViewPager viewPager_post;
    HorizontalScrollAdapter_Post horizontalScrollAdapter_post;
    Button postButton;
    public Uri OriginalPath = Uri.parse("android.resource://com.example.come/" + R.drawable.add_your_image);
    public Uri[] uriArray = new Uri[]{OriginalPath, OriginalPath, OriginalPath, OriginalPath, OriginalPath, OriginalPath, OriginalPath};



        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.post_fragment, container, false);
        }


        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            postButton = view.findViewById(R.id.button2);
            postButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {postButtonClicked();}
            });
            captionField = view.findViewById(R.id.captionField);
            viewPager_post=view.findViewById(R.id.viewPager_post);
            horizontalScrollAdapter_post = new HorizontalScrollAdapter_Post(viewPager_post.getContext(), uriArray);
            viewPager_post.setAdapter(horizontalScrollAdapter_post);
            horizontalScrollAdapter_post.notifyDataSetChanged();
        }



    //receives on activity result from activity
        public void getIntent(Intent data) {
            try {
                Uri uri = data.getData();
                int position = horizontalScrollAdapter_post.getCurrentPosition();
                uriArray[position] = uri;
                ImageView myView = (ImageView) viewPager_post.findViewWithTag(position);
                myView.setImageURI(uri);
            }
            catch (Exception e){
            //just here so that the app does not crash when no picture is chosen
            }

        }

        public void postButtonClicked(){
            String captionOfPost = captionField.getText().toString();
            //Implement Code here Gorka
        }
/*
        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            mViewModel = new ViewModelProvider(this).get(PostViewModel.class);
            // TODO: Use the ViewModel
        }

 */




}