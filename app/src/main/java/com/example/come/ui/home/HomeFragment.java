package com.example.come.ui.home;

import android.app.PictureInPictureUiState;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
//import android.R;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.come.BuildConfig;
import com.example.come.R;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.come.databinding.FragmentHomeBinding;
import com.example.come.db.Picture;
import com.example.come.db.Publication;
import com.example.come.db.RoomDB;
import com.example.come.db.User;
import com.example.come.distmatrix.Element;
import com.example.come.distmatrix.PlacesService;
import com.example.come.distmatrix.Root;
import com.example.come.distmatrix.Row;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment implements LocationListener {
    ArrayList<PostData> posts;
    Location deviceLoc;
    SwipeRefreshLayout swipeRefreshLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        if (ContextCompat.checkSelfPermission(requireActivity(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        LocationManager locationManager = (LocationManager) requireActivity()
                .getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 0, 1, this);

        posts = setUpPosts();
        for(int i = 0; i < posts.size(); ++i){
            updatePostDistance(i);
        }

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.my_recyclerview);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);

        Post_RecyclerViewAdapter adapter = new Post_RecyclerViewAdapter(getContext(), posts);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);

                //do the fetch data request here
                Toast.makeText(getContext(), "Successfull refresh", Toast.LENGTH_SHORT).show();
                setUpUriPosts();


            }
        });
    }

    private ArrayList<PostDataUri> setUpUriPosts(){
        ArrayList<PostDataUri> postList = new ArrayList<>();
        // Uri myUri = Uri.parse(string)

        return postList;
    }

    /**
     * Listens for when device location changes and updates global location value
     * NOTE: Will eventually update all posts to have correct distance when distance
     * has changed significantly
     * @param location
     */
    @Override
    public void onLocationChanged(Location location){
        final double TOLERANCE = 0.5; // min dist (km) needed to required update
        if(deviceLoc == null || distBetweenLocations(location, deviceLoc) > TOLERANCE) {
            deviceLoc = location;
            for(int i = 0; i < posts.size(); ++i){
                updatePostDistance(i);
            }
        }
    }

    /**
     * Finds the distance in kilometers between two sets of coordinates
     * @param l1 - location 1
     * @param l2 - location 2
     * @return - distance between l1 and l2 in km
     */
    private double distBetweenLocations(Location l1, Location l2){
        double lon1 = Math.toRadians(l1.getLongitude());
        double lat1 = Math.toRadians(l1.getLatitude());
        double lon2 = Math.toRadians(l2.getLongitude());
        double lat2 = Math.toRadians(l2.getLatitude());

        // Haversine formula
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Multiply by 6371 to get distance in kilometers
        return (c * 6371);
    }


    private ArrayList<PostData> setUpPosts(){
        ArrayList<PostData> list = new ArrayList<>();

        RoomDB db;
        db = RoomDB.getInstance(getContext());

        String[] captions= {"Dummy post nr 1","Another Food review","Last review of me"};
        int[][] images= {
                {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3},
                {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3},
                {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3}
        };
        String[] names = {"Yatai Market", "Honest Greens",
                "Takos Al Pastor"};

        String[] cities = {"Madrid", "madrid", "Madrid"};
        for (int i=0; i<3; i++){
            list.add(new PostData(captions[i],images[i], names[i], cities[i]));
        }

        return list;
    }

    private void updatePostDistance(int index){
        if (deviceLoc == null) return;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PlacesService usersService = retrofit.create(PlacesService.class);

        String origin = deviceLoc.getLatitude() + ", " + deviceLoc.getLongitude();
        String destination = posts.get(index).getAddress();
        String key = BuildConfig.MAPS_API_KEY;

        Call<Root> call = usersService.queryDistance(origin, destination, key);

        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call,
                                   Response<Root> response) {
                // Find min distance to location
                double min = -1.0;
                assert response.body() != null;
                for (Row row : response.body().rows) {
                    for (Element element: row.elements){
                        if (element.distance == null) return;
                        else{
                            double dist = Integer.parseInt(element.distance.value);
                            if (min == -1 || min > dist){
                                min = dist;
                            }
                        }

                    }
                }

                posts.get(index).setDistance(min/1000);
            }
            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Log.e(this.getClass().getSimpleName(), "Exception calling endpoint", t);
            }
        });
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
    }
}