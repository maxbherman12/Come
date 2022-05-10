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
import android.widget.Toast;

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
    View globalView;

    public Uri OriginalPath = Uri.parse("android.resource://com.example.come/" + R.drawable.add_your_image);
    public Uri[] uriArray = new Uri[]{OriginalPath, OriginalPath, OriginalPath, OriginalPath, OriginalPath, OriginalPath, OriginalPath};

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        globalView = inflater.inflate(R.layout.post_fragment, container, false);
        return globalView;
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

    /**
     * handlePost()
     * Pulls data from the entry fields and publishes them to the database
     */
    private void handlePost() {
        RoomDB db = RoomDB.getInstance(getContext());
        String currentUser = ((CurrentUser) getActivity().getApplication()).getUsername();

        String captionOfPost = captionField.getText().toString().trim();
        String cityOfPost = cityField.getText().toString().trim();
        String restaurantOfPost = restaurantField.getText().toString().trim();

        String postValidation = validatePost(captionOfPost, restaurantOfPost, cityOfPost);
        if(postValidation.equals("valid")){
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

            resetPostFields();
            Toast.makeText(globalView.getContext(), "Successfully posted", Toast.LENGTH_SHORT).show();
        } else {
            String errorMessage = "Cannot publish post. " + postValidation;
            Toast.makeText(globalView.getContext(), errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * resetPostFields()
     * Resets all entry fields in then post fragment once an item has been posted
     */
    private void resetPostFields(){
        captionField.setText("");
        restaurantField.setText("");
        cityField.setText("");
        int count = 0;

        for(int i = 0; i < uriArray.length; ++i){
            if (uriArray[i] !=OriginalPath){
                count=count+1;
            }

            for (int s = 0; s < count; ++s) {
                uriArray[i] = OriginalPath;
                ImageView myView = (ImageView) viewPager_post.findViewWithTag(s);
                myView.setImageURI(OriginalPath);
            }
        }
    }

    /**
     * validatePost()
     * Ensures that all fields for the post that is to be published are valid
     * @param caption - post caption
     * @param name - restaurant name of post
     * @param city - city of post
     * @return - valid if the post is valid, otherwise the error message to display
     */
    private String validatePost(String caption, String name, String city){
        // Must include all fields
        if(caption.equals("") && name.equals("") && city.equals("")){
            return "You must include all fields";
        }
        // Caption must be at least 20 characters and no more than 200 characters
        if(caption.length() < 20 || caption.length() > 200){
            return "Caption must be between 20 and 200 characters";
        }
        // Restaurant name cannot be more than 40 characters long
        if(name.length() > 40){
            return "Restaurant name must be no more than 40 characters";
        }
        // All posts must include one photo
        int count = 0;
        for(int i = 0; i < uriArray.length-1; i++){
            String uri = uriArray[i].toString();
            if (!uri.equals(OriginalPath.toString())){
                ++count;
            }
        }
        if(count == 0) return "You must include at least one photo";

        // Else, return valid
        return "valid";
    }
}


