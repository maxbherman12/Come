package com.example.come.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
//import android.R;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.come.R;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.come.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    ArrayList<PostData> posts= new ArrayList<>();
    private FragmentHomeBinding binding;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.my_recyclerview);
        setUpPosts();
        Post_RecyclerViewAdapter adapter = new Post_RecyclerViewAdapter(getContext(), posts);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }


    private void setUpPosts(){
        String[] captions= {"This is my food review","Another Food review","Last review of me"};
        int[][] images= {
                {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3},
                {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3},
                {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3}
        };
        for (int i=0; i<3; i++){
            posts.add(new PostData(captions[i],images[i]));
        }
    }






    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}