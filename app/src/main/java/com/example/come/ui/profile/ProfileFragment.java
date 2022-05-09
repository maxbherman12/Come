package com.example.come.ui.profile;

import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.come.MyApplication;
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

        //get the username
        String loggedInUser = ((MyApplication) getActivity().getApplication()).getSomeVariable();
        loggedInUser = "@"+loggedInUser;
        // Create ProfileData object
        // TODO: Replace hard coded values with database implementation
        ProfileData profileData = new ProfileData(loggedInUser,loggedInUser,
                "Here is my bio", R.drawable.profile_photo, getRestaurants());

        // Set values
        name.setText(profileData.getName());
        username.setText(profileData.getUsername());
        bio.setText(profileData.getBio());
        image.setImageResource(profileData.getImage());

        MyAdapter listAdapter = new MyAdapter(view.getContext(), profileData.getToVisitList());
        list.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();

        return view;
    }

    static class MyAdapter extends ArrayAdapter<String> {
        Context context;
        List<String> list;

        public MyAdapter(@NonNull Context context, List<String> lst) {
            super(context, android.R.layout.simple_list_item_1, lst);
            this.context = context;
            this.list = lst;
        }

        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent ){
            LayoutInflater layoutInflater =
                    (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            @SuppressLint("ViewHolder") View row =
                    layoutInflater.inflate(R.layout.profile_row, parent, false);

            TextView item = row.findViewById(R.id.profile_list_item);
            Button btn = row.findViewById(R.id.profile_list_btn);

            item.setText(list.get(position));

            btn.setOnClickListener(view -> {
                list.remove(position);
                notifyDataSetChanged();
            });

            return row;
        }

        @Override
        public boolean isEnabled(int position){
            return false;
        }
    }

    // TODO: Replace this method with database implementation
    public List<String> getRestaurants(){
        String[] restaurants = {"Humuseria", "Cherry Pecas", "La Musa Latina", "Vietnamese Express"};
        return new ArrayList<>(Arrays.asList(restaurants));
    }
}