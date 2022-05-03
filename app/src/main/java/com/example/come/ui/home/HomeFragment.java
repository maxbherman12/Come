package com.example.come.ui.home;

import android.app.PictureInPictureUiState;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
//import android.R;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import com.example.come.R;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.come.databinding.FragmentHomeBinding;
import com.example.come.db.Picture;
import com.example.come.db.Publication;
import com.example.come.db.RoomDB;
import com.example.come.db.User;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    ArrayList<PostData> posts;
    private FragmentHomeBinding binding;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


       /* HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);*/

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.my_recyclerview);
        posts = setUpPosts();
        Post_RecyclerViewAdapter adapter = new Post_RecyclerViewAdapter(getContext(), posts);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }


    private ArrayList<PostData> setUpPosts(){
        ArrayList<PostData> list = new ArrayList<>();

        RoomDB db;
        db = RoomDB.getInstance(getContext());

        String[] captions= {"This is my food review","Another Food review","Last review of me"};
        int[][] images= {
                {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3},
                {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3},
                {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3}
        };
        for (int i=0; i<3; i++){
            list.add(new PostData(captions[i],images[i]));
        }

        return list;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==999){
            Toast.makeText(getContext(), "One", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}