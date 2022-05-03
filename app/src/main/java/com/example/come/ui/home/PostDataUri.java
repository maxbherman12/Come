package com.example.come.ui.home;

import android.net.Uri;

public class PostDataUri {
    String caption;
    Uri[] urisOfPost;

    public PostDataUri(String caption, Uri[] urisOfPost) {
        this.caption = caption;
        this.urisOfPost = urisOfPost;
    }

    public String getCaption() {
        return caption;
    }

    public Uri[] getUrisOfPost() {
            return urisOfPost;
        }

}
