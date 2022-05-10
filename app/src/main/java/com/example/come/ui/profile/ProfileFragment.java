package com.example.come.ui.profile;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.come.CurrentUser;
import com.example.come.R;

import java.util.List;

public class ProfileFragment extends Fragment {
    TextView name, username, bio;
    EditText addText;
    ImageView image, addBtn;
    ListView list;
    List<String> visitList;

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
        addText     = view.findViewById(R.id.profile_add_list_text);
        addBtn      = view.findViewById(R.id.profile_add_button);

        // Create ProfileData object from stored current user
        ProfileData profileData = new ProfileData(
                ((CurrentUser) getActivity().getApplication()).getName(),
                ((CurrentUser) getActivity().getApplication()).getUsername(),
                ((CurrentUser) getActivity().getApplication()).getBio(),
                ((CurrentUser) getActivity().getApplication()).getImg(),
                ((CurrentUser) getActivity().getApplication()).getRestaurantList());

        // Set values
        name.setText(profileData.getName());
        username.setText(profileData.getUsername());
        bio.setText(profileData.getBio());
        image.setImageResource(profileData.getImage());

        visitList = profileData.getToVisitList();
        MyAdapter listAdapter = new MyAdapter(view.getContext(), visitList);
        list.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();

        addBtn.setOnClickListener(view1 -> {
            String valToAdd = addText.getText().toString();
            addText.setText("");
            visitList.add(valToAdd);
            profileData.setToVisitList(visitList);
            ((CurrentUser) getActivity().getApplication()).setRestaurantList(visitList);
            listAdapter.notifyDataSetChanged();
        });

        return view;
    }

    class MyAdapter extends ArrayAdapter<String> {
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
                ((CurrentUser) getActivity().getApplication()).setRestaurantList(list);
                notifyDataSetChanged();
            });

            return row;
        }

        @Override
        public boolean isEnabled(int position){
            return false;
        }
    }
}