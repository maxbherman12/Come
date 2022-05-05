package com.example.come.ui.home;

import android.net.Uri;

import java.util.ArrayList;

public class PostDataUri {
    String caption;
    Uri[] urisOfPost;
    String name;
    String city;
    double distance;
    String address;

    public PostDataUri(String caption, Uri[] urisOfPost, String name, String city) {
        this.caption = caption;
        this.urisOfPost = urisOfPost;
        this.name = name;
        this.city = city;
        this.address = name + " " + city;
    }

    public String getCaption() {
        return caption;
    }

    public Uri[] getUrisOfPost() {
            return urisOfPost;
        }

    public String getName(){ return name; }

    public String getCity() { return city; }

    public String getAddress() { return address; }

    public double getDistance() { return distance; }

    public void setDistance(double val) { distance = val; }

    public String getDistanceStr() {
        if (distance == 0.0){
            return "";
        }
        return String.format("%.1f", distance) + " km";
    }

}
