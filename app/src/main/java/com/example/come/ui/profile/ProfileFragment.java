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
import android.widget.ListView;

import com.example.come.R;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {

    private ProfileViewModel mViewModel;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);

        List<String> restaurantList = getRestaurants();
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(view.getContext(),
                android.R.layout.simple_list_item_1, restaurantList);
        ListView list = view.findViewById(R.id.restaurant_list);
        list.setAdapter(listAdapter);

        // tmp list of restaurants, replace later with fetching from db
//        listAdapter.notifyDataSetChanged();

        return view;
    }

    public List<String> getRestaurants(){
        List<String> restaurantList = new ArrayList<>();
        String[] restaurants = {"Humuseria", "Cherry Pecas", "La Musa Latina", "Vietnamese Express"};
        for(String restaurant: restaurants){
            restaurantList.add(restaurant);
        }
        return restaurantList;
    }
}