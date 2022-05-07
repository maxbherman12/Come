package com.example.come.ui.home;


import android.annotation.SuppressLint;

public class PostData {
    private String caption;
    private int[] imageArray;
    private String name;
    private String city;
    private double distance;
    private String address;

    public PostData(String caption, int[] images, String name, String city) {
        this.caption = caption;
        this.imageArray = images;
        this.name = name;
        this.city = city;
        this.address = name + " " + city;
        this.distance = -1.0; // means no distance has been found yet
    }

    public String getName(){ return name; }

    public String getCity() { return city; }

    public String getCaption() {
        return caption;
    }

    public int[] getImageArray() {
        return imageArray;
    }

    public String getAddress() { return address; }

    public double getDistance() { return distance; }
    public void setDistance(double val) { distance = val; }

    @SuppressLint("DefaultLocale")
    public String getDistanceStr() {
        if (distance <= 0.0){
            return "";
        }
        return String.format("%.1f", distance) + " km";
    }
}

