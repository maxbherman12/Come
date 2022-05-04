package com.example.come.ui.home;


public class PostData {
    String caption;
    int[] imageArray;
    String name;
    String city;
    double distance;
    String address;

    public PostData(String caption, int[] images, String name, String city) {
        this.caption = caption;
        this.imageArray = images;
        this.name = name;
        this.city = city;
        this.address = name + " " + city;
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

    public String getDistanceStr() {
        if (distance == 0.0){
            return "";
        }
        return String.format("%.1f", distance) + " km";
    }
}

