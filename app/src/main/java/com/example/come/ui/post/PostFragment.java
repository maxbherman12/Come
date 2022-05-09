package com.example.come.ui.post;

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
import androidx.viewpager.widget.ViewPager;

import com.example.come.CurrentUser;
import com.example.come.R;
import com.example.come.db.Picture;
import com.example.come.db.Publication;
import com.example.come.db.RoomDB;

import java.util.List;

public class PostFragment extends Fragment {
    EditText captionField, cityField, restaurantField;
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
        postButton          = view.findViewById(R.id.button2);
        captionField        = view.findViewById(R.id.captionField);
        cityField           = view.findViewById(R.id.city_field);
        restaurantField     = view.findViewById((R.id.restaurant_field));
        viewPager_post      = view.findViewById(R.id.viewPager_post);

        postButton.setOnClickListener(v -> handlePost());

        horizontalScrollAdapter_post =
                new HorizontalScrollAdapter_Post(viewPager_post.getContext(), uriArray);
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

    public void handlePost() {
        RoomDB db = RoomDB.getInstance(getContext());
        String currentUser = ((CurrentUser) getActivity().getApplication()).getUsername();

        String captionOfPost = captionField.getText().toString();
        String cityOfPost = cityField.getText().toString();
        String restaurantOfPost = restaurantField.getText().toString();

        Publication publication = new Publication(captionOfPost,cityOfPost,restaurantOfPost,currentUser);
        db.PublicationDao().insertPublication(publication);

        List<Publication> allP =  db.PublicationDao().getAllPublications();
        Publication lastOne = allP.get(allP.size()-1);
        int pID = lastOne.getPublicationId();
        for(int i = 0; i < uriArray.length-1; i++){
            String uri = uriArray[i].toString();
            if (!uri.equals(OriginalPath.toString())){
                db.PictureDao().insertPicture(new Picture(uri, pID));
            }
        }
    }
}


