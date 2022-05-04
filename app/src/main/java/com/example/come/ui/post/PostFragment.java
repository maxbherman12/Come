package com.example.come.ui.post;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;

import com.example.come.R;
import com.example.come.db.Picture;
import com.example.come.db.Publication;
import com.example.come.db.RoomDB;
import com.example.come.db.User;

import java.util.List;

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
            public void onClick(View v) {
                postButtonClicked();
            }
        });
        captionField = view.findViewById(R.id.captionField);
        viewPager_post = view.findViewById(R.id.viewPager_post);
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
        } catch (Exception e) {
            //just here so that the app does not crash when no picture is chosen
        }

    }

    public void postButtonClicked() {

        RoomDB db;
        db = RoomDB.getInstance(getContext());


        String username = "come";
        String password = "come";
        User user = new User();
        user.setUserName(username);
        user.setPassword(password);

        db.UserDao().insertUser(user);

        if (db.UserDao().getAllUsers() == null) {

            User newUser = new User();
            newUser.setUserName(username);
            newUser.setPassword(password);

            db.UserDao().insertUser(newUser);
        }

        User currentUser = db.UserDao().findUserByName(username);
        String captionOfPost = captionField.getText().toString();
        Publication publication = new Publication();
        publication.setCaption(captionOfPost);
        publication.setFk_userId(currentUser.getUserId());




        for(int i = 0; i < uriArray.length-1; i++){
                Picture picture = new Picture();
                picture.setUrl(uriArray[i].toString());
                picture.setFk_publicationId(publication.getPublicationId());
                System.out.println("The id of the pictures is:");
                System.out.println(picture.getpictureId());
                System.out.println("The url of the picture is:");
                System.out.println(picture.getUrl());
                System.out.println("The picture belongs to publication:");
                System.out.println(picture.getFk_publicationId());
            }


        //Uri selectedImageUri = data.getData();
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


