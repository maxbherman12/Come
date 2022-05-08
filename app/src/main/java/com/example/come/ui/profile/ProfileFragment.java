package com.example.come.ui.profile;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.come.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProfileFragment extends Fragment {
    TextView name;
    TextView username;
    TextView bio;
    ImageView image;
    ListView list;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);

        // Connect all variables to layout
        name        = view.findViewById(R.id.profile_fullname);
        username    = view.findViewById(R.id.profile_username);
        bio         = view.findViewById(R.id.profile_bio);
        image       = view.findViewById(R.id.profile_image);
        list        = view.findViewById(R.id.restaurant_list);


        // Create ProfileData object
        // TODO: Replace hard coded values with database implementation
        ProfileData profileData = new ProfileData("John Smith","@john_smith_eats",
                "Here is my bio", R.drawable.profile_photo, getRestaurants());

        // Set values
        name.setText(profileData.getName());
        username.setText(profileData.getUsername());
        bio.setText(profileData.getBio());
        image.setImageResource(profileData.getImage());

        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(view.getContext(),
                android.R.layout.simple_list_item_1, profileData.getToVisitList());
        list.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();

        return view;
    }

    // TODO: Replace this method with database implementation
    public List<String> getRestaurants(){
        String[] restaurants = {"Humuseria", "Cherry Pecas", "La Musa Latina", "Vietnamese Express"};
        return new ArrayList<>(Arrays.asList(restaurants));
    }
}