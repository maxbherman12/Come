package com.example.come.ui.home;


public class PostData {
    String caption;
    int[] imageArray;

    public PostData(String caption, int[] images) {
        this.caption = caption;
        this.imageArray = images;
    }

    public String getCaption() {
        return caption;
    }

    public int[] getImageArray() {
        return imageArray;
    }
}

