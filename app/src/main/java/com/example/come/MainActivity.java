package com.example.come;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.come.ui.post.PostFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.come.databinding.ActivityMainBinding;
import com.example.come.R;
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    public NavController navController;
    //PostFragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.postFragment, R.id.searchFragment, R.id.profileFragment)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        //fragment =(PostFragment) getSupportFragmentManager().findFragmentById(R.id.postFragment);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Toast.makeText(this, "One", Toast.LENGTH_SHORT).show();

           // navController.findDestination(1);
            NavHostFragment navHostFragment= (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_main);
            PostFragment fragment = (PostFragment) navHostFragment.getChildFragmentManager().getPrimaryNavigationFragment();
           // PostFragment fragment = (PostFragment) getSupportFragmentManager().findFragmentById(R.id.postFragment);
            fragment.getIntent(data);
    }
}