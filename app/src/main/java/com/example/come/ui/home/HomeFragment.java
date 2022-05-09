package com.example.come.ui.home;

import static androidx.core.content.ContextCompat.checkSelfPermission;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.come.BuildConfig;
import com.example.come.R;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.come.db.Picture;
import com.example.come.db.Publication;
import com.example.come.db.RoomDB;
import com.example.come.db.User;
import com.example.come.distmatrix.Element;
import com.example.come.distmatrix.PlacesService;
import com.example.come.distmatrix.Root;
import com.example.come.distmatrix.Row;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment implements LocationListener {
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
    ArrayList<PostDataUri> fetchedPosts;
    Location deviceLoc;
    SwipeRefreshLayout swipeRefreshLayout;
    Post_RecyclerViewAdapter_Fetched post_recyclerViewAdapter_fetched;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        if (checkSelfPermission(requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        LocationManager locationManager = (LocationManager) requireActivity()
                .getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 0, 1, this);

        if (ContextCompat.checkSelfPermission(requireActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(requireActivity(),
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                         MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }

        fetchedPosts = setUpUriPosts();
        updateAllPostsUri();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.my_recyclerview);
        swipeRefreshLayout =  view.findViewById(R.id.swipeRefreshLayout);

        post_recyclerViewAdapter_fetched = new Post_RecyclerViewAdapter_Fetched(getContext(), fetchedPosts);
        recyclerView.setAdapter(post_recyclerViewAdapter_fetched);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                //do the fetch data request here
                Toast.makeText(getContext(), "Successfull refresh", Toast.LENGTH_SHORT).show();
                fetchedPosts = setUpUriPosts();
                updateAllPostsUri();
                Post_RecyclerViewAdapter_Fetched adapter = new Post_RecyclerViewAdapter_Fetched(getContext(), fetchedPosts);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });
    }

    private ArrayList<PostDataUri> setUpUriPosts(){
        ArrayList<PostDataUri> postList = new ArrayList<>();
        RoomDB db = RoomDB.getInstance(getContext());

        List<Publication> publications = db.PublicationDao().getAllPublications();

        for (Publication publication : publications){
            String caption = publication.getCaption();
            User user = db.UserDao().findUserByUsername(publication.getFk_userName());
            String username = user.getUserName();
            int publicationId = publication.getPublicationId();
            String city = publication.getCity();
            String restaurant = publication.getRestaurant();
            List<Picture> allPics = db.PictureDao().getAllPictures();
            ArrayList<Uri> pictures = new ArrayList<>();

            for (Picture picture: allPics){
                int pubId = picture.getFk_publicationId();
                if (pubId == publicationId){
                    pictures.add(Uri.parse(picture.getUrl()));
                }
            }
            Uri[] UriArray = pictures.toArray(new Uri[0]);

            String captionWithUsername = "@" + username + ": " + caption;
            postList.add(new PostDataUri(captionWithUsername, UriArray, restaurant, city));
        }

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
            for(int i = 0; i < fetchedPosts.size(); ++i){
                updatePostDistanceUri(i);
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

    private void updatePostDistanceUri(int index){
        if (deviceLoc == null) return;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PlacesService usersService = retrofit.create(PlacesService.class);

        String origin = deviceLoc.getLatitude() + ", " + deviceLoc.getLongitude();
        String destination = fetchedPosts.get(index).getAddress();
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

                PostDataUri curPost = fetchedPosts.get(index);
                curPost.setDistance(min/1000);

                fetchedPosts.set(index, curPost);
                post_recyclerViewAdapter_fetched.notifyItemChanged(index);
            }
            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Log.e(this.getClass().getSimpleName(), "Exception calling endpoint", t);
            }
        });
    }

    public void updateAllPostsUri(){
        for(int i = 0; i < fetchedPosts.size(); ++i){
            updatePostDistanceUri(i);
        }
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